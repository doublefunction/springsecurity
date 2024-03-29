package jit.wxs.demo;

import jit.wxs.demo.security.SecurityConstants;
import jit.wxs.demo.security.validate.code.VerifyServlet;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

@SpringBootApplication
@EnableRedisHttpSession
public class SecurityDemo02Application {

    public static void main(String[] args) {
        SpringApplication.run(SecurityDemo02Application.class, args);
    }

    /**
     * 注入验证码servlet
     */
    @Bean
    public ServletRegistrationBean indexServletRegistration() {
        ServletRegistrationBean registration = new ServletRegistrationBean(new VerifyServlet());
        registration.addUrlMappings(SecurityConstants.VALIDATE_CODE_PIC_URL);
        return registration;
    }
}
