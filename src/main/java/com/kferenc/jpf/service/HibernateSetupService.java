package com.kferenc.jpf.service;

import com.kferenc.jpf.model.HibernateSetup;

public interface HibernateSetupService {

    public HibernateSetup getHibernateSetup();

    public void setHibernateSetup(HibernateSetup hibernateSetup);

    public void storeHibernateSetup(HibernateSetup hibernateSetup);

}
