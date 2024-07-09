package com.fastermaker.config;

import com.alibaba.druid.filter.stat.StatFilter;
import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.jakarta.StatViewServlet;
import com.alibaba.druid.support.jakarta.WebStatFilter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.sql.DataSource;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 数据源配置
 */
@Configuration
public class DataSourceConfig implements WebMvcConfigurer {
    @ConfigurationProperties("spring.datasource")
    @ConditionalOnProperty(name = "spring.datasource.type", havingValue = "com.alibaba.druid.pool.DruidDataSource")
    @Bean
    public DataSource dataSource(){
        DruidDataSource druidDataSource = new DruidDataSource();
        return druidDataSource;
    }
    /**
     * 开启 Druid 数据源内置监控页面
     *
     * @return
     */
    @Bean("statViewServlet")
    public ServletRegistrationBean statViewServlet() {
        StatViewServlet statViewServlet = new StatViewServlet();
        //向容器中注入 StatViewServlet，并将其路径映射设置为 /druid/*
        ServletRegistrationBean servletRegistrationBean = new ServletRegistrationBean(statViewServlet, "/druid/*");
        //配置监控页面访问的账号和密码（选配）
      //  servletRegistrationBean.addInitParameter("loginUsername", "admin");
      //  servletRegistrationBean.addInitParameter("loginPassword", "123456");
        return servletRegistrationBean;
    }
    @Bean
    public StatFilter statFilter() {
        StatFilter statFilter = new StatFilter();
        //慢sql记录
        statFilter.setLogSlowSql(true);
        statFilter.setMergeSql(true);
        //超过多少时间为慢sql
        statFilter.setSlowSqlMillis(5000);
        return statFilter;
    }
    /**
     * 配置一个web监控的filter
     */
    @Bean("webStatFilter")
    @ConditionalOnClass(DruidDataSource.class)
    public FilterRegistrationBean webStatFilter(){
        Map<String,String> initParams = new HashMap<>();
        // 这些不进行统计
        initParams.put("exclusions","*.js,*.css,*.gif,*.png,/druid/*");
        FilterRegistrationBean bean = new FilterRegistrationBean();
        //白名单
        //bean.addInitParameter("allow", "");//多个ip逗号隔开
        //IP黑名单 (存在共同时，deny优先于allow) : 如果满足deny的话提示:Sorry, you are not permitted to view this page.
        //servletRegistrationBean.addInitParameter("deny", "");
        WebStatFilter webStatFilter= new WebStatFilter();
        webStatFilter.setSessionStatEnable(true);
        bean.setFilter(webStatFilter);
        bean.setInitParameters(initParams);
        bean.setUrlPatterns(Arrays.asList("/*"));
        return  bean;
    }
}
