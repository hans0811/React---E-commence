package com.ecommerce.hans.utils;

import java.util.List;
import java.util.function.Supplier;

public class EscapeUtils {
    public EscapeUtils() {
    }

    public static String escapSql(String sql){
        return sql;
    }

    public static Supplier<List> escapeResultList(List result){
        return () -> result;
    }
}
