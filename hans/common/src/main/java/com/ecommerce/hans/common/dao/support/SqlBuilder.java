package com.ecommerce.hans.common.dao.support;

import com.ecommerce.hans.common.utils.BeanCopyUtils;
import com.ecommerce.hans.common.utils.EscapeUtils;
import com.ecommerce.hans.common.utils.StringCaseUtils;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.reflect.MethodUtils;
import org.hibernate.query.NativeQuery;
import org.hibernate.transform.AliasedTupleSubsetResultTransformer;
import org.hibernate.transform.ResultTransformer;
import org.hibernate.transform.Transformers;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.*;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public class SqlBuilder implements Cloneable{

    private StringBuffer sb = new StringBuffer();

    private List<Object> parameters = new LinkedList<>();

    private Map<String, Object> mapParameters = new HashMap<>();

    private SqlBuilder countSql;

    private String orderBy;

    private Pageable pageable;

    private boolean ignoreEmpty = false;

    private boolean toCamelCase = true;

    private boolean offsetAndFetch = false;

    public SqlBuilder() {
    }

    public SqlBuilder(SqlBuilder sqlBuilder) {
        this.sb = sb;
        this.parameters = new LinkedList<>(sqlBuilder.parameters);
        this.mapParameters = new HashMap<>(sqlBuilder.mapParameters);
    }

    private SqlBuilder appendBefore(String sql){
        sb.insert(0, sql);
        return this;
    }

    public SqlBuilder tap(Supplier<Boolean> supplier, Consumer<SqlBuilder> consumer){
        if(supplier.get()){
            consumer.accept(this);
        }
        return this;
    }

    public SqlBuilder tap(Consumer<SqlBuilder> consumer){
        consumer.accept(this);
        return this;
    }

    public SqlBuilder(String sql){
        this.sb.append(sql);
    }

    public SqlBuilder(StringBuffer sb){
        this.sb = sb;
    }

    public SqlBuilder append(String str){
        sb.append(' ');
        sb.append(str);
        sb.append(' ');
        return this;
    }

    public SqlBuilder append(String str, Object... params){
        this.append(str);
        if(params != null){
            Arrays.stream(params)
                    .forEach(parameters::add);
        }
        return this;
    }

    public SqlBuilder nativeSqlKey(){
        toCamelCase = false;
        return this;
    }

    public SqlBuilder ignoreEmpty(){
        this.ignoreEmpty = true;
        return this;
    }

    public SqlBuilder where(){
        sb.append(" where 1=1 ");
        return this;
    }

    public SqlBuilder isNotNull(String key){
        sb.append(String.format(" and %s is not null ", key));
        return this;
    }

    public SqlBuilder setParameter(Map<String, Object> map){
        this.mapParameters = map;
        return this;
    }

    public SqlBuilder eq(String key, Object value){
        if(value == null){
            return this;
        }
        if(ignoreEmpty && StringUtils.isEmpty(value.toString())){
            return this;
        }
        sb.append(String.format(" and %s = ? ", key));

        parameters.add(value);
        return this;
    }

    public SqlBuilder ne(String key, Object value){
        if(value == null){
            return this;
        }
        if(ignoreEmpty && StringUtils.isEmpty(value.toString())){
            return this;
        }

        sb.append(String.format(" and %s != ? ",key));
        parameters.add(value);
        return this;
    }

    public SqlBuilder betweenFields(String loKey, String hiKey, Object value){
        if(value == null ) return this;

        sb.append(String.format(" and ? between %s and %s ", loKey, hiKey));
        parameters.add(value);
        return this;
    }

    public SqlBuilder lt(String key, Object value) {
        if (value == null) {
            return this;
        }
        sb.append(String.format(" and %s < ? ", key));
        parameters.add(value);
        return this;
    }

    public SqlBuilder le(String key, Object value) {
        if (value == null) {
            return this;
        }
        sb.append(String.format(" and %s <= ? ", key));
        parameters.add(value);
        return this;
    }

    public SqlBuilder gt(String key, Object value) {
        if (value == null) {
            return this;
        }
        sb.append(String.format(" and %s > ? ", key));
        parameters.add(value);
        return this;
    }

    public SqlBuilder ge(String key, Object value) {
        if (value == null) {
            return this;
        }
        sb.append(String.format(" and %s >= ? ", key));
        parameters.add(value);
        return this;
    }

    public SqlBuilder between(String key, Object lo, Object hi) {
        if (lo == null && hi == null) {
            return this;
        } else if (lo == null && hi != null) {
            return this.le(key, lo);
        } else if (lo != null && hi == null) {
            return this.ge(key, lo);
        } else {
            sb.append(String.format(" and %s between ? and ? ", key));
            parameters.add(lo);
            parameters.add(hi);
        }
        return this;
    }

    public SqlBuilder like(String key, String value, LikeMatcher matcher) {
        if (value == null) {
            return this;
        }
        if (ignoreEmpty && StringUtils.isEmpty(value.toString())) {
            return this;
        }

        sb.append(String.format(" and %s like ? ", key));
        parameters.add(matcher.apply(value));
        return this;
    }

    public SqlBuilder notIn(String key, Collection<?> values) {

        if (CollectionUtils.isEmpty(values)) {
            return this;
        }

        sb.append(String.format(" and %s not in (%s) ", key,
                values.stream()
                        .map(o -> "?")
                        .collect(Collectors.joining(","))));

        parameters.addAll(values);

        return this;
    }

    public SqlBuilder fetchOnlyOne(String orderBy) {
        this.offsetAndFetch = true;
        return this.offsetAndFetch(orderBy, 0, 1);
    }

    public SqlBuilder offsetAndFetch(String orderBy, int offset, int fetch) {
        this.offsetAndFetch = true;
        sb.append(String.format(" %s offset %s row fetch next %s row only ", orderBy, offset, fetch));

        return this;
    }

    public <T> SqlBuilder in(String key, T[] values) {

        if (values == null || values.length == 0) {
            return this;
        }

        sb.append(String.format(" and %s in (%s) ", key,
                Arrays.stream(values)
                        .map(o -> "?")
                        .collect(Collectors.joining(","))));

        for (Object value : values) {
            parameters.add(value);
        }

        return this;
    }

    public SqlBuilder in(String key, Collection<?> values) {

        if (CollectionUtils.isEmpty(values)) {
            return this;
        }

        sb.append(String.format(" and %s in (%s) ", key,
                values.stream()
                        .map(o -> "?")
                        .collect(Collectors.joining(","))));

        parameters.addAll(values);

        return this;
    }

    public SqlBuilder countSql(SqlBuilder countSql) {
        this.countSql = countSql;
        return this;
    }

    /**
     * msq sql server order by is required
     * @param orderBy
     * @param pageable
     * @return
     */
    public SqlBuilder page(String orderBy, Pageable pageable) {

        this.orderBy = orderBy;

        if (pageable.getSort() != null && pageable.getSort().isSorted()) {
            this.orderBy = " order by " +
                    pageable.getSort().stream()
                            .map(o -> o.getProperty() + " " + o.getDirection().name())
                            .collect(Collectors.joining(",")) + " ";
        }

        this.pageable = pageable;
        return this;
    }

    public String getSql(){
        return sb.toString();
    }

    public Query createQuery(EntityManager entityManager){
        try{
            final Query query = (Query) MethodUtils.invokeExactMethod(entityManager,
                    "createNativeQuery", getSql());

            // list or map
            if(!mapParameters.isEmpty()){
                mapParameters.entrySet().stream()
                        .forEach(e -> query.setParameter(e.getKey(), e.getValue()));
            }else{
                for(int i = 0; i < parameters.size(); i++){
                    query.setParameter(i+1, parameters.get(i));
                }
            }
            return query;

        } catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    public <T> Page<T> getPageResult(EntityManager entityManager, Class<T> clz){

        if( pageable != null && pageable != Pageable.unpaged()){
            int total=0;
            if(countSql == null){
                countSql = new SqlBuilder(this)
                        .appendBefore(" select count('a') from ( ")
                        .append("  as total_temp");
            }
            total = countSql.getTotalCount(entityManager);

            append(orderBy)
                    .append(String.format(" offset &s row ", pageable.getOffset()))
                    .append(String.format(" fetch next %s row only ", pageable.getPageSize()));
            return new PageImpl<T>(this.getResultList(entityManager, clz), pageable, total);
        } else{
            return new PageImpl<T>(this.getResultList(entityManager, clz));
        }

    }

    public Integer getTotalCount(EntityManager entityManager){
        Query query = createQuery(entityManager);
        Integer singleResult = (Integer) query.unwrap(NativeQuery.class).getSingleResult();

        return singleResult;
    }

    @SuppressWarnings("deprecation")
    public <T> NativeQuery<T> wrap(NativeQuery<T> query) {
        if (!mapParameters.isEmpty()) {
            mapParameters.entrySet().stream().forEach(e -> query.setParameter(e.getKey(), e.getValue()));
        } else {
            for (int i = 0 ; i < parameters.size() ; i++) {
                query.setParameter(i+1, parameters.get(i));
            }
        }
        query.setResultTransformer(new CompositeResultTransformer(toCamelCase));
        return query;
    }

    @SuppressWarnings({ "deprecation", "unchecked" })
    public <T> Optional<T> getSingleResult(EntityManager entityManager, Class<T> clz) {
        Query query = createQuery(entityManager);
        if (!this.offsetAndFetch) {
            query.setMaxResults(1);
        }
        query.unwrap(NativeQuery.class).setResultTransformer(
                new CompositeResultTransformer(clz));
        return EscapeUtils.escapeResultList(query.getResultList()).get().stream().findFirst();
    }

    @SuppressWarnings({ "deprecation", "unchecked" })
    public <T> List<T> getResultList(EntityManager entityManager, Class<T> clz) {
        Query query = createQuery(entityManager);
        query.unwrap(NativeQuery.class).setResultTransformer(new CompositeResultTransformer(clz));

        return EscapeUtils.escapeResultList(query.getResultList()).get();
    }

    @SuppressWarnings({ "deprecation", "unchecked" })
    public List<Map<String, Object>> getResultList(EntityManager entityManager) {
        Query query = createQuery(entityManager);
        query.unwrap(NativeQuery.class).setResultTransformer(
                new CompositeResultTransformer(toCamelCase));
        return EscapeUtils.escapeResultList(query.getResultList()).get();
    }

    @SuppressWarnings({ "deprecation", "unchecked" })
    public Optional<Map<String, Object>> getSingleResult(EntityManager entityManager) {
        Query query = createQuery(entityManager);
        if (!this.offsetAndFetch) {
            query.setMaxResults(1);
        }

        query.unwrap(NativeQuery.class).setResultTransformer(
                new CompositeResultTransformer(toCamelCase));
        return EscapeUtils.escapeResultList(query.getResultList()).get().stream().findFirst();
    }

    @SuppressWarnings("unchecked")
    public List<Map<String, Object>> getResultListWithAlias(EntityManager entityManager) {
        Query query = createQuery(entityManager);
        query.unwrap(NativeQuery.class).setResultTransformer(
                new WithAliasTransformer());
        return EscapeUtils.escapeResultList(query.getResultList()).get();
    }


    /**
     * For SQL - Like
     */

    public static enum LikeMatcher{
        EXACT(Function.identity()),
        STARTING(s -> '%' + s ),
        ENDING(s -> s + '%'),
        CONTAINING(s -> '%' + s + '%');

        private Function<String, String> fun;

        private LikeMatcher(Function<String, String> fun){
            this.fun = fun;
        }

        public String apply(String value){
            return fun.apply(value);
        }
    }

    public static class WithAliasTransformer extends AliasedTupleSubsetResultTransformer {

        @Override
        public boolean isTransformedValueATupleElement(String[] aliases, int tupleLength) {
            return true;
        }

        @Override
        public Object transformTuple(Object[] tuple, String[] aliases) {
            Map<String, Object> map = new HashMap<>();
            map.put("tuple", tuple);
            map.put("aliases", aliases);
            return map;
        }

    }

    public static class CompositeResultTransformer extends AliasedTupleSubsetResultTransformer{

        private static final long serialVersionUID = 1L;

        private ResultTransformer transformer = Transformers.ALIAS_TO_ENTITY_MAP;

        private Class<?> clz;

        private boolean toCamelCase = false;

        private String[] casedAliases;

        private boolean isInitialized = false;

        public CompositeResultTransformer() {}

        public CompositeResultTransformer(boolean toCamelCase){
            this.toCamelCase = toCamelCase;
        }

        public CompositeResultTransformer(Class<?> clz){
            this.toCamelCase = true;
            this.clz = clz;
        }

        @Override
        public boolean isTransformedValueATupleElement(String[] aliases, int tupleLength) {
            return true;
        }

        @Override
        public Object transformTuple(Object[] tuple, String[] aliases) {

            if (!this.isInitialized) {
                if (toCamelCase) {
                    this.casedAliases = Arrays.stream(aliases)
                            .map(StringCaseUtils::toCamcalCase)
                            .toArray(size -> new String[size]);
                } else {
                    this.casedAliases = aliases;
                }
                this.isInitialized = true;
            }

            Object[] casedTuple = Arrays.stream(tuple)
                    .map(t -> {

                        if (t instanceof Byte) {
                            return ((Byte) t).intValue();
                        }
                        return t;
                    })
                    .toArray(size -> new Object[size]);

            Object transformTuple = transformer.transformTuple(casedTuple, casedAliases);

            if (clz != null) {
                return BeanCopyUtils.populateMapToObj(clz)
                        .apply((Map<String, ? extends Object>) transformTuple);
            } else {
                return transformTuple;
            }
        }





    }


}
