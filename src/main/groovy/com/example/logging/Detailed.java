package com.example.logging;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface Detailed {

    String name() default "";

    DetailType type();

    enum DetailType {
        API_PROVIDER,
        API_CONSUMER,
        DB_CLIENT,
        INTERNAL_REFERENCE
    }

}
