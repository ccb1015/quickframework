package ${basepackage}.web.filter;


import javax.servlet.*;
import java.io.IOException;
import java.util.Enumeration;

/**
 * Created by dgl on 17/2/9.
 */
public class LoginFilter implements Filter {
    @Override
    public void init(FilterConfig config) throws ServletException {

        Enumeration<String> parameterNames = config.getInitParameterNames();
        while (parameterNames.hasMoreElements()) {
            String s = parameterNames.nextElement();
            System.out.println(s+":"+config.getInitParameter(s));
        }
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

       // System.out.println("====执行过滤");
        filterChain.doFilter(servletRequest,servletResponse);
    }

    @Override
    public void destroy() {

    }
}
