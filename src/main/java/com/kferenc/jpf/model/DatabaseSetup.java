package com.kferenc.jpf.model;

public class DatabaseSetup {

    private String configured;
    private String catalog;
    private String driverClassName;
    private String host;
    private String params;
    private String password;
    private String port;
    private String urlScheme;
    private String username;

    public boolean isNone() {
        return !isSchemeCreated() && !isFullConfigured();
    }

    public boolean isSchemeCreated() {
        return "schemeCreated".equals(getConfigured());
    }

    public boolean isFullConfigured() {
        return "fullConfigured".equals(getConfigured());
    }

    public void setNone() {
        setConfigured("");
    }

    public void setSchemeCreated() {
        setConfigured("schemeCreated");
    }

    public void setFullConfigured() {
        setConfigured("fullConfigured");
    }

    public String getConfigured() {
        return configured;
    }

    public String getCatalog() {
        return catalog;
    }

    public String getDriverClassName() {
        return driverClassName;
    }

    public String getHost() {
        return host;
    }

    public String getParams() {
        return params;
    }

    public String getPassword() {
        return password;
    }

    public String getPort() {
        return port;
    }

    public String getUrlScheme() {
        return urlScheme;
    }

    public String getUsername() {
        return username;
    }

    public String getUrl() {
        return composeUrl();
    }

    public void setConfigured(String configured) {
        this.configured = configured;
    }

    public void setCatalog(String catalog) {
        this.catalog = catalog;
    }

    public void setDriverClassName(String driverClassName) {
        this.driverClassName = driverClassName;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public void setParams(String params) {
        this.params = params;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public void setUrlScheme(String urlScheme) {
        this.urlScheme = urlScheme;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    private String composeUrl() {
        StringBuilder urlBuilder = new StringBuilder();

        urlBuilder.append(getUrlScheme()).append("://").append(getHost());
        if (!getPort().isEmpty()) {
            urlBuilder.append(":").append(getPort());
        }
        if (!isNone()) {
            urlBuilder.append("/").append(getCatalog());
        }
        if (!getParams().isEmpty()) {
            urlBuilder.append("?").append(getParams());
        }

        return urlBuilder.toString();
    }

}
