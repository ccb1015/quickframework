package com.bonc.quickframework.generator;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.FileHandler;

import com.bonc.quickframework.entity.GeneratorDbserver;
import com.bonc.quickframework.entity.GeneratorEntity;
import com.bonc.quickframework.entity.GeneratorProject;
import com.bonc.quickframework.entity.GeneratorService;
import com.bonc.quickframework.entity.GeneratorWebserver;
import com.bonc.quickframework.generator.entity.Column;
import com.bonc.quickframework.generator.entity.Table;
import com.bonc.quickframework.generator.helper.FileHelper;
import com.bonc.quickframework.generator.helper.ShellHelper;

public class GeneratorFacade {

	/**
	 * 创建项目
	 * 
	 * @param project
	 * @param templateRootPath
	 * @param outRootPath
	 * @throws Exception
	 */
	public static void makeProjectCode(GeneratorProject project,GeneratorWebserver webserver,
			GeneratorDbserver database, Collection<GeneratorEntity> entities,Collection<GeneratorService> services,
			String templateRootPath, String outRootPath,boolean isDelete) throws Exception {
		Generator g = new Generator(templateRootPath, outRootPath);
		List<Map> models = new ArrayList<Map>();
		// project
		Map<String, Object> projectInfo = DataParser.parseProjectData(project,outRootPath);
		// entitys
		List<Table> tableInfos = new ArrayList<Table>();
		if (entities != null && entities.size() > 0) {
			tableInfos = DataParser.parseTableData(entities);
		} else {
			Table table=new Table("helloworld", "test");
			Column column=new Column("id", "");
			column.setPk(true);
			column.setJavaType("Long");
			table.getColumns().add(column);
			tableInfos.add(table);
		}
		projectInfo.put("tables", tableInfos);
		projectInfo.put("services", services);
		projectInfo.put("webserver", webserver);
		projectInfo.put("dbserver", database);
		/*for (Table table : tableInfos) {
			Map<String, Object> data = new HashMap<String, Object>(projectInfo);
			data.put("table", table);
			data.put("className", table.getClassName());
			models.add(data);
		}
		g.buildAllTemplates(null, models);*/
		
		
		g.buildProjectTemplates(projectInfo,isDelete);
		System.out.println(" ------------------------------------------------------------------------");
		System.out.println(" GENERATE SUCCESS");
		System.out.println(" ------------------------------------------------------------------------");
	}


	/**
	 * 部署项目
	 * 
	 * @param codePath
	 *            代码目录（项目根目录）
	 */
	public static boolean compileAndDeploy(String scriptPath)
			throws Exception {
		String commandFile="redeploy.sh";
		if(ShellHelper.isWindow){
			commandFile = "redeploy.bat";
		}
		File file=new File(scriptPath,commandFile);
		commandFile = file.getAbsolutePath().replace("\\", "/");
//		if(!ShellHelper.isWindow){
//			commandFile ="sh "+ commandFile;
//		}
		
		if(!file.exists()){
			throw new Exception("file not fund:"+commandFile);
		}
		String resultStr = ShellHelper.excuteCommandFile(commandFile);
		/*Uploaded: http://localhost:8080/manager/html/deploy?path=%2FDemo&update=true (15264 KB at 9551.5 KB/sec)

			[INFO] tomcatManager status code:200, ReasonPhrase:OK
			[INFO] OK - Undeployed application at context path /Demo
			[INFO] OK - Deployed application at context path /Demo
			[INFO] ------------------------------------------------------------------------
			[INFO] BUILD SUCCESS
			[INFO] ------------------------------------------------------------------------*/
		if(resultStr.contains("OK - Deployed application at context path")){
			if(resultStr.contains("BUILD SUCCESS")){
				return true;
			}
		}
		throw new Exception("deploy error:"+resultStr);
	}
	/**
	 * 清理项目
	 * @param scriptPath
	 * @return
	 * @throws Exception
	 */
	public static boolean clean(String scriptPath)
			throws Exception {
		String commandFile="sh clean.sh";
		if(ShellHelper.isWindow){
			commandFile = "clean.bat";
		}
		File file=new File(scriptPath,commandFile);
		commandFile = file.getAbsolutePath().replace("\\", "/");

		if(!file.exists()){
			throw new Exception("file not fund:"+commandFile);
		}
		String resultStr = ShellHelper.excuteCommandFile(commandFile);
		
		if(resultStr.contains("BUILD SUCCESS")){
			return true;
		}
		throw new Exception("deploy error:"+resultStr);
	}
	
	public static void main(String[] args) throws Exception {
		System.out.println(clean("C:/HelloWorld"));
	}
}
