package com.wish.ws.timediff.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.time.format.DateTimeFormatter;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface DateFormatToApply {

    String dateFormat() default "yyyy-MM-dd'T'HH:mm:ssZ";

}
