package br.com.rankbet.hibernate.interceptor;

import org.hibernate.EmptyInterceptor;
import org.hibernate.type.Type;


import java.io.Serializable;
import java.util.Date;



public class Interceptor extends EmptyInterceptor {

    private static final long serialVersionUID = -1324056706245509029L;

    @Override
    public boolean onSave(Object entity, Serializable id, Object[] state, String[] propertyNames, Type[] types) {
        return super.onSave(entity, id, state, propertyNames, types);
    }

    @Override
    public boolean onFlushDirty(Object entity, Serializable id, Object[] currentState, Object[] previousState, String[] propertyNames, Type[] types) {
        return super.onFlushDirty(entity, id, currentState, previousState,
                propertyNames, types);
    }

    @Override
    public void onDelete(Object entity, Serializable id, Object[] state, String[] propertyNames, Type[] types) {
            super.onDelete(entity, id, state, propertyNames, types);
    }
}
