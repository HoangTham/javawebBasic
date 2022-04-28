package com.example.basic.repository.impl;

import com.example.basic.domain.UserEntity;
import com.example.basic.dto.UserSearch;
import com.example.basic.repository.custom.UserRepositoryCustom;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class UserRepositoryImpl implements UserRepositoryCustom {

    @PersistenceContext
    private EntityManager entityManager;
    @Override
    public List<UserEntity> search(UserSearch search) {
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT u FROM UserEntity as u ");
        Map<String, Object> values = new HashMap<>();
        sql.append(createWhereQuery(search, values));
        Query query = entityManager.createQuery(sql.toString(), UserEntity.class);
        values.forEach(query::setParameter);
        return query.getResultList();
    }

    @Override
    public Long count(UserSearch search) {
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT COUNT(u) FROM UserEntity as u");
        Map<String, Object> values = new HashMap<>();
        sql.append(createWhereQuery(search, values));
        Query query = entityManager.createQuery(sql.toString(),Long.class);
        values.forEach((query::setParameter));
        return (Long) query.getSingleResult();
    }

    private String createWhereQuery(UserSearch search, Map<String, Object> values){
        StringBuilder sql = new StringBuilder(" where 1=1 ");
        if(Objects.nonNull(search.getFirstName()) && !search.getFirstName().isBlank()) {
            sql.append(" and u.firstName =:first ");
            values.put("first",search.getFirstName());
        }
        if(Objects.nonNull(search.getLastName()) && !search.getLastName().isBlank()) {
            sql.append(" and u.lastName =:last ");
            values.put("last",search.getLastName());
        }

        return sql.toString();
    }
}
