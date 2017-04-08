package com.kferenc.jpf.service;

import com.kferenc.jpf.configuration.ApplicationProperties;
import com.kferenc.jpf.model.HibernateSetup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("hibernateSetupService")
public class HibernateSetupServiceImpl implements HibernateSetupService {

    @Autowired
    ApplicationProperties properties;

    @Override
    public HibernateSetup getHibernateSetup() {
        HibernateSetup hibernateSetup = new HibernateSetup();
        hibernateSetup.setDialect(properties.getProperty("hibernate.dialect"));
        hibernateSetup.setShowSql(properties.getProperty("hibernate.showSql"));
        hibernateSetup.setFormatSql(properties.getProperty("hibernate.formatSql"));
        return hibernateSetup;
    }

    @Override
    public void setHibernateSetup(HibernateSetup hibernateSetup) {
        properties.setProperty("hibernate.dialect", hibernateSetup.getDialect());
        properties.setProperty("hibernate.showSql", hibernateSetup.getShowSql());
        properties.setProperty("hibernate.formatSql", hibernateSetup.getFormatSql());
    }

    @Override
    public void storeHibernateSetup(HibernateSetup hibernateSetup) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
