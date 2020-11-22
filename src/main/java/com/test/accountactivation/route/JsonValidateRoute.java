package com.test.accountactivation.route;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.dataformat.JsonLibrary;
import org.springframework.stereotype.Component;

import static com.test.accountactivation.route.Route.TRANSFORM_ROUTE_URI;
import static com.test.accountactivation.route.Route.VALIDATE_ROUTE_URI;

@Component
class JsonValidateRoute extends RouteBuilder {

    @Override
    public void configure() {
        from(VALIDATE_ROUTE_URI.value)
                .routeId(Route.VALIDATE_ROUTE_ID.value)
                .log("validating json with schema")
                .marshal().json(JsonLibrary.Jackson)
                .to("json-validator:account_details_schema.json")
                .to(TRANSFORM_ROUTE_URI.value);
    }

}
