package com.cdyfsz.base.biz.svc.zhongdeng.common.util;

import club.newepoch.base.project.exceptions.exceptions.business.BizException;
import com.cdyfsz.base.biz.svc.zhongdeng.api.AmountOfMoney;
import org.apache.commons.lang3.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author LiBo
 * @date 2019/11/30
 */
public class AmountOfMoneyValidator implements ConstraintValidator<AmountOfMoney, String> {
    private String pattern1 = "^-?(([1-9][0-9]*)|(([0]\\.\\d{1,";
    private String pattern2 = "}|[1-9][0-9]*\\.\\d{1,";
    private String pattern3 = "}|[1-9][0-9]*\\.\\d{1,";


    AmountOfMoney amountOfMoney;

    @Override
    public void initialize(AmountOfMoney constraintAnnotation) {
        this.amountOfMoney = constraintAnnotation;
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (StringUtils.isNotBlank(value) && !isMoney(value, pattern1 + amountOfMoney.digitRange() + pattern2 + amountOfMoney.digitRange() + pattern3)) {
            throw new BizException("");
        }
        return true;
    }

    public boolean isMoney(String str, String pattern) {
        if (StringUtils.isNotEmpty(str) && StringUtils.isNotEmpty(pattern)) {
            Pattern p = Pattern.compile(pattern);
            Matcher m = p.matcher(str);
            return m.matches();
        }
        return false;
    }
}
