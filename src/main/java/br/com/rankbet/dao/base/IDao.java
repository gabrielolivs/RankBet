package br.com.rankbet.dao.base;

import br.com.rankbet.exception.BusinessException;
import br.com.rankbet.exception.ConstraintViolationException;
import org.hibernate.Session;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;

interface IDao<T> {

    void save(T t) throws BusinessException;

    void delete(T t) throws BusinessException, ConstraintViolationException;

    T find(T t);

    List<T> findAll();

    Session getSession();

    void closeSession();

    void beginTransaction();

    void commit();

    void rollback();

    T findById(long id);

    List<T> paginate(T filter, int pageSize, int page);

    int count(T filter);

    List<Predicate> makeExample(T filter, CriteriaBuilder criteriaBuilder, Root<T> from);
}
