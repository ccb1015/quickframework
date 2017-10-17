import React, {Component} from 'react';
import ReactDOM from 'react-dom';
import Common from '../../components/Common'
import './${className}.css';
import ${className}Table from './Table';
import  SearchBox from './SearchBox';
import  Add from './Add';
import PageController from '../../components/PageController'
import {
	Container,
	Page,
    Divider,
    Row,
    Col,
    fetch,
    Button,
    Card,
    CardBody
} from 'epm-ui';

class ${className} extends Common {
    constructor(props) {
        super(props);
        this.state = {
            sortData: {
                sortAttribute: "",
                sortType: ""
            },
            searchData: {
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
            },
            pageData: {
                page: 0,
                size: 10,
                total: 0
            },
            tableData: {
                content: [],
                last: false,
                totalElements: 0,
                totalPages: 0,
                number: 0,
                size: 10,
                first: false
            },
            status: "list",
            currentData: {},
<#list table.associatedTables?values as foreignKey>
			${foreignKey.classTableName?uncap_first}Data: [],
</#list>
            requestPath: this.getRootPath()+`${className?uncap_first}/`,
        };

        this.getData();      
<#list table.associatedTables?values as foreignKey>
		this.get${foreignKey.classTableName}Data();
</#list>
    }    
    
<#list table.associatedTables?values as foreignKey>
    get${foreignKey.classTableName}Data(){
        const url = this.getRootPath()+"${foreignKey.classTableName?uncap_first}s";   
        fetch(url, {
            mode: 'cors',
            method: 'GET'
        })
            .then((res) => res.json())
            .then((responseData) => {
<#if foreignKey.field.ui?? >
	<#assign ui = foreignKey.field.ui>
	<#if ui.code=="Select">
				this.formatSelectData(responseData, "${foreignKey.field.showField}");
	<#elseif ui.code=="TreeSelect">
				const tree = this.arrayToTree(responseData, "${foreignKey.parentFieldOfReferencedObject}");
				this.formatTreeSelectData(tree, "${foreignKey.field.showField}");
	</#if>
</#if>
                this.setState({${foreignKey.classTableName?uncap_first}Data: responseData});
            })
            .catch((error) => {
                console.log("Error fetching and parsing data", error);
            });
    }
</#list>

    handleDelete(id){
        this.deleteData(id<#list table.associatedTables?values as foreignKey>, this.get${foreignKey.classTableName}Data</#list>);
        this.setState({status: "list"});
    }

    //新增保存时触发
    handleAddDone(data){
        this.postData(data<#list table.associatedTables?values as foreignKey>, this.get${foreignKey.classTableName}Data</#list>);
        this.setState({status: "list"});
    }

    //修改保存时触发
    handleUpdateDone(data, id){
        this.updateData(data, id<#list table.associatedTables?values as foreignKey>, this.get${foreignKey.classTableName}Data</#list>);
        this.setState({currentData: {}});
        this.setState({status: "list"});
    }
    
    
    //取消新增或修改时触发
    handleCancel(){
     	this.setState({currentData: {}});
        this.setState({status: "list"});
        this.setState({
        	 searchData: {
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
            }
        })
    }
    


    handlePage() {
        let originURL = this.state.requestPath;
        let URL = originURL + "?currentPage=" + this.state.pageData.page + "&pageSize=" + this.state.pageData.size;
        if (this.state.sortData.sortAttribute !== "") {
            URL = URL + "&sortAttribute=" + this.state.sortData.sortAttribute;
        }
        if (this.state.sortData.sortType !== "") {
            URL = URL + "&sortType=" + this.state.sortData.sortType;
        }
<#list table.columns as column>
	<#if column.isSearchCondition() >
		if (this.state.searchData.${column.searchField} !== "") {
            URL = URL + "&${column.searchField}=" + this.state.searchData.${column.searchField};
        }
	</#if>
</#list>
		
<#list table.associatedTables?values as foreignKey>
	<#if foreignKey.isSearchCondition()>
		<#if foreignKey.relation == "ManyToMany">
		if (this.state.searchData.${foreignKey.searchField} !== "") {
            URL = URL + "&${foreignKey.referenceField}[0].${foreignKey.searchField}=" + this.state.searchData.${foreignKey.searchField};
        }
		</#if>
	</#if>
</#list>
		
<#list table.associatedTables?values as foreignKey>
	<#if foreignKey.isSearchCondition()>
		<#if foreignKey.relation == "ManyToOne">
		if (this.state.searchData.${foreignKey.searchField} !== "") {
            URL = URL + "&${foreignKey.referenceField}.${foreignKey.searchField}=" + this.state.searchData.${foreignKey.searchField};
        }
		</#if>
	</#if>
</#list>

        this.state.requestPath = URL;
        this.getData();
        this.state.requestPath = originURL;
    }

    render() {
        if (this.state.status === "add"){
            return (
                <Add handleAddDone={this.handleAddDone.bind(this)}
                     handleUpdateDone={this.handleUpdateDone.bind(this)}
                     handleCancel={this.handleCancel.bind(this)}
<#list table.associatedTables?values as foreignKey>
					${foreignKey.classTableName?uncap_first}Data= {this.state.${foreignKey.classTableName?uncap_first}Data}
</#list>
                     />
            );
        }else if (this.state.status === "update"){
            return (
                <Add currentData={ this.state.currentData}
                     handleAddDone={this.handleAddDone.bind(this)}
                     handleUpdateDone={this.handleUpdateDone.bind(this)}
                     handleCancel={this.handleCancel.bind(this)}
<#list table.associatedTables?values as foreignKey>
					${foreignKey.classTableName?uncap_first}Data= {this.state.${foreignKey.classTableName?uncap_first}Data}
</#list>
					/>
            );
        }else{
            return (
                <div className="app">
                    <div className="rightWrap">
                        <div className="box-title">
                            <h4 style={{'fontWeight': 'bold'}}>${table.description}</h4>
                        </div>
                        <div className="right-box">
                        <Card>
                            <CardBody>
                            <Row>
                                <Col size={20}>
                                    <SearchBox searchData={this.state.searchData}
                                               receiveSearchData={this.receiveSearchData.bind(this)}
<#list table.associatedTables?values as foreignKey>
	<#if foreignKey.isSearchCondition()>
												${foreignKey.classTableName?uncap_first}Data={this.state.${foreignKey.classTableName?uncap_first}Data}
	</#if>
</#list>
                                               />
                                </Col >
                                 <Col size={4}>
                                    <Page>
                                        <Container type="fluid">
                                            <Divider/>
                                            <Button type="info" style={{width: '92px'}} onClick={this.handleAdd.bind(this)}>新增</Button>
                                            </Container>
                                    </Page>
                                </Col>
                            </Row>
                            <Divider/>
                            <${className}Table tableData={this.state.tableData.content}
                                           receiveSortData={this.receiveSortData.bind(this)}
                                           handleUpdate={this.handleUpdate.bind(this)}
                                           handleDelete={this.handleDelete.bind(this)}/>
                            <Divider/>
                            <PageController pageData={this.state.pageData}
                                            receivePageData={this.receivePageData.bind(this)}/>
                             </CardBody>
                          </Card>
                        </div>
                    </div>
                </div>
            );
        }
    }
}

ReactDOM.render(<${className} />, document.getElementById('root'));