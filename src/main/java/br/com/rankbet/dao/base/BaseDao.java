package br.com.rankbet.dao.base;

import br.com.rankbet.hibernate.HibernateSessionFactory;

import br.com.rankbet.exception.BusinessException;
import br.com.rankbet.exception.ConstraintViolationException;
import org.apache.commons.lang3.reflect.FieldUtils;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import static java.lang.reflect.Modifier.isStatic;

@SuppressWarnings("DuplicatedCode")
public class BaseDao<T> implements IDao<T> {

    private final SessionFactory sessionFactory = HibernateSessionFactory.getSessionFactory();
    private Session session;
    protected final Class<T> persistentClass;

    public BaseDao(Class<T> persistentClass) {
        this.persistentClass = persistentClass;
    }

    @Override
    public void save(T t) throws BusinessException {
        this.beginTransaction();
        try {
            this.getSession().merge(t);
            this.commit();
        } catch (Exception e) {
            this.rollback();
            throw new BusinessException(e.getMessage(), e.getCause());
        } finally {
            this.closeSession();
        }
    }

    @Override
    public void delete(T t) throws BusinessException, ConstraintViolationException {
        this.beginTransaction();
        try {
            this.getSession().delete(t);
            this.commit();
        } catch (PersistenceException e) {

            this.rollback();
            throw new ConstraintViolationException(e.getMessage(), e.getCause());
        } catch (Exception e) {

            this.rollback();
            throw new BusinessException(e.getMessage(), e.getCause());
        } finally {
            this.closeSession();
        }
    }

    @Override
    public T find(T filter) {
        CriteriaBuilder criteriaBuilder = this.getSession().getCriteriaBuilder();
        CriteriaQuery<T> criteriaQuery = criteriaBuilder.createQuery(persistentClass);
        Root<T> from = criteriaQuery.from(persistentClass);
        List<Predicate> predicateList = this.makeExample(filter, criteriaBuilder, from);
        criteriaQuery.where(predicateList.toArray(new Predicate[0]));
        TypedQuery<T> q = this.getSession().createQuery(criteriaQuery);
        return q.getSingleResult();
    }

    @Override
    public List<T> findAll() {
        CriteriaQuery<T> cq = this.getSession().getCriteriaBuilder().createQuery(persistentClass);
        Root<T> rootEntry = cq.from(persistentClass);
        CriteriaQuery<T> all = cq.select(rootEntry);
        Query<T> allQuery = this.getSession().createQuery(all);
        return allQuery.getResultList();
    }

    @Override
    public T findById(long id) {
        CriteriaBuilder cb = this.getSession().getCriteriaBuilder();
        CriteriaQuery<T> cq = cb.createQuery(persistentClass);
        Root<T> model = cq.from(persistentClass);
        cq.where(cb.equal(model.get("id"), id));
        TypedQuery<T> q = this.getSession().createQuery(cq);
        return q.getSingleResult();
    }

    @Override
    public List<T> paginate(T filter, int pageSize, int page) {
        CriteriaBuilder criteriaBuilder = this.getSession().getCriteriaBuilder();
        CriteriaQuery<T> criteriaQuery = criteriaBuilder.createQuery(persistentClass);
        Root<T> from = criteriaQuery.from(persistentClass);
        List<Predicate> predicateList = this.makeExample(filter, criteriaBuilder, from);
        criteriaQuery.where(predicateList.toArray(new Predicate[0]));
        TypedQuery<T> q = this.getSession().createQuery(criteriaQuery);
        q.setFirstResult((page - 1) * pageSize);
        q.setMaxResults(pageSize);
        return q.getResultList();
    }

    public List<T> paginate(T filter, int pageSize, int page, String sortBy) {
        CriteriaBuilder criteriaBuilder = this.getSession().getCriteriaBuilder();
        CriteriaQuery<T> criteriaQuery = criteriaBuilder.createQuery(persistentClass);
        Root<T> from = criteriaQuery.from(persistentClass);
        criteriaQuery.orderBy(criteriaBuilder.asc(from.get(sortBy)));
        List<Predicate> predicateList = this.makeExample(filter, criteriaBuilder, from);
        criteriaQuery.where(predicateList.toArray(new Predicate[0]));
        TypedQuery<T> q = this.getSession().createQuery(criteriaQuery);
        q.setFirstResult((page - 1) * pageSize);
        q.setMaxResults(pageSize);
        return q.getResultList();
    }
    public List<T> paginate(T filter, int pageSize, int page, String sortBy, String filterby) {
        CriteriaBuilder criteriaBuilder = this.getSession().getCriteriaBuilder();
        CriteriaQuery<T> criteriaQuery = criteriaBuilder.createQuery(persistentClass);
        Root<T> from = criteriaQuery.from(persistentClass);
        criteriaQuery.orderBy(criteriaBuilder.asc(from.get(sortBy)));
        List<Predicate> predicateList = new ArrayList<>();
        predicateList.add(criteriaBuilder.like(from.get("name"), "%"+filterby+"%"));
        criteriaQuery.where(predicateList.toArray(new Predicate[0]));
        TypedQuery<T> q = this.getSession().createQuery(criteriaQuery);
        q.setFirstResult((page - 1) * pageSize);
        q.setMaxResults(pageSize);
        return q.getResultList();

    }



    @Override
    public int count(T filter) {
        CriteriaBuilder criteriaBuilder = this.getSession().getCriteriaBuilder();
        CriteriaQuery<Long> criteriaQuery = criteriaBuilder.createQuery(Long.class);
        Root<T> from = criteriaQuery.from(persistentClass);
        criteriaQuery.select(criteriaBuilder.count(from));
        List<Predicate> predicateList = this.makeExample(filter, criteriaBuilder, from);
        criteriaQuery.where(predicateList.toArray(new Predicate[0]));
        TypedQuery<Long> q = this.getSession().createQuery(criteriaQuery);
        return q.getSingleResult().intValue();
    }

    @Override
    public Session getSession() {
        this.openSession();
        return this.session;
    }

    @Override
    public void closeSession() {
        if (this.session != null) {
            this.session.clear();
            this.session.close();
        }
    }

    @Override
    public void beginTransaction() {
        this.openSession();
        this.session.beginTransaction();
    }

    @Override
    public void commit() {
        this.openSession();
        this.session.getTransaction().commit();
    }

    @Override
    public void rollback() {
        this.openSession();
        this.session.getTransaction().rollback();
    }

    @Override
    public List<Predicate> makeExample(T clazz, CriteriaBuilder criteriaBuilder, Root<T> from) {
        List<Predicate> predicateList = new ArrayList<>();
        List<Field> fieldList = Arrays.stream(clazz.getClass().getDeclaredFields()).collect(Collectors.toList());
        for (Field item : fieldList) {
            if (!isStatic(item.getModifiers())) {
                try {
                    Object value = FieldUtils.readField(item, clazz, true);
                    if (value != null) {
                        if (item.getType() == String.class)
                            predicateList.add(criteriaBuilder.like(from.get(item.getName()), "%".concat(value.toString()).concat("%")));
                        else if (item.getType().isPrimitive() || item.getType() == Number.class || item.getType() == Date.class)
                            predicateList.add(criteriaBuilder.equal(from.get(item.getName()), value));
                    }
                } catch (IllegalAccessException e) {

                }
            }
        }
        return predicateList;
    }

    private void openSession() {
        if (this.session == null || (!this.session.isOpen())) {
            this.session = sessionFactory.openSession();
        }
    }
}
