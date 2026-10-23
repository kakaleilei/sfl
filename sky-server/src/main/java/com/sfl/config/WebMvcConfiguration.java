package com.sfl.config;

import com.sfl.interceptor.JwtTokenAdminInterceptor;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

/**
 * 配置类，注册web层相关组件
 */
@Configuration
@Slf4j
public class WebMvcConfiguration extends WebMvcConfigurationSupport {
    @Autowired
    private JwtTokenAdminInterceptor jwtTokenAdminInterceptor; // Changed from private to public

    /**
     * 注册自定义拦截器
     *
     * @param registry
     */
    @Override
    protected void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(jwtTokenAdminInterceptor)
                .addPathPatterns("/**")
                .excludePathPatterns(
                        "/admin/employee/login", "/admin/employee/register",
                        // 放行 knife4j / springdoc 接口文档相关资源
                        "/doc.html", "/webjars/**", "/v3/api-docs/**",
                        "/swagger-ui/**", "/swagger-resources/**", "/favicon.ico");
    }

    /**
     * 接口文档信息（springdoc-openapi / knife4j）
     */
    @Bean
    public OpenAPI openAPI() {
        log.info("准备生成接口文档。。。");
        return new OpenAPI().info(new Info()
                .title("苍穹外卖项目接口文档")
                .description("苍穹外卖项目接口文档")
                .version("1.0"));
    }

    /**
     * 设置静态资源映射,http://localhost:8080/doc.html
     */
    @Override
    protected void addResourceHandlers(ResourceHandlerRegistry registry) {
        log.info("开始设置静态资源映射......");
        registry.addResourceHandler("/doc.html").addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
    }
}
