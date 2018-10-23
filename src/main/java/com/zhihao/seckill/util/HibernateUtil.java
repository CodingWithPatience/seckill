/**
 * 
 */
package com.zhihao.seckill.util;

import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Environment;
import org.springframework.beans.factory.annotation.Autowired;

import com.zhihao.seckill.dao.impl.SeckillDaoImpl;

/**
 * @author zzh
 * 2018年10月7日
 */
public class HibernateUtil {
	
	private static final Logger logger = LogManager.getLogger(SeckillDaoImpl.class);

//	@Autowired
//	private static DataSource dataSource;
	
	@Autowired
	private static DataSource proxyDataSource;
	
	public static SessionFactory buildSessionFactory(Class<?>... clazz) {
		StandardServiceRegistry registry = null;
		try {
			Map<String, Object> settings = new HashMap<>();
			StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder();
			
			settings.put(Environment.DATASOURCE, proxyDataSource);
	        settings.put(Environment.HBM2DDL_AUTO, "update");
	        settings.put(Environment.DIALECT, "org.hibernate.dialect.MySQL5InnoDBDialect");
	        settings.put(Environment.CURRENT_SESSION_CONTEXT_CLASS, "thread");
	        settings.put(Environment.SHOW_SQL, true);
	        settings.put(Environment.FORMAT_SQL, true);
	        settings.put(Environment.USE_SECOND_LEVEL_CACHE, false);
	        settings.put(Environment.USE_QUERY_CACHE, false);
	        
	        builder.applySettings(settings);
	        registry = builder.build();

	        // builds a session factory from the service registry
	        MetadataSources metadataSources = new MetadataSources(registry);
	        for(int i=0; i<clazz.length; i++) {
	        	metadataSources.addAnnotatedClass(clazz[i]);
	        }

	        Metadata metadata = metadataSources.getMetadataBuilder().build();
	        return metadata.buildSessionFactory();
		} catch (Exception e) {
			// Make sure you log the exception, as it might be swallowed
            logger.error("Initial SessionFactory creation failed." + e.getMessage());
            // The registry would be destroyed by the SessionFactory, but we had
            // trouble
            // building the SessionFactory so destroy it manually.
            StandardServiceRegistryBuilder.destroy(registry);
            throw new ExceptionInInitializerError(e.getCause());
		}
	}
	
//	private static DataSource getDataSource() {
//        // use pretty formatted query with multiline enabled
//        PrettyQueryEntryCreator creator = new PrettyQueryEntryCreator();
//        creator.setMultiline(true);
//        SystemOutQueryLoggingListener listener = new SystemOutQueryLoggingListener();
//        listener.setQueryLogEntryCreator(creator);
//
//        // Create ProxyDataSource
//        return ProxyDataSourceBuilder.create(dataSource)
//                .name("ProxyDataSource")
//                .countQuery()
//                .multiline()
//                .listener(listener)
//                .logSlowQueryToSysOut(1, TimeUnit.MINUTES)
//                .build();
//    }
	
	// use hibernate to format queries
//	private static class PrettyQueryEntryCreator extends DefaultQueryLogEntryCreator {
//        private Formatter formatter = FormatStyle.BASIC.getFormatter();
//
//        @Override
//        protected String formatQuery(String query) {
//            return this.formatter.format(query);
//        }
//    }
}
