
package com.bonc.quickframework.generator;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.bonc.quickframework.entity.GeneratorDbserver;
import com.bonc.quickframework.entity.GeneratorProject;
import com.bonc.quickframework.entity.GeneratorWebserver;
import com.bonc.quickframework.service.IGeneratorDbserverService;
import com.bonc.quickframework.service.IGeneratorProjectService;
import com.bonc.quickframework.service.IGeneratorWebserverService;

public class testGenerator {

	static IGeneratorProjectService projectService = null;
	static IGeneratorDbserverService dbService = null;
	static IGeneratorWebserverService webService = null;
	static {
		ApplicationContext context = new ClassPathXmlApplicationContext(
				"applicationContext.xml");
		projectService = (IGeneratorProjectService) context
				.getBean("generatorProjectService");
		dbService = (IGeneratorDbserverService) context.getBean("generatorDbserverService");
		webService = (IGeneratorWebserverService) context.getBean("generatorWebserverService");
	}

	public static void main(String[] args) {
		String tmpPath = "my_mvn_template";
		String outPath = "C:/Demo";
		try {
			System.out.println("*******     正在查询数据...    *********");
			GeneratorProject project = projectService.findById(1L);
			GeneratorDbserver db=dbService.findById(project.getDbserverId());
			GeneratorWebserver web=webService.findById(project.getWebserverId());
			
			System.out.println("*******     正在生成代码 ...    *********");
			GeneratorFacade.makeProjectCode(project,web,db,project.getGeneratorEntitys(),null,tmpPath, outPath,false);
			System.out.println("*******     生成代码：success     *********");
			
			System.out.println("*******     正在 编译 部署 ... *********");
			Boolean deployResult= GeneratorFacade.compileAndDeploy(outPath);
			System.out.println("*******     部署结果 :"+deployResult);
			
		} catch (Exception e) {
			e.printStackTrace();
			/*try {
				FileHelper.cleanDirectory(new File(outPath));
			} catch (IOException e1) {
			}*/
		}
	}

}
