package mw.jpa.config;

import mw.jpa.rwdb.ReplicationRoutingDataSource;
import mw.jpa.rwdb.DynamicDataSourceTransactionManager;
import mw.jpa.rwdb.MyInterceptor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by liuyu on 2017/12/1.
 */
@Configuration
@EnableJpaRepositories(entityManagerFactoryRef = "entityManagerFactory")
public class JpaDbConfig {
    @Value("${druid.type}")
    private Class<? extends DataSource> dataSourceType;
    @Bean(name = "writeDataSource")
    @ConfigurationProperties(prefix = "druid.winpos.master")
    public DataSource winposMasterDataSource() {
        return DataSourceBuilder.create().type(dataSourceType).build();
    }

    @Bean(name = "readDataSource")
    @ConfigurationProperties(prefix = "druid.winpos.slave")
    public DataSource winposSlaveDataSource1() {
        return DataSourceBuilder.create().type(dataSourceType).build();
    }
    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(@Qualifier("dataSource") DataSource dataSource){
        LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        vendorAdapter.setDatabase(Database.MYSQL);
        vendorAdapter.setGenerateDdl(true);
        vendorAdapter.setShowSql(false);
        factory.setJpaVendorAdapter(vendorAdapter);
        factory.setPackagesToScan("mw.jpa");
        factory.setDataSource(dataSource);
        Map<String, Object> jpaProperties = new HashMap<String, Object>();
        jpaProperties.put("hibernate.ejb.interceptor",new MyInterceptor());
        factory.setJpaPropertyMap(jpaProperties);
        factory.afterPropertiesSet();
        return factory;
    }
    @Bean
    public ReplicationRoutingDataSource dataSource(@Qualifier("writeDataSource") DataSource writeDataSource, @Qualifier("readDataSource") DataSource readDataSource){
        ReplicationRoutingDataSource routingDataSource=new ReplicationRoutingDataSource();
        Map<Object, Object> dataSourceMap = new HashMap<Object, Object>();
        dataSourceMap.put("write", writeDataSource);
        dataSourceMap.put("read", readDataSource);
        routingDataSource.setTargetDataSources(dataSourceMap);
        routingDataSource.setDefaultTargetDataSource(writeDataSource);
        return  routingDataSource;
    }
    @Bean
    public PlatformTransactionManager transactionManager(EntityManagerFactory entityManagerFactory) {
        DynamicDataSourceTransactionManager transactionManager = new DynamicDataSourceTransactionManager();
        transactionManager.setEntityManagerFactory(entityManagerFactory);
//        transactionManager.setDataSource(dataSource());
        return transactionManager;
    }
}
