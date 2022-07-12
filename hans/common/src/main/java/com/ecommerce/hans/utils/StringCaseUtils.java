package com.ecommerce.hans.utils;

import org.apache.commons.text.CaseUtils;

import java.util.HashMap;
import java.util.Map;

public class StringCaseUtils {

    private StringCaseUtils(){}

    public static Map<String, ?> toCamcalCaseKey(Map<String, ?> map){
        return map.entrySet().stream()
                .collect(HashMap::new,
                        (m, e) ->{
                            m.put(toCamcalCase(e.getKey()), e.getValue());
                        },
                        (l,r) ->{
                            l.putAll(r);
                        }
                        );
    }

    public static String toCamcalCase(String s){
        return CaseUtils.toCamelCase(s, false, '_', '-');
    }
}
