<#assign className = table.className>
<#assign classNameLower = className?uncap_first>

package ${basepackage}.api;

import java.util.List;

import javax.annotation.Resource;
import javax.validation.Valid;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import ${basepackage}.bean.PageInfo;
import ${basepackage}.domain.*;
import ${basepackage}.service.interfac.*;
import ${basepackage}.util.PageInfoUtil;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;  
  
  
@RestController  
@RequestMapping("/api")  
public class ${className}Api { 
	
		private static final Log logger = LogFactory.getLog(${className}Api.class);
  
        @Resource(name="${classNameLower}Service")  
        private I${className}Service ${classNameLower}Service;
        
        /*
         * 查询所有${table.description}信息
         */  
        @ApiOperation(value="获取全部${table.description}信息", notes="")
        @RequestMapping(value="/${classNameLower}s",method = RequestMethod.GET)  
        public List<${className}>getAll${className}s(){  
            return ${classNameLower}Service.findAll();  
        }  
        
        /*
         * 获取pathVariable参数id
         * 查询id的信息
         */
        @ApiOperation(value="获取单个${table.description}信息", notes="根据url的id来获取${table.description}信息")
        @ApiImplicitParam(name = "id", value = "${table.description}ID", required = true, dataType = "Long")
        @RequestMapping(value="/${classNameLower}/{id}", method = RequestMethod.GET)  
        public ResponseEntity<${className}> get${className}(@PathVariable("id") Long id){  
            ${className} ${classNameLower} = ${classNameLower}Service.findOne(id);  
            if(${classNameLower} == null){  
                return new ResponseEntity<${className}>(HttpStatus.NOT_FOUND);  
            }  
            return new ResponseEntity<${className}>(${classNameLower},HttpStatus.OK);  
        }
        
        /*
         *  模糊查询并分页
         */
        @ApiOperation(value="分页获取${table.description}信息", notes="根据传过来的参数来分页获取${table.description}信息")
        @ApiImplicitParams({
                @ApiImplicitParam(name = "${classNameLower}", value = "${table.description}信息", required = false, dataType = "${className}"),
                @ApiImplicitParam(name = "pageable", value = "页面信息", required = false, dataType = "PageInfo"),
        })
        @RequestMapping(value="/${classNameLower}",method = RequestMethod.GET)  
        public Page<${className}> getAll${className}(${className} ${classNameLower}, PageInfo pageInfo){
            Pageable pageable = PageInfoUtil.retirevePageInfo(pageInfo);
            return ${classNameLower}Service.findByAuto(${classNameLower}, pageable);
        } 
        
        /*
         * 通过 requestBody获取${table.description}信息
         * 在数据库中添加一条${table.description}信息
         */  
        @ApiOperation(value="新增${table.description}信息", notes="根据传过来的${classNameLower}信息添加${table.description}")
        @ApiImplicitParams({
                @ApiImplicitParam(name = "${classNameLower}", value = "${table.description}详细实体${classNameLower}", required = true, dataType = "${className}")
        })
        @RequestMapping(value="/${classNameLower}", method = RequestMethod.POST)  
        public ResponseEntity<${className}> create${className}(@Valid @RequestBody ${className} ${classNameLower}){ 
        	${classNameLower}.setId(Long.MAX_VALUE);
        	${className} ${classNameLower}Db = ${classNameLower}Service.save(${classNameLower});
        	return new ResponseEntity<${className}>(${classNameLower}Db,HttpStatus.OK);
        } 
        
        /*
         * 通过  pathVariable获取${table.description}id
         * 通过 requestBody获取${table.description}信息
         * 更新id的${table.description}信息
         */
        @ApiOperation(value="更新${table.description}信息", notes="根据url的id来指定更新对象，并根据传过来的user信息来更新${table.description}详细信息")
        @ApiImplicitParams({
                @ApiImplicitParam(name = "id", value = "${table.description}ID", required = true, dataType = "Long"),
                @ApiImplicitParam(name = "${classNameLower}", value = "${table.description}详细实体${classNameLower}", required = true, dataType = "${className}")
        })
        @RequestMapping(value="/${classNameLower}/{id}", method = RequestMethod.PUT)  
        public ResponseEntity<${className}> update${className}(@Valid @RequestBody ${className} ${classNameLower}, @PathVariable("id") Long id){  
            ${className} ${classNameLower}Db = ${classNameLower}Service.findOne(id);  
            if(${classNameLower}Db == null){  
                return new ResponseEntity<${className}>(HttpStatus.NOT_FOUND);  
            }  
            else{  
                ${classNameLower}Db = ${classNameLower}Service.save(${classNameLower});  
                return new ResponseEntity<${className}>(${classNameLower}Db,HttpStatus.OK);  
            }  
        }  
        
        /*
         * 通过  pathVariable获取${table.description}id
         * 删除id的${table.description}
         */ 
        @ApiOperation(value="删除${table.description}", notes="根据url的id来指定删除对象")
        @ApiImplicitParam(name = "id", value = "${table.description}ID", required = true, dataType = "Long")
        @RequestMapping(value="/${classNameLower}/{id}", method = RequestMethod.DELETE)  
        public void delete${className}(@PathVariable("id") Long id) {  
            ${classNameLower}Service.delete(id);  
        }         
} 