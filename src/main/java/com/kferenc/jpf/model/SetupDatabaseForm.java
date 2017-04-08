package com.kferenc.jpf.model;

public class SetupDatabaseForm {

    private String host;
    private String port;
    private String catalog;
    private String username;
    private String password;

    public String getHost() {
        return host;
    }

    public String getPort() {
        return port;
    }

    public String getCatalog() {
        return catalog;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public void setCatalog(String catalog) {
        this.catalog = catalog;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
