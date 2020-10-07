package pers.tuershen.nbtedit.function.annotation;

import pers.tuershen.nbtedit.function.handle.TagType;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface TagParameter {

    TagType TYPE();


}
