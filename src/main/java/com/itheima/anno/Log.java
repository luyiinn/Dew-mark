package com.itheima.anno;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Method;

@Retention(RetentionPolicy.RUNTIME) // 指定注解什么时候生效（运行时生效）
@Target(ElementType.METHOD) // 指定在方法上执行
public @interface Log {
}
