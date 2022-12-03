package com.mcj.music.aspect;

import com.mcj.music.utils.AspectUtils;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;


/**
 * @author mcj
 * @date 2022/10/23 9:54
 * @email 2037612492@qq.com
 * @description 日志的切面类,为controller类中的每个方法添加日志
 */
@Aspect                // 定义切面
@Component
@Slf4j
public class LogAspect {

    /**
     * execution函数用于匹配方法执行的连接点，语法为：
     * execution(方法修饰符(可选)  返回类型  方法名  参数  异常模式(可选))
     * 参数部分允许使用通配符：
     * *  匹配任意字符，但只能匹配一个元素
     * .. 匹配任意字符，可以匹配任意多个元素，表示类时，必须和*联合使用
     * +  必须跟在类名后面，如Horseman+，表示类本身和继承或扩展指定类的所有类
     * 此处表示返回类型、参数不限，只要是com.mcj.music.controller包中的任何类中的任何方法
     */
    @Pointcut("execution(* com.mcj.music.controller.*.*(..))")      // 定义切点
    public void logPointcut(){}


    /**
     * 环绕通知，在执行每个controller类中的方法之后打印相关的日志
     * @param joinPoint
     * @return
     * @throws Throwable
     */
    @Around("logPointcut()")                                       // 进行环绕通知
    public Object doAround(ProceedingJoinPoint joinPoint) throws Throwable {
        String paramNameAndValue = AspectUtils.getParamNameAndValue(joinPoint);
        String methodName = AspectUtils.getMethod(joinPoint);
        Object proceed = joinPoint.proceed();
        log.info("当前请求方法：{}，当前请求参数名与参数：{}，当前请求结果：{}", methodName, paramNameAndValue, proceed);
        return proceed;
    }



}
