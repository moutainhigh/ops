package com.jyblife.logic.wl.ops.common.utils;


import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.groups.Default;
import java.util.*;

/**
 * 对象验证工具
 *
 * @author yangcey
 */
public class ValidatorUtil {

    private static Validator validator = Validation.buildDefaultValidatorFactory()
            .getValidator();


    public static <T> List<String> validate(T t) {
        List<String> errorResult = new ArrayList<String>();
        Set<ConstraintViolation<T>> set = validator.validate(t, Default.class);
        if (set != null && set.size() > 0) {
            for (ConstraintViolation<T> cv : set) {
                errorResult.add(cv.getMessage());
            }
        }
        return errorResult;
    }


    public static <T> Map<String, List<String>> validateDetail(T obj) {
        Map<String, List<String>> errorMap = null;
        Set<ConstraintViolation<T>> set = validator.validate(obj, Default.class);
        if (set != null && set.size() > 0) {
            errorMap = new HashMap<String, List<String>>();
            String property = null;
            for (ConstraintViolation<T> cv : set) {
                property = StrUtils.camel2Underline(cv.getPropertyPath().toString());
                if (errorMap.get(property) != null) {
                    errorMap.get(property).add(cv.getMessage());
                } else {
                    List<String> sb = new ArrayList<String>();
                    sb.add(cv.getMessage());
                    errorMap.put(property, sb);
                }
            }
        }
        return errorMap;
    }


}