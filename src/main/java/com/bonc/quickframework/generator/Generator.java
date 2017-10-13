package com.bonc.quickframework.generator;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.bonc.quickframework.entity.GeneratorService;
import com.bonc.quickframework.generator.entity.Table;
import com.bonc.quickframework.generator.helper.FileHelper;
import com.bonc.quickframework.generator.helper.FreemarkerHelper;
import com.bonc.quickframework.generator.helper.IOHelper;
import com.bonc.quickframework.generator.helper.StringHelper;

import freemarker.template.Configuration;
import freemarker.template.Template;

public class Generator {

	private File inRootPath = new File("template");
	private File outRootPath = new File("outRoot");
	private final String encoding = "UTF-8";
	private Configuration conf=new Configuration();

	public Generator() {
		this(null, null);
	}

	public Generator(String templatePath, String outPath) {
		if (StringHelper.isBlank(templatePath)) {
			templatePath = "template";
		}
		if (StringHelper.isBlank(outPath)) {
			outPath = "temp";
		}
		
		this.inRootPath = new File((new File(templatePath)).getAbsolutePath().replace("\\", File.separator));
		this.outRootPath = new File((new File(outPath)).getAbsolutePath().replace("\\", File.separator));
		
		if (!outRootPath.exists()) {
			outRootPath.mkdirs();
		}
		try {
			//
			conf.setDirectoryForTemplateLoading(inRootPath); //设置模板文件的目录
			conf.setDefaultEncoding(encoding);  
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println("initial template path error");
		}
	}
	/**
	 * 编译模版文件
	 * @param models 参数map集合
	 * @param templateFiles 模版文件（默认所有模版文件）
	 */
	public void buildProjectTemplates(Map projectInfo,Boolean isDelete)
			throws Exception {
		if(isDelete){
			FileHelper.cleanDirectory(outRootPath);
		}
		List<String> templateFiles = this.listAllTemplatFiles();
		if(templateFiles == null || templateFiles.size()==0){
			throw new RuntimeException("template file not found.");
		}
		//实体对象
		List<Map> tableModels = new ArrayList<Map>();
		Collection<Table> tables = (Collection<Table>) projectInfo.get("tables");
		if(tables!=null){
			for (Table table : tables) {
				Map<String, Object> tableMap = new HashMap<String, Object>(projectInfo);
				tableMap.put("table", table);
				tableMap.put("className", table.getClassName());
				tableModels.add(tableMap);
			}
		}
		//接口对象
		List<Map> apiModels=new ArrayList<Map>();
		Collection<GeneratorService> services = (Collection<GeneratorService>) projectInfo.get("services");
		if(services!=null){
			for (GeneratorService service : services) {
				Map<String, Object> apiMap = new HashMap<String, Object>(projectInfo);
				apiMap.put("service", service);
				service.setCode(StringHelper.javaTypeName(service.getCode()));
				apiMap.put("className",service.getCode());
				apiModels.add(apiMap);
			}
		}
		for (String tmpFile : templateFiles) {
			if(FileHelper.isCopyFile(tmpFile)){//拷贝文件
				if(StringHelper.containExpression(tmpFile)){
					for (Map model : tableModels) {
						String outFileName = getOutFileName(new File(tmpFile), model);
						IOHelper.copyAndClose(new FileInputStream(tmpFile),new FileOutputStream(outFileName));
						System.out.println("[copy]      "+tmpFile+"  ---->  "+ outFileName);
					}
				}else{
					String outFileName = getOutFileName(new File(tmpFile), null);
					IOHelper.copyAndClose(new FileInputStream(tmpFile),new FileOutputStream(outFileName));
					System.out.println("[copy]      "+tmpFile+"  ---->  "+ outFileName);
				}
			}else{//模版文件
				if(StringHelper.containExpression(tmpFile)){
					for (Map tableModel : tableModels) {
						this.buildTemplate(tableModel, tmpFile);
					}
					if(FileHelper.isApiFile(tmpFile)){//web service接口
						for(Map apiModel:apiModels){
							this.buildTemplate(apiModel, tmpFile);
						}
					}
				}else{
					this.buildTemplate(projectInfo, tmpFile);
				}
			}
		}
	}

	/**
	 * 编译模版文件
	 * @param model 参数map
	 * @param tmpFile 模版文件
	 */
	public void buildTemplate(Map model, String tmpFile) throws Exception {
		if(StringHelper.isBlank(tmpFile)){
			throw new Exception("template can't be null.");
		}
		String outPath = this.getOutFileName(new File(tmpFile), model);
		if (StringHelper.containExpression(outPath)) {
			return;
		}
		File outFile = new File(outPath);
		if (new File(tmpFile).isDirectory()) {
			if (!outFile.exists())
				outFile.mkdirs();
		} else {
			if (!outFile.getParentFile().exists())
				outFile.getParentFile().mkdirs();
			this.processTemplate(model, tmpFile, outFile);
		}
	}

	private void processTemplate(Map model, String templateFile, File outputFile)
			throws Exception {
		/*String content = IOHelper.readFile(new File(templateFile), encoding);
		Template template = new Template(null, new StringReader(content), conf);*/
		
		String tmp =FileHelper.getRelativePath(this.inRootPath, new File(templateFile));
		Template template = conf.getTemplate(tmp);
		FreemarkerHelper.processTemplate(template, model, outputFile, encoding);
		
		System.out.println("[generate]      "+templateFile+"  ---->  "+ outputFile);
	}

	//扫描模版列表
	private List<String> listAllTemplatFiles() throws Exception {
		List<String> result = new ArrayList<String>();
		try {
			List list = FileHelper.searchAllNotIgnoreFile(this.inRootPath);
			for (Object object : list) {
				File file = ((File) object);
				String filePath = file.getPath().replace("\\",File.separator);
				if (filePath.toLowerCase().endsWith(".include")) {
					System.out.println("[ignore]     file name end with 'include'  ---->  " + filePath);
				}else{
					result.add(filePath);
				}
			}
		} catch (Exception e) {
			throw new Exception("scan template file error!");
		}
		return result;
	}
	/**
	 * 根据模版路径  得到 目标路径
	 * @param templateFile
	 * @param model
	 */
	private String getOutFileName(File templateFile,Map model){
		String outFileName = null;
		try {
			outFileName = FileHelper.getRelativePath(this.inRootPath,templateFile);
			if(StringHelper.containExpression(outFileName)){
				outFileName = FreemarkerHelper.processTemplateString(outFileName,model, conf);
			}
			if (templateFile.isDirectory()) {
				outFileName = outFileName.replace(".", File.separator);
			}
		} catch (Exception e) {
			System.err.println(" warings : process filename error. the file is:"+templateFile.getAbsolutePath());
		}
		return new File(this.outRootPath,outFileName).getAbsolutePath().replace("\\", File.separator);
	}
	
	
}
