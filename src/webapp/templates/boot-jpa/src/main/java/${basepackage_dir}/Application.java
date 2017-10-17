package ${basepackage};

import org.springframework.boot.SpringApplication;  
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import ${basepackage}.repository.BaseRepositoryFactoryBean;

@EnableJpaRepositories(repositoryFactoryBeanClass = BaseRepositoryFactoryBean.class)
@EnableTransactionManagement
@SpringBootApplication
public class Application {  
    public static void main(String[] args){  
        SpringApplication.run(Application.class, args);  
    }  
}  