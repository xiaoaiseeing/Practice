package com.tplink.aop;

import org.apache.log4j.PropertyConfigurator;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.Calendar;
import java.util.TimeZone;
import com.tplink.constant.NumConstant;
import com.tplink.constant.StringConstant;
import com.tplink.entity.LoginNumber;
import com.tplink.repository.LoginRepository;

/**
 * @author ZouYun
 * @version 1.0
 * @since 2020/8/25 10:22
 */
@Aspect
@Component
public class LoginAop {
    @Autowired
    private LoginRepository loginRepository;

    /**
     * 给登录方法加上环绕通知
     */
    @Pointcut(StringConstant.AOP_PATH)
    public void pointCut() {}

    @Around(StringConstant.AOP_POINT)
    public Object around(ProceedingJoinPoint proceedingJoinPoint) {
        Object result = null;
        Object[] args = proceedingJoinPoint.getArgs();
        String name = (String) args[NumConstant.ZERO];
        Logger logger = LoggerFactory.getLogger(LoginAop.class);
        PropertyConfigurator.configure(StringConstant.PROPERTIES_PATH);
        try {
            Calendar calendar = Calendar.getInstance();
            calendar.setTimeZone(TimeZone.getTimeZone(StringConstant.UTC));
            result = proceedingJoinPoint.proceed();
            String sign = (String) result;
            if (sign.equals(StringConstant.SUCCESS_SIGN)) {
                long loginTime = calendar.getTimeInMillis();
                LoginNumber loginNumber = new LoginNumber(name, loginTime);
                loginRepository.save(loginNumber);
            }
        } catch (Throwable throwable) {
            logger.error(StringConstant.AOP_EXCEPTION_MESSAGE, throwable);
        }
        return result;
    }
}
