package com.ecommerce.hans.common.persistance;

import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.io.Serializable;

@NoRepositoryBean
public interface BaseRepo<T, ID extends Serializable> extends PagingAndSortingRepository<T, ID> {
}