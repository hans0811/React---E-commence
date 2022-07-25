package com.ecommerce.hans.common.support;

import org.apache.commons.lang3.reflect.MethodUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Component
public class DataRestQueryProvider {
    private static final String CUSTOM_DAO_POST_FIX = "DaoCustomImpl";

    private static final Logger logger = LoggerFactory.getLogger(DataRestQueryProvider.class);

    private Map<String, Function<NamedQueryParameter, Page<Map<String, Object>>>> functionCache =
            new HashMap<>();

    @Autowired
    private ConfigurableListableBeanFactory applicationContext;

    @PostConstruct
    public void afterPropertiesSet() throws Exception {
        String[] beanNamesForType = applicationContext.getBeanNamesForAnnotation(Repository.class);

        for (String beanName : beanNamesForType) {

            if (!beanName.endsWith(CUSTOM_DAO_POST_FIX)) {
                continue;
            }

            logger.debug("dao custom bean : {}", beanName);
            Object bean = applicationContext.getBean(beanName);

            BeanDefinition beanDefinition = applicationContext.getBeanDefinition(beanName);

            Class<?> clz = Class.forName(beanDefinition.getBeanClassName());

            for (Method method : clz.getDeclaredMethods()) {
                if (method.isAnnotationPresent(DataRestQuery.class)) {
                    if (method.getReturnType().equals(Function.class) &&
                            method.getParameterCount() == 0) {
//                        Object fun = MethodUtils.invokememethod.invoke(bean, new Object[] {});
                        Object fun = MethodUtils.invokeMethod(bean, method.getName());

                        this.functionCache.put(beanName.replace(CUSTOM_DAO_POST_FIX, "") + "." + method.getName(),
                                (Function<NamedQueryParameter, Page<Map<String, Object>>>) fun);
                    } else {
                        throw new IllegalArgumentException("data rest query usage error");
                    }
                }
            }
        }
        functionCache.keySet().stream().forEach(logger::debug);
    }

    public Function<NamedQueryParameter, Page<Map<String, Object>>> getFunction(String named) {
        return functionCache.get(named);
    }

    public static class NamedQueryParameter {
        private Map<String, Object> map;
        private Pageable pagable;

        private NamedQueryParameter(Map<String, Object> map, Pageable pagable) {
            this.map = map;
            this.pagable = pagable;
        }

        public static NamedQueryParameter of(Map<String, Object> map, Pageable pagable) {
            return new NamedQueryParameter(map, pagable);
        }

        /**
         * 取得 map
         *
         * @return 傳回 map。
         */
        public Map<String, Object> getMap() {
            return map;
        }

        /**
         * 取得 pagable
         *
         * @return 傳回 pagable。
         */
        public Pageable getPagable() {
            return pagable;
        }
    }
}
