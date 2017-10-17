import React, {Component, PropTypes} from 'react';
import CommonSearchBox from '../../components/CommonSearchBox'
import {
    Page,
    Row,
    Container,
    Label,
    Form,
    FormItem,
    Button,
    Col,
    Divider,
    Input,
    Select,
    TreeSelect,

} from 'epm-ui';

class SearchBox extends CommonSearchBox {
    constructor(props) {
        super(props);
        this.state = {
<#list table.columns as column>
	<#if column.isSearchCondition() >
        ${column.searchField}: "",
	</#if>
</#list>            
<#list table.associatedTables?values as foreignKey>
	<#if foreignKey.isSearchCondition()>
		${foreignKey.searchField}: "",
	</#if>
</#list>
        };
        this.handleSubmit = this.handleSubmit.bind(this);
    }
    
<#list table.columns as column>
	<#if column.isSearchCondition() >
	handle${column.searchField}Change(data) {
        this.setState({"${column.searchField}": data});
    }
	</#if>
</#list>

<#list table.associatedTables?values as foreignKey>
	<#if foreignKey.isSearchCondition()>
		
		<#if foreignKey.field.ui?? >
			<#assign ui = foreignKey.field.ui>
			<#if ui.code=="Select">
	handle${foreignKey.classTableName}Change(data) {
        this.setState({"${foreignKey.searchField}": data});
    }
			<#elseif ui.code=="TreeSelect">							
    handle${foreignKey.classTableName}Select(node, isSelected, selectedNodes, event) {
        this.setState({"${foreignKey.searchField}": node.name});
    }
			</#if>
		</#if>
	</#if>
</#list>

    render() {
        return (
            <Page>
                <Container type="fluid">
                    <Divider/>
                    <Row>
                        <Col size={20}>
                            <Form type="inline" action="#">
                                <Row>
                                </Row>
                                
<#list table.columns as column>
	<#if column.isSearchCondition() >
		        				<FormItem>
                                    <Label>${column.description}：</Label>
                                    <Input type="text" value={this.props.searchData.${column.description}} placeholder="请输入${column.searchField}"
                                           onChange={this.handle${column.searchField}Change.bind(this)}/>
                                </FormItem>
	</#if>
</#list>
		
<#list table.associatedTables?values as foreignKey>
	<#if foreignKey.isSearchCondition()>
		<#if foreignKey.field.ui?? >
			<#assign ui = foreignKey.field.ui>
			<#if ui.code=="Select">
							<FormItem>
                                <Label><#if foreignKey.description??>${foreignKey.description}</#if>：</Label>
                                <Select placeholder="请选择<#if foreignKey.description??>${foreignKey.description}</#if>" 
                                		dataSource={  this.props.${foreignKey.classTableName?uncap_first}Data }
                                		multiple={ false }
                                        onChange={this.handle${foreignKey.classTableName}Change.bind(this)} />
                            </FormItem>
			<#elseif ui.code=="TreeSelect">							
							<FormItem name="treeselect">
                                <Label><#if foreignKey.description??>${foreignKey.description}</#if>：</Label>
                                <TreeSelect
                                    dataSource={ this.props.${foreignKey.classTableName?uncap_first}Data }
                                    dataValueMapper={ 'id' }
                                    checkable={ false }
                                    multiple={ false }
                                    placeholder="请选择<#if foreignKey.description??>${foreignKey.description}</#if>"
                                    onSelect={this.handle${foreignKey.classTableName}Select.bind(this)}
                                />
                            </FormItem>
            <#else>
                    		<FormItem>
                                <Label>${column.description}：</Label>
                                <Input type="text" value={this.props.searchData.${column.description}} placeholder="请输入${column.searchField}"
                                       onChange={this.handle${column.searchField}Change.bind(this)}/>
                            </FormItem>
			</#if>
		</#if>
	</#if>
</#list>
                            </Form>
                        </Col>
                        <Col size={2}>
                            <Button type="info" style={{width: '92px'}} onClick={this.handleSubmit.bind(this)}>查询</Button>
                        </Col>
                    </Row>
                    <Divider fitted/>
                </Container>
            </Page>
        );
    }
}

export default  SearchBox;