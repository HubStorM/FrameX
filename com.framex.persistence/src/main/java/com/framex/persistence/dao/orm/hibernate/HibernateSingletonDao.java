package com.framex.persistence.dao.orm.hibernate;

import com.framex.persistence.SpringContextUtil;
import com.framex.persistence.dao.DaoTypeEnum;
import com.framex.persistence.dao.orm.OrmDao;
import org.hibernate.Criteria;
import org.hibernate.FlushMode;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.orm.hibernate4.support.HibernateDaoSupport;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
import java.io.Serializable;
import java.util.List;

/**
 * @author lijie
 * @date 2017/10/24 09:21
 * @description
 */
public enum HibernateSingletonDao implements OrmDao{
    INSTACNE;

    private final DataSource dataSource;

    private final SessionFactory sessionFactory;

    private final HibernateTemplate hibernateTemplate;

    private HibernateSingletonDao() {
        dataSource = SpringContextUtil.getApplicationContext().getBean("dataSource", DataSource.class);
        sessionFactory = SpringContextUtil.getApplicationContext().getBean("defaultSessionFactory", SessionFactory.class);
        hibernateTemplate = new HibernateTemplate(sessionFactory);
        //hibernateTemplate.setCheckWriteOperations(false);
    }

    private Session getSession(){
        return sessionFactory.getCurrentSession();
    }

    @Override
    public DaoTypeEnum getType() {
        return DaoTypeEnum.SINGLETON;
    }

    @Override
    public DataSource getDataSource() {
        return dataSource;
    }

    @Override
    public <T> T findObject(Class<T> requiredType, Serializable id) {
        return hibernateTemplate.get(requiredType, id);
    }

    @Override
    public <T> T findObject(String hql, Class<T> requiredType, Object... args) {
        return hibernateTemplate.execute(session -> {
            Query<T> query = session.createQuery(hql, requiredType);
            for(int i = 0; i < args.length; ++i){
                query.setParameter(i, args[i]);
            }
            return query.uniqueResult();
        });
    }

    @Override
    public <T> List<T> findList(Class<T> requiredType) {
        return hibernateTemplate.loadAll(requiredType);
    }

    @Override
    public <T> List<T> findList(String hql, Class<T> requiredType, Object... args) {
        return hibernateTemplate.execute(session -> {
            Query<T> query = session.createQuery(hql, requiredType);
            for(int i = 0; i < args.length; ++i){
                query.setParameter(i, args[i]);
            }
            return query.list();
        });
    }

    @Override
    public <T> List<T> findList(int currentPage, int pageSize, Class<T> requiredType) {
        return hibernateTemplate.execute(session -> {
            Criteria criteria = session.createCriteria(requiredType).setFirstResult((currentPage - 1) * pageSize).setMaxResults(pageSize);
            return criteria.list();
        });
    }

    @Override
    public <T> List<T> findList(int currentPage, int pageSize, String hql, Class<T> requiredType, Object... args) {
        return hibernateTemplate.execute(session -> {
            Query<T> query = session.createQuery(hql, requiredType).setFirstResult((currentPage - 1) * pageSize).setMaxResults(pageSize);
            for(int i = 0; i < args.length; ++i){
                query.setParameter(i, args[i]);
            }
            return query.list();
        });
    }

    @Override
    public <T> Serializable save(T item) {
        return hibernateTemplate.save(item);
    }

    @Override
    public <T> void update(T item) {
        hibernateTemplate.update(item);
    }



    /*public HibernateSingletonDao(DaoTypeEnum daoTypeEnum) {
        dataSource = SpringContextUtil.getApplicationContext().getBean("dataSource", DataSource.class);

        LocalSessionFactoryBean lsfb = new LocalSessionFactoryBean();
        if(daoTypeEnum.equals(DaoTypeEnum.SINGLETON)){
            DataSource defaultDataSource = SpringContextUtil.getApplicationContext().getBean("defaultDataSource", DataSource.class);
            lsfb.setDataSource(defaultDataSource);
        }
        if(daoTypeEnum.equals(DaoTypeEnum.PROTOTYPE)){
            DynamicDataSource dynamicDataSource = new DynamicDataSource().setDataSourceHolder("defaultDataSource");
            lsfb.setDataSource(dynamicDataSource);
        }
        Properties props = new Properties();
        props.put("hibernate.show_sql", "true");
        props.put("hibernate.format_sql", "true");
        lsfb.setHibernateProperties(props);
        this.sessionFactory = sessionFactory;
    }*/



}
