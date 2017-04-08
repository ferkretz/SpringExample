package com.kferenc.jpf.configuration;

import com.kferenc.jpf.model.DatabaseSetup;
import com.kferenc.jpf.model.HibernateSetup;
import com.kferenc.jpf.service.DatabaseSetupService;
import com.kferenc.jpf.service.HibernateSetupService;
import java.util.Properties;
import javax.sql.DataSource;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@ComponentScan({"com.kferenc.jpf"})
public class HibernateConfig {

    @Autowired
    HibernateSetupService hibernateSetupService;
    @Autowired
    DatabaseSetupService databaseSetupService;

    @Bean
    public LocalSessionFactoryBean sessionFactory() {
        LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
        sessionFactory.setDataSource(dataSource());
        sessionFactory.setPackagesToScan(new String[]{"com.kferenc.jpf.model"});
        sessionFactory.setHibernateProperties(hibernateProperties());
        return sessionFactory;
    }

    @Bean
    public DataSource dataSource() {
        DatabaseSetup databaseSetup = databaseSetupService.getDatabaseSetup();
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(databaseSetup.getDriverClassName());
        dataSource.setUrl(databaseSetup.getUrl());
        dataSource.setUsername(databaseSetup.getUsername());
        dataSource.setPassword(databaseSetup.getPassword());
        return dataSource;
    }

    @Bean
    @Autowired
    public HibernateTransactionManager transactionManager(SessionFactory s) {
        HibernateTransactionManager txManager = new HibernateTransactionManager();
        txManager.setSessionFactory(s);
        return txManager;
    }

    private Properties hibernateProperties() {
        HibernateSetup hibernateSetup = hibernateSetupService.getHibernateSetup();
        Properties properties = new Properties();
        properties.put("hibernate.dialect", hibernateSetup.getDialect());
        properties.put("hibernate.show_sql", hibernateSetup.getShowSql());
        properties.put("hibernate.format_sql", hibernateSetup.getFormatSql());
        return properties;
    }

}
