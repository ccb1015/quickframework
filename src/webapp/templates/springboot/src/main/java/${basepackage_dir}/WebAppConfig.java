package ${basepackage};

import ${basepackage}.web.config.LoginFilterBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.embedded.FilterRegistrationBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ImportResource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import ${basepackage}.dao.base.BaseRepositoryFactoryBean;

import javax.servlet.FilterRegistration;

@EnableConfigurationProperties(LoginFilterBean.class)
@SpringBootApplication
@ImportResource(locations = { "classpath:application-bean.xml" })
@EnableTransactionManagement(proxyTargetClass = true)
@EnableJpaRepositories(repositoryFactoryBeanClass = BaseRepositoryFactoryBean.class)
public class WebAppConfig extends WebMvcConfigurerAdapter {

	public static void main(String[] args) {
		SpringApplication.run(WebAppConfig.class, args);
	}

	/*
	 * public void addInterceptors(InterceptorRegistry registry) {
	 * registry.addInterceptor(new
	 * UserSecurityInterceptor()).addPathPatterns("/**"
	 * ).excludePathPatterns("/error","/login","/signin"); }
	 */

}