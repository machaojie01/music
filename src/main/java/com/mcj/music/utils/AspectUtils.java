package com.mcj.music.utils;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.reflect.CodeSignature;

import java.util.HashMap;
import java.util.Map;

/**
 * @author mcj
 * @date 2022/10/23 10:15
 * @email 2037612492@qq.com
 * @description
 */
@Slf4j
public class AspectUtils {

    /**
     * 获取当前切点的参数名称与值
     * @param joinPoint
     * @return
     */
    public static String getParamNameAndValue(ProceedingJoinPoint joinPoint) {
        Map<String, Object> paramNameAndValueMap = new HashMap<>(8);
        Object[] args = joinPoint.getArgs();
        String[] parameterNames = ((CodeSignature) joinPoint.getSignature()).getParameterNames();
        for (int i = 0; i < parameterNames.length; i++) {
            paramNameAndValueMap.put(parameterNames[i], args[i]);
        }
        return JSONObject.toJSONString(paramNameAndValueMap);
    }

    /**
     * 获取当前切点的全限定方法名
     * @param joinPoint
     * @return
     */
    public static String getMethod(ProceedingJoinPoint joinPoint) {
        // 获取当前切点的全限定类型
        String declaringTypeName = joinPoint.getSignature().getDeclaringTypeName();
        // 获取当前切点的方法名
        String name = joinPoint.getSignature().getName();
        return declaringTypeName + "." + name;
    }


}
