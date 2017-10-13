package ${basepackage};

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;

/**
 * 打包成war包在web容器运行,需要继承SpringBootServletInitializer,覆盖config
 */
public class ServletInitializer extends SpringBootServletInitializer {  
	  
    @Override  
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {  
        return application.sources(WebAppConfig.class);  
    }  
  
}  
