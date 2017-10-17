import React from 'react';
import CommonAdd from '../../components/CommonAdd'
import {
    Page,
    fetch,
    Row,
    Container,
    Modal,
    ModalHeader,
    ModalBody,
    ModalFooter,
    Label,
    Form,
    FormItem,
    Button,
    Col,
    Divider,
    Input,
    Select,
    Option,
    TreeSelect,
    DateTimePicker,
    Textarea,
    Checkbox,
    CheckboxGroup,
    Card,
    CardBody
} from 'epm-ui';
import './${className}.css';

class Add extends CommonAdd {
    constructor(props) {
        super(props);

        this.handleValue = this.handleValue.bind(this)
    }

    //提交新增、修改的数据
    handleValue() {
        const formData = this.getValue();
        //this.formDataValidate(formData);
<#assign times = 1>       
<#list table.associatedTables?values as foreignKey>
	<#if foreignKey.relation == "ManyToMany">
		<#if foreignKey.field.ui?? >
			<#assign ui = foreignKey.field.ui>
			<#if ui.code=="Select">
			
		for (let i = 0; i < formData.${foreignKey.referenceField}.length; i++) {
            for (let j = 0; j < this.props.${foreignKey.classTableName?uncap_first}Data.length; j++) {
                if (this.props.${foreignKey.classTableName?uncap_first}Data[j].${foreignKey.field.showField} === formData.${foreignKey.referenceField}[i]) {
                    formData.${foreignKey.referenceField}.splice(i, 1, this.props.${foreignKey.classTableName?uncap_first}Data[j]);
                    break;
                }
            }
        }
					
			<#elseif ui.code=="TreeSelect">
			
		for (let i = 0; i < formData.${foreignKey.referenceField}.length; i++) {
            let obj = this.treeIterator(formData.${foreignKey.referenceField}[i], this.props.${foreignKey.classTableName?uncap_first}Data);
            formData.${foreignKey.referenceField}.splice(i, 1, obj);
        }
			
			</#if>
		</#if>
	</#if>
	<#if foreignKey.relation == "ManyToOne">
		<#if foreignKey.field.ui?? >
			<#assign ui = foreignKey.field.ui>
			<#if ui.code=="Select">
		
		let temp${times} = formData.${foreignKey.referenceField};
		formData.${foreignKey.referenceField} = null;
		for (let j = 0; j < this.props.${foreignKey.classTableName?uncap_first}Data.length; j++) {
            if (this.props.${foreignKey.classTableName?uncap_first}Data[j].${foreignKey.field.showField} == temp${times}) {
                formData.${foreignKey.referenceField} = this.props.${foreignKey.classTableName?uncap_first}Data[j];
                break;
            }
        }
        <#assign times = times + 1>
			
			<#elseif ui.code=="TreeSelect">
			
        let obj = this.treeIterator(formData.${foreignKey.referenceField}, this.props.${foreignKey.classTableName?uncap_first}Data);
        formData.${foreignKey.referenceField} = obj;

			</#if>
		</#if>
	</#if>
</#list>
	
        if(this.props.currentData==null){
            this.props.handleAddDone(formData);
        }else{
            this.props.handleUpdateDone(formData, this.props.currentData.id);
        }
    }

<#list table.associatedTables?values as foreignKey>
	<#if foreignKey.field.ui?? >
		<#assign ui = foreignKey.field.ui>
		<#if ui.code=="Select">    
			<#if foreignKey.relation == "ManyToMany">
			
		showBack${foreignKey.referenceField?cap_first}Value() {
            let ${foreignKey.referenceField?uncap_first}DefaultValue = [];
	        if (this.props.currentData){
	            if(this.props.currentData===null){
	                ${foreignKey.referenceField?uncap_first}DefaultValue = [];
	            }else{
	                for (let i = 0; i < this.props.currentData.${foreignKey.referenceField?uncap_first}.length; i++) {
	                    for (let j = 0; j < this.props.${foreignKey.classTableName?uncap_first}Data.length; j++) {
	                        if (this.props.${foreignKey.classTableName?uncap_first}Data[j].value === this.props.currentData.${foreignKey.referenceField?uncap_first}[i].${foreignKey.field.showField}) {
	                            ${foreignKey.referenceField?uncap_first}DefaultValue.push(this.props.${foreignKey.classTableName?uncap_first}Data[j].value);
	                        }
	                    }
	                }
	            }
	        }
	        return ${foreignKey.referenceField?uncap_first}DefaultValue;
	    }
			
			</#if> 
			<#if foreignKey.relation == "ManyToOne">
			
	    showBack${foreignKey.referenceField?cap_first}Value() {
            let ${foreignKey.referenceField?uncap_first}DefaultValue = [];
	        if (this.props.currentData){
	            if(this.props.currentData===null){
	                ${foreignKey.referenceField?uncap_first}DefaultValue = [];
	            }else{
	                for (let j = 0; j < this.props.${foreignKey.classTableName?uncap_first}Data.length; j++) {
	                    if (this.props.currentData.${foreignKey.referenceField?uncap_first} && this.props.${foreignKey.classTableName?uncap_first}Data[j].value === this.props.currentData.${foreignKey.referenceField?uncap_first}.${foreignKey.field.showField}) {
	                        ${foreignKey.referenceField?uncap_first}DefaultValue.push(this.props.${foreignKey.classTableName?uncap_first}Data[j].value);
	                    }
	                }
	            }
	        }
	        return ${foreignKey.referenceField?uncap_first}DefaultValue;
	    }
			    
			</#if> 
		<#elseif ui.code=="TreeSelect">
			<#if foreignKey.relation == "ManyToMany">
			
		showBack${foreignKey.referenceField?cap_first}Value() {
            let ${foreignKey.referenceField?uncap_first}DefaultValue = [];
		    if (this.props.currentData){
		        if(this.props.currentData===null){
		            ${foreignKey.referenceField?uncap_first}DefaultValue = [];
		        }else{
		            for (let i = 0; i < this.props.currentData.${foreignKey.referenceField?uncap_first}.length; i++) {
		                let obj = new Object();
		                obj.value = this.props.currentData.${foreignKey.referenceField?uncap_first}[i].id;
		                obj.name = this.props.currentData.${foreignKey.referenceField?uncap_first}[i].${foreignKey.field.showField};
		                ${foreignKey.referenceField?uncap_first}DefaultValue.push(obj);
		            }
		        }
		    }
		    return ${foreignKey.referenceField?uncap_first}DefaultValue;
		}
			
			</#if> 
			<#if foreignKey.relation == "ManyToOne">
			
		showBack${foreignKey.referenceField?cap_first}Value() {
            let ${foreignKey.referenceField?uncap_first}DefaultValue = {};
		    if (this.props.currentData){
		        if(this.props.currentData===null){
		            ${foreignKey.referenceField?uncap_first}DefaultValue = {};
		        }else{
		        	if(this.props.currentData.${foreignKey.referenceField?uncap_first}){
		                ${foreignKey.referenceField?uncap_first}DefaultValue.value = this.props.currentData.${foreignKey.referenceField?uncap_first}.id;
		                ${foreignKey.referenceField?uncap_first}DefaultValue.name = this.props.currentData.${foreignKey.referenceField?uncap_first}.${foreignKey.field.showField};
		        	}
		        }
		    }
		    return ${foreignKey.referenceField?uncap_first}DefaultValue;
		}
			
			</#if>
		</#if>
	</#if>
</#list>

    render() {
        let title= "";
        if(this.props.currentData){
            title = "${table.description}修改";
        }else{
            title = "${table.description}新增";
        }

        return (
            <Page>
                <Container type="fluid">
                    <div className="app">
                        <div className="rightWrap">
                            <div className="box-title">
                                <h4 className="box-title-h" style={{'fontWeight': 'bold'}}>{ title}</h4>
                            </div>
                            <div className="right-box">
                                <Form
                                    getter={ this.formGetter.bind(this) }
                                    method="post"
                                    type="horizontal"
                                    async={ true }
                                    onSubmit={ this.handleValue.bind(this) }
                                    action={""}
                                >
                                    <Card>
                                        <CardBody>
                                        <Row>
                                        	
<#list table.columns as column>
	<#assign uitype = column.javaType>
	<#if  uitype=="java.lang.Boolean">
												<Col size={{ small: 24, medium: 12, large: 12 }}>
	                                        		<FormItem name="${column.columnName?uncap_first}">
			                                            <Label>${column.description}：</Label>
			                                            <Select  name="${column.columnName?uncap_first}" placeholder="请输入${column.description}"       
			                                                   value={this.props.currentData==null?"":(this.props.currentData.${column.columnName?uncap_first}==null?"":(this.props.currentData.${column.columnName?uncap_first}).toString())}>
																  <Option value="true">是 </Option>
																  <Option value="false">否</Option>
														</Select>
	                                        		</FormItem>
	                                        	</Col>
			
	<#else>
		<#if column.field.ui?? >
			<#assign ui = column.field.ui>
			
			<#if ui.code=="DateTimePicker">
												<Col size={{ small: 24, medium: 12, large: 12 }}>
	                                        		<FormItem name="${column.columnName?uncap_first}">
			                                            <Label>${column.description}：</Label>
			                                            <DateTimePicker  name="${column.columnName?uncap_first}" placeholder="请输入${column.description}"
			                                                   value={this.props.currentData==null?"":new Date(this.props.currentData.${column.columnName?uncap_first})}/>
	                                        		</FormItem>
	                                        	</Col>
											
			
			<#elseif ui.code=="Input">
				<#if column.columnNameLower=="id">
												<Col size={{ small: 24, medium: 12, large: 12 }} style={{display:'none'}}>
													<FormItem name="${column.columnName?uncap_first}" >
			                                            <Label>${column.description}：</Label>
			                                            <Input type="text" name="${column.columnName?uncap_first}" placeholder="请输入${column.description}"
			                                                   value={this.props.currentData==null?"":this.props.currentData.${column.columnName?uncap_first}}/>
			                                        </FormItem>
												</Col>
				<#else>
												<Col size={{ small: 24, medium: 12, large: 12 }}>
													<FormItem name="${column.columnName?uncap_first}">
			                                            <Label>${column.description}：</Label>
			                                            <Input type="text" name="${column.columnName?uncap_first}" placeholder="请输入${column.description}"
			                                                   value={this.props.currentData==null?"":this.props.currentData.${column.columnName?uncap_first}}/>
			                                        </FormItem>
												</Col>
				</#if>								
												
			</#if>
		</#if>
	</#if>
</#list>
   	
<#list table.associatedTables?values as foreignKey>
	<#if foreignKey.field.ui?? >
		<#assign ui = foreignKey.field.ui>
		<#if ui.code=="Select">
		
									<Col size={{ small: 24, medium: 12, large: 12 }}>
										<FormItem name="${foreignKey.referenceField}">
						                    <Label><#if foreignKey.description??>${foreignKey.description}</#if>：</Label>
						                    <Select placeholder="请选择<#if foreignKey.description??>${foreignKey.description}</#if>" 
					                            dataSource={ this.props.${foreignKey.classTableName?uncap_first}Data }
                                                value={this.showBack${foreignKey.referenceField?cap_first}Value()} 
                                                <#if foreignKey.relation == "ManyToOne">
						                        multiple={ false }
						                        </#if>
						                        <#if foreignKey.relation == "ManyToMany">
						                        multiple={ true }
						                        </#if>
                                        		/>
						                </FormItem>
									</Col>
		
										
		<#elseif ui.code=="TreeSelect">	
									<Col size={{ small: 24, medium: 12, large: 12 }}>
										<FormItem name="${foreignKey.referenceField}">
						                    <Label><#if foreignKey.description??>${foreignKey.description}</#if>：</Label>
						                    <TreeSelect
						                        dataSource={ this.props.${foreignKey.classTableName?uncap_first}Data }
						                        dataValueMapper={ 'id' }
						                        checkable={ false }
						                        placeholder="请选择<#if foreignKey.description??>${foreignKey.description}</#if>"
						                        <#if foreignKey.relation == "ManyToOne">
						                        multiple={ false }
						                        </#if>
						                        <#if foreignKey.relation == "ManyToMany">
						                        multiple={ true }
						                        </#if>
                                                defaultValue={this.showBack${foreignKey.referenceField?cap_first}Value()}
						                    />
						                </FormItem>
									</Col>
								
										
		</#if>
	</#if>
</#list>
</Row>


<Row>
<#list table.columns as column1>
	<#if column1.field.ui?? >
		<#assign ui = column1.field.ui>
		
		<#if ui.code=="Textarea">
				 
	         								<Col size={{ small: 24, medium: 24, large: 24 }}>
	         								<Row>
												<Col style={{width:'9%'}}>
													<h5>${column1.description}：</h5>
												</Col>
	                                            <Col style={{width:'90%'}}>
	                                             	<Textarea type="text" name="${column1.columnName?uncap_first}" placeholder="请输入${column1.description}"
	                                                   value={this.props.currentData==null?"":this.props.currentData.${column1.columnName?uncap_first}}/>
	                                            </Col>      	
	                                        </Row>	  
											</Col>
         								                           
		</#if>
	</#if>
</#list>
</Row>                                      	                                      
                                            <Divider/>
                                            <Button type="success" htmlType="submit" onClick={this.handleClose.bind(this)}>是</Button>
                                            <Button type="info" onClick={this.handleClose.bind(this)}>否</Button>
										</CardBody>
                                    </Card>
								</Form>
                            </div>
                        </div>
                    </div>
                    <Divider/>
                </Container>
            </Page>
        );
    }
}
export default  Add;