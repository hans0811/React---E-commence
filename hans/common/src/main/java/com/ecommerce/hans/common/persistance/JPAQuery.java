package com.ecommerce.hans.common.persistance;

import com.ecommerce.hans.common.exception.DatabaseException;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class JPAQuery{

    private static final int MAXRESULT = 50000;

    private Query query = null;

    private String sqlString = "";

    private EntityManager entityManager = null;

    private Integer maxResults = null;

    public JPAQuery(EntityManager entityManager, String sqlString, Query query) {
        this.query = query;
        this.sqlString = sqlString;
        this.entityManager = entityManager;
    }

    public int executeUpdate() throws DatabaseException {
        int rows = 0;

        try {
            rows = query.executeUpdate();
        }
        catch (Exception e) {
            throw new DatabaseException("cannot execute update, sql = " + sqlString, e);
        }

        return rows;
    }


    /**
     * @return
     * @throws DatabaseException
     * @see javax.persistence.Query#getResultList()
     */

    @SuppressWarnings("unchecked")
    public <T> List<T> getResultList() throws DatabaseException {
        List<T> list = null;
        try {
            // 改為有指定 max return result 才設定
            if (this.maxResults != null && this.maxResults > 0) {
                query.setMaxResults(Math.min(this.maxResults, MAXRESULT));
            }
            list = (List<T>) query.getResultList();
        } catch (Exception e) {
            throw new DatabaseException("Cannot get result list, sql = " + sqlString, e);
        } finally {
            entityManager.clear();
        }

        return (list != null) ? list : new ArrayList<>();
    }

    /**
     * @return
     * @throws DatabaseException
     * @see javax.persistence.Query#getSingleResult()
     */
    public Object getSingleResult() throws DatabaseException {
        Object entity = null;

        try {
            entity = query.getSingleResult();
        }
        catch (NoResultException e) {
            // 'entity = null;'
        }
        catch (Exception e) {
            throw new DatabaseException("Cannot get single result, sql = " + sqlString, e);
        }
        finally {
            entityManager.clear();
        }

        return entity;
    }

    /**
     * @param startPosition
     * @return
     * @see javax.persistence.Query#setFirstResult(int)
     */
    public Query setFirstResult(int startPosition) {
        return query.setFirstResult(startPosition);
    }

    /**
     * @param flushModeType
     * @return
     * @see javax.persistence.Query#setFlushMode(javax.persistence.FlushModeType)
     */
    public Query setFlushMode(FlushModeType flushModeType) {
        return query.setFlushMode(flushModeType);
    }

    /**
     * @param hintName
     * @param value
     * @return
     * @see javax.persistence.Query#setHint(java.lang.String, java.lang.Object)
     */
    public Query setHint(String hintName, Object value) {
        return query.setHint(hintName, value);
    }

    /**
     * @param position
     * @param value
     * @param temporalType
     * @return
     * @see javax.persistence.Query#setParameter(int, java.util.Calendar,
     *      javax.persistence.TemporalType)
     */
    public Query setParameter(int position, Calendar value, TemporalType temporalType) {
        return query.setParameter(position, value, temporalType);
    }

    /**
     * @param position
     * @param value
     * @param temporalType
     * @return
     * @see javax.persistence.Query#setParameter(int, java.util.Date,
     *      javax.persistence.TemporalType)
     */
    public Query setParameter(int position, Date value, TemporalType temporalType) {
        return query.setParameter(position, value, temporalType);
    }

    /**
     * @param position
     * @param value
     * @return
     * @see javax.persistence.Query#setParameter(int, int)
     */
    public Query setParameter(int position, int value) {
        return query.setParameter(position, Integer.valueOf(value));
    }

    /**
     * @param position
     * @param value
     * @return
     * @see javax.persistence.Query#setParameter(int, java.lang.Object)
     */
    public Query setParameter(int position, Object value) {
        return query.setParameter(position, value);
    }

    /**
     * @param name
     * @param value
     * @param temporalType
     * @return
     * @see javax.persistence.Query#setParameter(java.lang.String,
     *      java.util.Calendar, javax.persistence.TemporalType)
     */
    public Query setParameter(String name, Calendar value, TemporalType temporalType) {
        return query.setParameter(name, value, temporalType);
    }

    /**
     * @param name
     * @param value
     * @param temporalType
     * @return
     * @see javax.persistence.Query#setParameter(java.lang.String,
     *      java.util.Date, javax.persistence.TemporalType)
     */
    public Query setParameter(String name, Date value, TemporalType temporalType) {
        return query.setParameter(name, value, temporalType);
    }

    /**
     * @param name
     * @param value
     * @return
     * @see javax.persistence.Query#setParameter(java.lang.String,
     *      java.lang.Object)
     */
    public Query setParameter(String name, Object value) {
        return query.setParameter(name, value);
    }

    /**
     * 取得 maxResult
     *
     * @return 傳回 maxResult。
     */
    public Integer getMaxResults() {
        return maxResults;
    }

    /**
     * 設定 maxResult
     *
     * @param maxResult 要設定的 maxResult。
     */
    public void setMaxResults(Integer maxResult) {
        this.maxResults = maxResult;
    }



}
