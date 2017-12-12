package mw.jpa.rwdb;

import mw.jpa.entiy.Customer;
import org.hibernate.EmptyInterceptor;
import org.hibernate.Transaction;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.io.Serializable;
import java.lang.reflect.Type;
import java.util.Iterator;
import java.util.Locale;

/**
 *
 * @author liuyu
 * @date 2017/12/12
 */
public class MyInterceptor extends EmptyInterceptor {
    private static final String REGEX = ".*insert\\u0020.*|.*delete\\u0020.*|.*update\\u0020.*";
    @Override
    public String onPrepareStatement(String sql) {
//        DynamicDataSourceGlobal dynamicDataSourceGlobal;
//        if(sql.matches(REGEX)) {
//            dynamicDataSourceGlobal = DynamicDataSourceGlobal.WRITE;
//        } else {
//            dynamicDataSourceGlobal = DynamicDataSourceGlobal.READ;
//        }
//        DynamicDataSourceHolder.clearDataSource();
//        DynamicDataSourceHolder.putDataSource(dynamicDataSourceGlobal);
//        System.out.println("onPrepareStatement="+dynamicDataSourceGlobal.name());
        return super.onPrepareStatement(sql);
    }
    @Override
    public void onDelete(
            Object entity,
            Serializable id,
            Object[] state,
            String[] propertyNames,
            org.hibernate.type.Type[] types) {
//        DynamicDataSourceGlobal dynamicDataSourceGlobal = DynamicDataSourceGlobal.WRITE;
//        DynamicDataSourceHolder.putDataSource(dynamicDataSourceGlobal);
        System.out.println("onDelete");
    }

    // This method is called when Employee object gets updated.
    @Override
    public boolean onFlushDirty(
            Object entity,
            Serializable id,
            Object[] currentState,
            Object[] previousState,
            String[] propertyNames,
            org.hibernate.type.Type[] types) {
//        DynamicDataSourceGlobal dynamicDataSourceGlobal = DynamicDataSourceGlobal.WRITE;
//        DynamicDataSourceHolder.putDataSource(dynamicDataSourceGlobal);
        System.out.println("onFlushDirty");
        return false;
    }
    @Override
    public boolean onLoad(
            Object entity,
            Serializable id,
            Object[] state,
            String[] propertyNames,
            org.hibernate.type.Type[] types) {
        System.out.println("onLoad");
        return false;
    }

    // This method is called when Employee object gets created.
    @Override
    public boolean onSave(
            Object entity,
            Serializable id,
            Object[] state,
            String[] propertyNames,
            org.hibernate.type.Type[] types) {
        System.out.println("onSave");
//        DynamicDataSourceGlobal dynamicDataSourceGlobal = DynamicDataSourceGlobal.WRITE;
//        DynamicDataSourceHolder.putDataSource(dynamicDataSourceGlobal);
        return false;
    }


    //called before commit into database
    @Override
    public void preFlush(Iterator iterator) {
        System.out.println("preFlush");
    }

    //called after committed into database
    @Override
    public void postFlush(Iterator iterator) {
        DynamicDataSourceHolder.clearDataSource();
        System.out.println("postFlush");
    }
}
