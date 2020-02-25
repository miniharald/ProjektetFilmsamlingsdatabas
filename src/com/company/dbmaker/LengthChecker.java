package com.company.dbmaker;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface LengthChecker {
    String name();
    int length();
    //int minValue() default 0;
    //int maxValue();
}
