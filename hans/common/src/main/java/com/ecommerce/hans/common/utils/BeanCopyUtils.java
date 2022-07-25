package com.ecommerce.hans.common.utils;

import com.ecommerce.hans.common.exception.RuntimeServiceException;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.converters.BigDecimalConverter;
import org.apache.commons.beanutils.converters.DateConverter;
import org.apache.commons.beanutils.converters.LongConverter;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.objenesis.instantiator.util.ClassUtils;

import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

public class BeanCopyUtils {
    private BeanCopyUtils() {}

    static {
        registerConverter();
    }

    private static void registerConverter() {
        ConvertUtils.register(new DateConverter(null), Date.class);
        ConvertUtils.register(new BigDecimalConverter(null), BigDecimal.class);
        ConvertUtils.register(new LongConverter(null), Long.class);
    }

    public static <T> Function<Object, T> convertObjectTo(Class<T> clz) {
        return (source) -> {
            T target = ClassUtils.newInstance(clz);
            return copyProperties(source, target, clz);
        };
    }

    public static <T> T populateMapToObject(Map<String, ? extends Object> map, Class<T> clz) {
        T target;
        try {
            target = ClassUtils.newInstance(clz);

            try {
                org.apache.commons.beanutils.BeanUtils.populate(target, map);
            } catch (org.apache.commons.beanutils.ConversionException e) {
                registerConverter();
                org.apache.commons.beanutils.BeanUtils.populate(target, map);
            }
        } catch (IllegalAccessException | InvocationTargetException e) {
            throw new RuntimeServiceException(e);
        }
        return target;
    }

    public static <T> Function<Map<String, ? extends Object>, T> populateMapToObj(Class<T> clz) {
        return (map) -> {
            return populateMapToObject(map, clz);
        };
    }

    public static <T> Function<Object, T> copyPropertiesFun(T target, Class<T> clz) {
        return (source) -> copyProperties(source, target, clz);
    }

    /**
     * copy properties without id
     * @param source
     * @param target
     * @return
     */
    public static void copyPropertiesIgnoreId(Object source, Object target) {
        BeanUtils.copyProperties(source, target, "id");
    }

    public static <T> T copyPropertiesIgnoreId(Object source, T target, Class<T> clz) {
        copyPropertiesIgnoreId(source, target);
        return target;
    }

    public static <T> T copyProperties(Object source, T target, Class<T> clz, String... ignoreProperties) {
        BeanUtils.copyProperties(source, target, ignoreProperties);
        return target;
    }

    public static void copyProperties(Object source, Object target, String... ignoreProperties) {
        BeanUtils.copyProperties(source, target, ignoreProperties);
    }

    public static <T> T copyPropertiesIgnoreNull(Object source, T target) {
        copyProperties(source, target, getNullProperties(source));
        return target;
    }

    public static void setProperty(final Object bean, final String name, final Object value) {
        try {
            org.apache.commons.beanutils.BeanUtils.setProperty(bean, name, value);
        } catch (IllegalAccessException | InvocationTargetException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 取得物件中所有 null 欄位名稱
     *
     * @param source DTO 物件
     * @return null 欄位名稱陣列
     */
    public static String[] getNullProperties(Object source) {
        BeanWrapper beanWrapper = new BeanWrapperImpl(source);
        PropertyDescriptor[] propertyDescriptors = beanWrapper.getPropertyDescriptors();
        List<String> nullPropertyName = new LinkedList<String>();

        for(PropertyDescriptor propertyDescriptor:propertyDescriptors) {
            String propertiesName = propertyDescriptor.getName();

            if (beanWrapper.getPropertyValue(propertiesName) == null) {
                nullPropertyName.add(propertiesName);
            }
        }

        return nullPropertyName.toArray(new String[nullPropertyName.size()]);
    }
}
