import React, {Component} from 'react';
import CommonTable from '../../components/CommonTable'
import {
  	Page,
    Table,
    Column,
    Button,
    Modal,
    ModalHeader,
    ModalBody,
    ModalFooter,
    Icon,
    Divider
} from 'epm-ui';
import './Table.css';

class ${className}Table extends CommonTable {
    constructor(props) {
        super(props);
        this.state = {
            sortData: {
                sortAttribute: "",
                sortType: ""
            },
            visible:false,
            Id:"",
        };
    }

    render() {
        return (
            <Page>
                <Table
                    dataSource={ this.props.tableData } selectable
                    style={{'borderLeft': '1px #DDD solid', 'borderRight': '1px #DDD solid'}}
                    bordered={ true }
                    headBolder={ true }
                    striped={ true }
                    bodyHeight={ '200px' }
                    onSort={ this.handleSort.bind(this) }
                >
                
<#list table.columns as column>
	<#if column.isShow() >
	<#assign ui = column.field.ui>
		<#if ui.code=="DateTimePicker">
					<Column title="${column.description}"  <#if column.isSort()>sort={true}</#if>>
                        {
                            (rowData) => {
                                return  rowData.${column.showField}==null?"":new Date(rowData.${column.showField}).toLocaleString();
                            }
                        }
                    </Column>
		<#elseif ui.code=="Input"> 
			<Column title="${column.description}" dataIndex="${column.showField}" <#if column.isSort()>sort={true}</#if>/>
					
		</#if>
	</#if>
</#list>
				
<#list table.associatedTables?values as foreignKey>
	<#if foreignKey.isShow()>
		<#if foreignKey.relation == "ManyToMany">
					<Column title="<#if foreignKey.description??>${foreignKey.description}</#if>"  <#if foreignKey.isSort()>sort={true}</#if> >
	                    {
	                        (rowData) => {
	                            let temp = rowData.${foreignKey.referenceField};
	                            let temp2="";
	                            for (let i=0; i < temp.length; i++) {
	                                temp2 = temp2 + temp[i].${foreignKey.showField} + ",";
	                            }
	                            return temp2.substring(0, temp2.length-1);
	                        }
	                    }
	                </Column>
		</#if>
        <#if foreignKey.relation == "ManyToOne">
	                <Column title="<#if foreignKey.description??>${foreignKey.description}</#if>" <#if foreignKey.isSort()>sort={true}</#if>>
                        {
                            (rowData) => {
                                return rowData.${foreignKey.referenceField} === null?"":rowData.${foreignKey.referenceField}.${foreignKey.showField};
                            }
                        }
                    </Column>
        </#if>
	</#if>
</#list>
			
                    <Column title="操作">
                        {(value, index, rowData) =>
                            <div>
                                <Button type="primary"
                                        onClick={this.handleUpdate.bind(this, rowData)}>修改</Button>
                                <Button type="info" onClick={this.handleDel.bind(this, rowData)}>删除</Button>
                            </div>
                        }
                    </Column>
                </Table>
                
                 <Modal size="medium" closable={true} visible={ this.state.visible } onClick={this.handleClose.bind(this)}>

                    <ModalHeader>
                        <Icon type="remove" />
                        		删除
                    </ModalHeader>
                    <ModalBody>
                        <Divider />
                       		 确定删除此条信息？
                    </ModalBody>
                    <ModalFooter>
                        <Button type="info" onClick={this.handleClose.bind(this)}>取消</Button>
                        <Button type="primary" onClick={this.handleDelete.bind(this)}>确定</Button>
                    </ModalFooter>
                </Modal>
                
                
            </Page>
        )
    }
}

export default ${className}Table;