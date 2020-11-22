package com.test.accountactivation.route;

import com.test.accountactivation.model.request.AccountDetails;
import com.test.accountactivation.model.response.AccountActivationResponse;
import com.test.accountactivation.service.AccountActivationService;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static com.test.accountactivation.route.Route.*;

@Component
class AccountActivationRoute extends RouteBuilder {

    @Autowired
    AccountActivationService accountActivationService;

    @Override
    public void configure() {

        from(ACCOUNT_ACTIVATION_ROUTE_URI.value)
                .routeId(ACCOUNT_ACTIVATION_ROUTE_ID.value)
                .tracing()
                .log("first name: ${body.firstName}")
                .log("last name: ${body.lastName}")
                .process(new Processor() {
                    public void process(Exchange exchange) throws Exception {
                        AccountDetails bodyIn = (AccountDetails) exchange.getIn().getBody();

                        AccountActivationResponse response = accountActivationService.activateAccount(bodyIn);

                        exchange.getIn().setBody(response);
                    }
                }).log(">>> processed")
                .setHeader(Exchange.HTTP_RESPONSE_CODE, constant(201));
    }

}
