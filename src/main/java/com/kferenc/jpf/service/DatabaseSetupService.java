package com.kferenc.jpf.service;

import com.kferenc.jpf.model.DatabaseSetup;
import java.io.IOException;
import org.springframework.dao.DataAccessException;

public interface DatabaseSetupService {

    public DatabaseSetup getDatabaseSetup();

    public void setDatabaseSetup(DatabaseSetup databaseSetup);

    public void storeDatabaseSetup(DatabaseSetup databaseSetup) throws IOException;

    public void createDatabase(String sqlPath) throws DataAccessException;

    public void prepareDatabase(DatabaseSetup databaseSetup, String sqlPath) throws DataAccessException, IOException;

    public boolean testConnection();

}
