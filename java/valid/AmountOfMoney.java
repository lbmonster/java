package com.cdyfsz.base.biz.svc.zhongdeng.common.util;

import com.cdyfsz.base.biz.svc.zhongdeng.api.AmountOfMoneyValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

/**
 * @ClassName jiee
 * @Author LiBo
 * @Date 2019/11/30
 */
@Target({ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE, ElementType.CONSTRUCTOR, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {AmountOfMoneyValidator.class})
@Documented
public @interface AmountOfMoney {

    String message() default "{size.error.message}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
    /**
     * 最多保留的小数位数
     *
     * @return int
     * @date 2019/11/30
     */
    int digitRange() default Integer.MAX_VALUE;

}
