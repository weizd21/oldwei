package top.oldwei.redis.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Target;

/***
 * @author weizd
 *
 * @date 2020.02.27
 */
@Target({ElementType.METHOD, ElementType.FIELD})
@Documented
public @interface Describe {

    String value() default "";

}
