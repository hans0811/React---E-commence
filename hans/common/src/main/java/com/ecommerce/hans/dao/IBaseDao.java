package com.ecommerce.hans.dao;

import com.ecommerce.hans.exception.DatabaseException;

import java.io.Serializable;
import java.util.List;

public interface IBaseDao<T> {

    /**
     * 依據Primary Key取得資料
     *
     * @param key
     * @return
     * @throws DatabaseException
     */
    public T findByKey(Serializable key) throws DatabaseException;

    /**
     * 取得全部資料
     *
     * @return
     * @throws Exception
     */
    public List<T> findAll() throws DatabaseException;

    /**
     * 依據Primary Key刪除資料
     *
     * @param key
     * @return
     * @throws DatabaseException
     */
    public void removeByKey(Serializable key) throws DatabaseException;

    /**
     * 刪除 entity
     *
     * @param entity
     * @throws DatabaseException
     * @throws Exception
     */
    public T remove(T entity) throws DatabaseException;

    /**
     * 刪除多筆 entitys
     *
     * @param entitys
     * @throws DatabaseException
     * @throws Exception
     */
    public List<T> remove(List<T> entitys) throws DatabaseException;

    /**
     * 移除所有資料
     * @return
     * @throws DatabaseException
     */
    public int removeAll() throws DatabaseException;


    /**
     * 更新 entity
     *
     * merge: update after select for detached entity
     *
     * @param entity
     * @throws DatabaseException
     */
    public T update(T entity) throws DatabaseException;

    /**
     * UPDATE Version EX
     *
     * Native update without pre-select
     *
     * @param entity
     * @throws DatabaseException
     */
    public T updateEx(T entity) throws DatabaseException;

    /**
     * 更新多筆Entity
     *
     * @param entities
     * @return
     * @throws DatabaseException
     */
    public List<T> update(List<T> entities) throws DatabaseException;

    /**
     * 新增一筆 entity
     *
     * @param entity
     * @return
     * @throws DatabaseException
     */
    public T insert(T entity) throws DatabaseException;

    /**
     * 新增一筆 entity (不 clear)
     *
     * @param entity
     * @return
     * @throws DatabaseException
     */
    public T insertWithoutClear(T entity) throws DatabaseException;

    /**
     * 新增多筆Entity
     *
     * @param entities
     * @return
     * @throws DatabaseException
     */
    public List<T> insert(List<T> entities) throws DatabaseException;

    /**
     * 新增或更新一筆 entity
     *
     * @param entity
     * @return
     * @throws DatabaseException
     */
    public T save(T entity) throws DatabaseException;

    /**
     * 新增或更新多筆Entity
     *
     * @param entities
     * @return
     * @throws DatabaseException
     */
    public List<T> save(List<T> entities) throws DatabaseException;

    /**
     * 是否包含此筆Entity
     *
     * @param key
     * @return
     * @throws DatabaseException
     */
    public boolean contains(Serializable key) throws DatabaseException;

    /**
     * 是否包含此筆Entity
     *
     * @param entity
     * @return
     * @throws DatabaseException
     */
    public boolean contains(T entity) throws DatabaseException;


    /**
     * <p>
     * 取得Sequence No
     * </p>
     *
     * 使用Oracle Sequence
     *
     * @return
     * @throws DatabaseException
     */
    public int getSeqNo(String sequenceName) throws DatabaseException;


}
