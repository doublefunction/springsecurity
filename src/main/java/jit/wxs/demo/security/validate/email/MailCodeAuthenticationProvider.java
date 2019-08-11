package jit.wxs.demo.security.validate.email;

import jit.wxs.demo.security.SecurityConstants;
import jit.wxs.demo.security.validate.mobile.SmsCodeAuthenticationToken;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * 邮箱登陆鉴权 Provider，要求实现 AuthenticationProvider 接口
 * @author jitwxs
 * @since 2019/1/9 13:59
 */
public class MailCodeAuthenticationProvider implements AuthenticationProvider {
    private UserDetailsService userDetailsService;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        MailCodeAuthenticationToken authenticationToken = (MailCodeAuthenticationToken) authentication;

        String email = (String) authenticationToken.getPrincipal();

        checkMailCode(email);

        UserDetails userDetails = userDetailsService.loadUserByUsername(email);

        // 此时鉴权成功后，应当重新 new 一个拥有鉴权的 authenticationResult 返回
       MailCodeAuthenticationToken authenticationResult = new MailCodeAuthenticationToken(userDetails, userDetails.getAuthorities());

        authenticationResult.setDetails(authenticationToken.getDetails());

        return authenticationResult;
    }

    private void checkMailCode(String email) {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String inputCode = request.getParameter(SecurityConstants.LOGIN_EMAIL_CODE_PARAMETER);

        Map<String, Object> emailCode = (Map<String, Object>) request.getSession().getAttribute("emailCode");
        if(emailCode == null) {
            throw new BadCredentialsException("未检测到申请验证码");
        }

        String applyMobile = (String) emailCode.get("email");
        int code = (int) emailCode.get("code");

        if(!applyMobile.equals(email)) {
            throw new BadCredentialsException("申请的邮箱与登录邮箱不一致");
        }
        if(code != Integer.parseInt(inputCode)) {
            throw new BadCredentialsException("验证码错误");
        }
    }

    @Override
    public boolean supports(Class<?> authentication) {
        // 判断 authentication 是不是 SmsCodeAuthenticationToken 的子类或子接口
        return MailCodeAuthenticationToken.class.isAssignableFrom(authentication);
    }

    public UserDetailsService getUserDetailsService() {
        return userDetailsService;
    }

    public void setUserDetailsService(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }
}
