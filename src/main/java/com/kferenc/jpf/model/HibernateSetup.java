package com.kferenc.jpf.model;

public class HibernateSetup {

    private String dialect;
    private String showSql;
    private String formatSql;

    public String getDialect() {
        return dialect;
    }

    public String getShowSql() {
        return showSql;
    }

    public String getFormatSql() {
        return formatSql;
    }

    public void setDialect(String dialect) {
        this.dialect = dialect;
    }

    public void setShowSql(String showSql) {
        this.showSql = showSql;
    }

    public void setFormatSql(String formatSql) {
        this.formatSql = formatSql;
    }

}
