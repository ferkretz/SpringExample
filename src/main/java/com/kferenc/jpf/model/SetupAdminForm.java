package com.kferenc.jpf.model;

public class SetupAdminForm {

    private String username;
    private String password;
    private String confirmPassword;
    private String email;
    private String databasePassword;

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public String getEmail() {
        return email;
    }

    public String getDatabasePassword() {
        return databasePassword;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setDatabasePassword(String databasePassword) {
        this.databasePassword = databasePassword;
    }

}
