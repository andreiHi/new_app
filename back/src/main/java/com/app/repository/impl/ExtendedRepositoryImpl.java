package com.app.repository.impl;

import com.app.repository.ExtendedRepository;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.beans.PropertyDescriptor;
import java.io.Serializable;
import java.util.List;

/**
 * @author Hincu Andrei (andreih1981@gmail.com)on 22.04.2019.
 * @version $Id$.
 * @since 0.1.
 */
@NoRepositoryBean
public class ExtendedRepositoryImpl<T, ID extends Serializable> extends SimpleJpaRepository<T, ID> implements ExtendedRepository<T, ID> {

    private EntityManager entityManager;

    public ExtendedRepositoryImpl(JpaEntityInformation<T, ?> entityInformation, EntityManager entityManager) {
        super(entityInformation, entityManager);
        this.entityManager = entityManager;
    }

    @Override
    @Transactional
    public List<T> findByAttributeContainsText(String attributeName, String text) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<T> query = builder.createQuery(getDomainClass());
        Root<T> root = query.from(getDomainClass());
        query.select(root).where(builder.like(root.get(attributeName), "%" + text + "%"));
        TypedQuery<T> typedQuery = entityManager.createQuery(query);
        return typedQuery.getResultList();
    }

    @Override
    @Transactional
    public T updateWith(T with, ID id) {
        T persist = entityManager.find(getDomainClass(), id);
        if (persist != null) {
            final BeanWrapperImpl sourceBean = new BeanWrapperImpl(with);
            final BeanWrapperImpl destBean = new BeanWrapperImpl(persist);
            final PropertyDescriptor[] propertyDescriptors = sourceBean.getPropertyDescriptors();
            for (PropertyDescriptor propertyDescriptor : propertyDescriptors) {
                final Object pv = sourceBean.getPropertyValue(propertyDescriptor.getName());
                if (pv != null && destBean.isWritableProperty(propertyDescriptor.getName())) {
                    destBean.setPropertyValue(propertyDescriptor.getName(), pv);
                }
            }
            entityManager.persist(persist);
        }
        return persist;
    }
}
