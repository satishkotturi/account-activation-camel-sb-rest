package com.test.accountactivation.route;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

import static com.test.accountactivation.route.Route.TRANSFORM_ROUTE_URI;

@Component
public class JsonToXmlRoute extends RouteBuilder {

    @Override
    public void configure() {
        from(TRANSFORM_ROUTE_URI.value)
                .routeId(Route.TRANSFORM_ROUTE_ID.value)
                .log("transforming json to xml")
                .unmarshal().xmljson()
                .log("${body}");
    }
}
