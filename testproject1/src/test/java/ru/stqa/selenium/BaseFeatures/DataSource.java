package ru.stqa.selenium.BaseFeatures;


import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface DataSource {

    enum Type {
        AUTO,
        FILE,
        EXCEL
    }

    String value() default "";
    Type type();

}