package jit.wxs.demo.security.validate.email;

import jit.wxs.demo.security.authentication.DefaultAuthenticationFailureHandler;
import jit.wxs.demo.security.authentication.DefaultAuthenticationSuccessHandler;
import jit.wxs.demo.security.authentication.EmailUserDetailsService;
import jit.wxs.demo.security.authentication.MobileUserDetailsService;
import jit.wxs.demo.security.validate.email.MailCodeAuthenticationFilter;
import jit.wxs.demo.security.validate.mobile.SmsCodeAuthenticationFilter;
import jit.wxs.demo.security.validate.mobile.SmsCodeAuthenticationProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;

@Component
public class MailCodeAuthenticationSecurityConfig extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {
    @Autowired
    private EmailUserDetailsService userDetailsService;
    @Autowired
    private DefaultAuthenticationSuccessHandler successHandler;
    @Autowired
    private DefaultAuthenticationFailureHandler failureHandler;

    @Override
    public void configure(HttpSecurity http) throws Exception {
        MailCodeAuthenticationFilter mailCodeAuthenticationFilter = new MailCodeAuthenticationFilter();
        mailCodeAuthenticationFilter.setAuthenticationManager(http.getSharedObject(AuthenticationManager.class));
        mailCodeAuthenticationFilter.setAuthenticationSuccessHandler(successHandler);
        mailCodeAuthenticationFilter.setAuthenticationFailureHandler(failureHandler);

        MailCodeAuthenticationProvider mailCodeAuthenticationProvider = new MailCodeAuthenticationProvider();
        mailCodeAuthenticationProvider.setUserDetailsService(userDetailsService);

        http.authenticationProvider(mailCodeAuthenticationProvider)
                .addFilterAfter(mailCodeAuthenticationFilter, SmsCodeAuthenticationFilter.class);
    }
}
