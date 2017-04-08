package com.kferenc.jpf.service;

import com.kferenc.jpf.configuration.ApplicationProperties;
import com.kferenc.jpf.model.DatabaseSetup;
import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.ServletContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.jdbc.datasource.init.DatabasePopulatorUtils;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import org.springframework.stereotype.Service;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

@Service("databaseSetupService")
public class DatabaseSetupServiceImpl implements DatabaseSetupService {

    @Autowired
    ApplicationProperties properties;
    @Autowired
    ServletContext servletContext;
    @Autowired
    private ResourceLoader resourceLoader;

    @Override
    public DatabaseSetup getDatabaseSetup() {
        DatabaseSetup databaseSetup = new DatabaseSetup();
        databaseSetup.setConfigured(properties.getProperty("jdbc.configured"));
        databaseSetup.setCatalog(properties.getProperty("jdbc.catalog"));
        databaseSetup.setDriverClassName(properties.getProperty("jdbc.driverClassName"));
        databaseSetup.setHost(properties.getProperty("jdbc.host"));
        databaseSetup.setParams(properties.getProperty("jdbc.params"));
        databaseSetup.setPassword(properties.getProperty("jdbc.password"));
        databaseSetup.setPort(properties.getProperty("jdbc.port"));
        databaseSetup.setUrlScheme(properties.getProperty("jdbc.urlScheme"));
        databaseSetup.setUsername(properties.getProperty("jdbc.username"));
        return databaseSetup;
    }

    @Override
    public void setDatabaseSetup(DatabaseSetup databaseSetup) {
        properties.setProperty("jdbc.configured", databaseSetup.getConfigured());
        properties.setProperty("jdbc.catalog", databaseSetup.getCatalog());
        properties.setProperty("jdbc.driverClassName", databaseSetup.getDriverClassName());
        properties.setProperty("jdbc.host", databaseSetup.getHost());
        properties.setProperty("jdbc.params", databaseSetup.getParams());
        properties.setProperty("jdbc.password", databaseSetup.getPassword());
        properties.setProperty("jdbc.port", databaseSetup.getPort());
        properties.setProperty("jdbc.urlScheme", databaseSetup.getUrlScheme());
        properties.setProperty("jdbc.username", databaseSetup.getUsername());
        DriverManagerDataSource dataSource = getDataSource();
        dataSource.setDriverClassName(databaseSetup.getDriverClassName());
        dataSource.setUrl(databaseSetup.getUrl());
        dataSource.setUsername(databaseSetup.getUsername());
        dataSource.setPassword(databaseSetup.getPassword());
    }

    @Override
    public void storeDatabaseSetup(DatabaseSetup databaseSetup) throws IOException {
        properties.storeConfig();
    }

    @Override
    public void createDatabase(String sqlPath) throws DataAccessException {
        ResourceDatabasePopulator populator = new ResourceDatabasePopulator();

        populator.addScript(resourceLoader.getResource(sqlPath));
        DatabasePopulatorUtils.execute(populator, getDataSource());
    }

    @Override
    public void prepareDatabase(DatabaseSetup databaseSetup, String sqlPath) throws DataAccessException, IOException {
        databaseSetup.setCatalog(databaseSetup.getCatalog().replaceAll("[^a-zA-Z]", ""));

        System.out.println(databaseSetup.getUrl());

        JdbcTemplate jdbc = new JdbcTemplate(getDataSource());

        jdbc.execute("DROP DATABASE IF EXISTS " + databaseSetup.getCatalog());
        jdbc.execute("CREATE DATABASE " + databaseSetup.getCatalog());
        databaseSetup.setSchemeCreated();
        setDatabaseSetup(databaseSetup);
        storeDatabaseSetup(databaseSetup);

        ResourceDatabasePopulator populator = new ResourceDatabasePopulator();
        populator.addScript(resourceLoader.getResource(sqlPath));
        DatabasePopulatorUtils.execute(populator, getDataSource());
    }

    @Override
    public boolean testConnection() {
        try {
            getDataSource().getConnection();
        } catch (SQLException ex) {
            //Logger.getLogger(DatabaseSetupServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        return true;
    }

    private DriverManagerDataSource getDataSource() {
        WebApplicationContext applicationContext = WebApplicationContextUtils.getWebApplicationContext(servletContext);
        return applicationContext.getBean("dataSource", DriverManagerDataSource.class);
    }

}
