package com.ecommerce.hans.dao;

import com.ecommerce.hans.exception.DatabaseException;
import com.ecommerce.hans.persistance.BaseRepo;
import net.bytebuddy.description.method.MethodDescription;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

import javax.persistence.EntityManager;
import javax.persistence.LockModeType;
import javax.persistence.PersistenceException;
import javax.persistence.Query;
import java.io.Serializable;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Optional;

public abstract class BaseDaoImp<T, K extends BaseRepo> implements IBaseDao<T> {

    //*********************************************
    // Local Variable
    //*********************************************
    /** Log */
    protected static org.apache.commons.logging.Log m_logger = LogFactory.getLog(BaseDaoImp.class);

    @Autowired
    private ApplicationContext context;

    @Autowired
    private EntityManager entityManager;

    @SuppressWarnings("serial")
    private final MethodDescription.TypeToken<K> typeToken = new MethodDescription.TypeToken<K>(getClass()) { };
    private final Type type = typeToken.getType();

    //*********************************************
    // getter & setter
    //*********************************************

    /**
     * Customer Repository
     * @return
     */
    public K getRepository(){
        String[] datas = type.getTypeName().split("\\.");
        String name = datas[datas.length - 1];

        // 如果有設定的話
        if(context.containsBean(name)){
            K repository = (K) context.getBean(name);
            return repository;
        }

        // 沒有設定的，就用首字小寫的名稱
        name = name.substring(0, 1).toLowerCase() + name.substring(1);
        K repository = (K) context.getBean(name);
        return repository;
    }

    /**
     * 建立一個SQL查詢
     * @param sql
     * @return
     * @throws DatabaseException
     */
    public JPAQuery createQuery(String sql) throws DatabaseException {
        JPAQuery jpaQuery = null;

        try {
            Query query = entityManager.createQuery(sql);
            jpaQuery = new JPAQuery(entityManager, sql, query);
        }
        catch (PersistenceException e) {
            throw e;
        }
        catch(Exception e) {
            throw new DatabaseException("failed to create query", e);
        }
        return jpaQuery;
    }

    /**
     * 建立一個上悲觀鎖的SQL查詢, 使用本方法需要包裝@Transactional. 最長 8秒. 要包transcation
     * @param sql
     * @return
     * @throws DatabaseException
     */
    public JPAQuery createLockQuery(String sql) throws DatabaseException {
        JPAQuery jpaQuery = null;
        try {
            Query query = entityManager.createQuery(sql);
            query.setLockMode(LockModeType.PESSIMISTIC_READ);
            query.setHint("javax.persistence.query.timeout", "8000"); //8秒
            jpaQuery = new JPAQuery(entityManager, sql, query);
        } catch (PersistenceException e) {
            throw e;
        } catch(Exception e) {
            throw new DatabaseException("failed to create query", e);
        }
        return jpaQuery;
    }

    /**
     * 建立一個SQL查詢
     * @param sql
     * @param resultClass
     * @return
     * @throws DatabaseException
     */
    public JPAQuery createQuery(String sql, Class resultClass) throws DatabaseException {
        JPAQuery jpaQuery = null;
        try {
            Query query = entityManager.createQuery(sql, resultClass);
            jpaQuery = new JPAQuery(entityManager, sql, query);
        }
        catch (PersistenceException e) {
            throw e;
        }
        catch(Exception e) {
            throw new DatabaseException("failed to create query", e);
        }
        return jpaQuery;
    }

    /**
     *
     * @param sb
     * @return
     * @throws DatabaseException
     */
    public JPAQuery createQuery(StringBuilder sb) throws DatabaseException {
        return createQuery(sb.toString());
    }

    /**
     *
     * @param sql
     * @return
     * @throws DatabaseException
     */
    public JPAQuery createNativeQuery(String sql) throws DatabaseException {

        JPAQuery jpaQuery = null;

        try {
            Query query = entityManager.createNativeQuery(sql);
            jpaQuery = new JPAQuery(entityManager, sql, query);
        }
        catch (PersistenceException e) {
            throw e;
        }
        catch(Exception e) {
            throw new DatabaseException("failed to create query", e);
        }
        return jpaQuery;
    }

    public JPAQuery createNativeQuery(String sql, Class resultClass) throws DatabaseException {

        JPAQuery jpaQuery = null;

        try {
            Query query = entityManager.createNativeQuery(sql, resultClass);
            jpaQuery = new JPAQuery(entityManager, sql, query);
        }
        catch (PersistenceException e) {
            throw e;
        }
        catch(Exception e) {
            throw new DatabaseException("failed to create query", e);
        }
        return jpaQuery;
    }

    /**
     *
     * @param key
     * @return
     * @throws DatabaseException
     */
    @Override
    public T findByKey(Serializable key) throws DatabaseException {
        // modify by alex 20180626: findone -> findById
        Optional<T> record = getRepository().findById(key);
        if(record.isPresent()){
            return record.get();
        }

        return null;
    }

    @Override
    public List<T> findAll() throws DatabaseException {
        return (List<T>) getRepository().findAll();
    }

    @Override
    public void removeByKey(Serializable key) throws DatabaseException {
        getRepository().delete(key);
    }

    @Override
    public T remove(T entity) throws DatabaseException {
        getRepository().delete(entity);
        return entity;
    }


    @Override
    public List<T> remove(List<T> entitys) throws DatabaseException {
        getRepository().deleteAll(entitys);
        return entitys;
    }


    @Override
    public int removeAll() throws DatabaseException {
        int count = (int) getRepository().count();
        getRepository().deleteAll();
        return count;
    }


    @Override
    public T update(T entity) throws DatabaseException {
        getRepository().save(entity);
        return entity;
    }


    @Override
    public T updateEx(T entity) throws DatabaseException {
        getRepository().save(entity);
        return entity;
    }


    @Override
    public List<T> update(List<T> entities) throws DatabaseException {
        getRepository().saveAll(entities);
        return entities;
    }


    @Override
    public T insert(T entity) throws DatabaseException {
        getRepository().save(entity);
        return entity;
    }


    @Override
    public T insertWithoutClear(T entity) throws DatabaseException {
        getRepository().save(entity);
        return entity;
    }


    @Override
    public List<T> insert(List<T> entities) throws DatabaseException {
        getRepository().saveAll(entities);
        return entities;
    }

    @Override
    public T save(T entity) throws DatabaseException {
        getRepository().save(entity);
        return entity;
    }

    @Override
    public List<T> save(List<T> entities) throws DatabaseException {
        getRepository().saveAll(entities);
        return entities;
    }


    /**
     *
     * @param entity
     * @return
     * @throws DatabaseException
     */
    @Override
    public boolean contains(Serializable key) throws DatabaseException {
        // modify by alex : 20180626 exists -> existsById
        return getRepository().existsById(key);
    }

    @Override
    public boolean contains(T entity) throws DatabaseException {
        // TODO Auto-generated method stub
        return false;
    }


    /**
     * 取得序號
     * @param sequenceName
     * @return
     * @throws DatabaseException
     */
    @Override
    public int getSeqNo(String sequenceName) throws DatabaseException {
        return 0;
    }


}
