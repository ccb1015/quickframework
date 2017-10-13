package ${basepackage}.web.config;

import ${basepackage}.web.filter.LoginFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.embedded.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Configuration
public class FilterConfig {

    @Bean
    public FilterRegistrationBean greetingFilterRegistrationBean() {
        FilterRegistrationBean registrationBean = new FilterRegistrationBean();
        registrationBean.setName("loginFilter");
        registrationBean.setFilter(new LoginFilter());
        registrationBean.setOrder(1);

        Map<String,String> initParams=new HashMap<>();
        initParams.put("loginUrl","/security/login!toLogin.action");
        initParams.put("skipUrls",".*/login!.*,.*/rest/.*,.*\\.(css|js|jpg|jpeg|png|gif|ico|woff)$");
        registrationBean.setInitParameters(initParams);

        List<String> urlList = new ArrayList<>();
        urlList.add("/*");
        registrationBean.setUrlPatterns(urlList);

        return registrationBean;
    }

    /*@Bean
    @Order(1)
    Filter loginFilter() {
        return new LoginFilter();
    }*/
}
