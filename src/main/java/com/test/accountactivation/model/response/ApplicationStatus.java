package com.test.accountactivation.model.response;

public enum  ApplicationStatus {

    ACCOUNT_ACTIVATED("Account activated"),
    APPLICATION_RECEIVED("Application received");

    public final String value;

    ApplicationStatus(String value) {
        this.value = value;
    }
}
