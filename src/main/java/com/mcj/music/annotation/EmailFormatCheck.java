package com.mcj.music.annotation;

import java.lang.annotation.*;

/**
 * @author mcj
 * @date 2022/10/28 20:27
 * @email 2037612492@qq.com
 * @description 邮箱参数校验有关的自定义注解
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD, ElementType.PARAMETER})
public @interface EmailFormatCheck {

    // 当邮箱不符合规则时的提示信息
    String msg() default "请输入正确的邮箱格式！";

}
