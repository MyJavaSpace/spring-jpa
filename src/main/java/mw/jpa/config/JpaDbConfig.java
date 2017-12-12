package mw.jpa.config;

import mw.jpa.rwdb.DynamicDataSource;
import mw.jpa.rwdb.DynamicDataSourceTransactionManager;
import mw.jpa.rwdb.MyInterceptor;
import org.hibernate.engine.jdbc.connections.spi.AbstractDataSourceBasedMultiTenantConnectionProviderImpl;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaDialect;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.EclipseLinkJpaDialect;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * Created by liuyu on 2017/12/1.
 */
@Configuration
@EnableJpaRepositories(entityManagerFactoryRef = "entityManagerFactory")
public class JpaDbConfig {
    @Value("${druid.type}")
    private Class<? extends DataSource> dataSourceType;
    @Bean(name = "winposMasterDataSource")
    @Primary
    @ConfigurationProperties(prefix = "druid.winpos.master")
    public DataSource winposMasterDataSource() {
        return DataSourceBuilder.create().type(dataSourceType).build();
    }

    @Bean(name = "winposSlaveDataSource")
    @ConfigurationProperties(prefix = "druid.winpos.slave")
    public DataSource winposSlaveDataSource1() {
        return DataSourceBuilder.create().type(dataSourceType).build();
    }
    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(){
        LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
        factory.setJpaVendorAdapter(hibernateJpaVendorAdapter());
        factory.setPackagesToScan("mw.jpa");
        factory.setDataSource(dynamicDataSource());
        Map<String, Object> jpaProperties = new HashMap<String, Object>();
        jpaProperties.put("hibernate.ejb.interceptor",new MyInterceptor());
        factory.setJpaPropertyMap(jpaProperties);
        factory.afterPropertiesSet();
        return factory;
    }
    @Bean
   public   HibernateJpaVendorAdapter hibernateJpaVendorAdapter(){
        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        vendorAdapter.setDatabase(Database.MYSQL);
        vendorAdapter.setGenerateDdl(true);
        vendorAdapter.setShowSql(false);
        return vendorAdapter;
    }

    @Bean
    public DynamicDataSource dynamicDataSource(){
        DynamicDataSource dynamicDataSource=new DynamicDataSource();
        dynamicDataSource.setReadDataSource(winposSlaveDataSource1());
        dynamicDataSource.setWriteDataSource(winposMasterDataSource());
        dynamicDataSource.afterPropertiesSet();
        return  dynamicDataSource;
    }
    @Bean("transactionManager")
    public DynamicDataSourceTransactionManager dynamicDataSourceTransactionManager(){
        DynamicDataSourceTransactionManager dynamicDataSourceTransactionManager=new DynamicDataSourceTransactionManager();
        dynamicDataSourceTransactionManager.setDataSource(dynamicDataSource());
        return  dynamicDataSourceTransactionManager;
    }
}
