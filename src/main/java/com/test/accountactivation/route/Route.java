package com.test.accountactivation.route;

public enum Route {

    ACCOUNT_ACTIVATION_ROUTE_URI("direct:activationService"),
    ACCOUNT_ACTIVATION_ROUTE_ID("direct-route"),
    VALIDATE_ROUTE_URI("direct:start"),
    VALIDATE_ROUTE_ID("direct-schema-route"),
    TRANSFORM_ROUTE_URI("direct:transform"),
    TRANSFORM_ROUTE_ID("direct-transform-route");

    public final String value;

    Route(String value) {
        this.value = value;
    }

}


