package com.mcj.music.aspect;


import com.mcj.music.annotation.CheckParam;
import com.mcj.music.annotation.EmailFormatCheck;
import com.mcj.music.utils.Consts;
import com.mcj.music.utils.ResponseUtils;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;

import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.lang.reflect.Parameter;

@Aspect
@Component
@Slf4j
public class ParamFormatCheckAspect {

    @Pointcut("@annotation(com.mcj.music.annotation.CheckParam)")
    public void checkParamPointcut(){}

    /**
     * 进行邮箱格式符合规则的校验
     * @param joinPoint
     */
    @Around("checkParamPointcut()")
    public Object doAround(ProceedingJoinPoint joinPoint) throws Throwable {
        // 获取方法的参数
        Object[] args = joinPoint.getArgs();

        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();

        Object proceed;

        CheckParam annotation = methodSignature.getMethod().getAnnotation(CheckParam.class);
        if (!annotation.isCheck()) {
            proceed = joinPoint.proceed();
            return proceed;
        }

        // 获取所有方法的参数名称
        Parameter[] parameters = methodSignature.getMethod().getParameters();
        // 遍历方法的参数名称
        for (int i = 0; i < parameters.length; i++) {
            Parameter parameter = parameters[i];

            // 获取参数类型
            Class<?> type = parameter.getType();

            // 判断该参数是一个String类型
            if (type == String.class) {
                EmailFormatCheck emailFormatCheck = parameter.getAnnotation(EmailFormatCheck.class);
                String arg = String.valueOf(args[i]);
                // 判断参数的值是否符合邮箱格式
                if (!arg.matches(Consts.EMAIL_FORMAT)) {
                    return ResponseUtils.fail(emailFormatCheck.msg());
                }
                continue;
            }

            // 判断是否是自定义类， classLoader等于null的时候不是自定义类
            if (type.getClassLoader() != null) {
                // 获取自定义对象的所有字段
                Field[] declaredFields = type.getDeclaredFields();
                Object arg = args[i];
                for (Field declaredField : declaredFields) {
                    // 判断该字段上是否有注解
                    if (declaredField.getAnnotation(EmailFormatCheck.class) != null) {
                        EmailFormatCheck emailFormatCheck = declaredField.getAnnotation(EmailFormatCheck.class);
                        if (declaredField.getType() == String.class) {
                            // 取消字段的安全访问检查，使能够对字段进行操作
                            declaredField.setAccessible(true);
                            // 获取该字段的值
                            String fieldValue = String.valueOf(declaredField.get(arg));
                            // 检查该字段是否符合邮箱的格式
                            if (!fieldValue.matches(Consts.EMAIL_FORMAT)) {
                                return ResponseUtils.fail(emailFormatCheck.msg());
                            }
                        } else {
                            return ResponseUtils.fail("参数类型不正确！");
                        }
                    }
                }
            }
        }
        proceed = joinPoint.proceed();
        return proceed;
    }

}
