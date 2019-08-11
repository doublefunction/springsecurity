package jit.wxs.demo.security.validate.email;

import jit.wxs.demo.security.SecurityConstants;
import jit.wxs.demo.security.validate.mobile.SmsCodeAuthenticationToken;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.util.Assert;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *  邮箱登录的鉴权过滤器，模仿 UsernamePasswordAuthenticationFilter 实现
 * @author jitwxs
 * @since 2019/1/9 13:52
 */
public class MailCodeAuthenticationFilter extends AbstractAuthenticationProcessingFilter {
    /**
     * form表单中手机号码的字段name
     */
    public static final String SPRING_SECURITY_FORM_EMAIL_KEY = SecurityConstants.LOGIN_EMAIL_PARAMETER;

    private String emailParameter = SPRING_SECURITY_FORM_EMAIL_KEY;
    /**
     * 是否仅 POST 方式
     */
    private boolean postOnly = true;

    public MailCodeAuthenticationFilter() {
        // 短信登录的请求
        super(new AntPathRequestMatcher(SecurityConstants.LOGIN_PROCESSING_URL_EMAIL, "POST"));
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        if (postOnly && !request.getMethod().equals("POST")) {
            throw new AuthenticationServiceException(
                    "Authentication method not supported: " + request.getMethod());
        }

        String email = obtainemail(request);

        if (email == null) {
            email = "";
        }

        email = email.trim();

        MailCodeAuthenticationToken authRequest = new MailCodeAuthenticationToken(email);

        // Allow subclasses to set the "details" property
        setDetails(request, authRequest);

        return this.getAuthenticationManager().authenticate(authRequest);
    }

    protected String obtainemail(HttpServletRequest request) {
        return request.getParameter(emailParameter);
    }

    protected void setDetails(HttpServletRequest request, MailCodeAuthenticationToken authRequest) {
        authRequest.setDetails(authenticationDetailsSource.buildDetails(request));
    }

    public String getEmailParameter() {
        return emailParameter;
    }

    public void setEmailParameter(String emailParameter) {
        Assert.hasText(emailParameter, "Email parameter must not be empty or null");
        this.emailParameter = emailParameter;
    }

    public void setPostOnly(boolean postOnly) {
        this.postOnly = postOnly;
    }
}
