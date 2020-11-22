package com.test.accountactivation.route;

import com.test.accountactivation.model.request.AccountDetails;
import com.test.accountactivation.model.response.AccountActivationResponse;
import com.test.accountactivation.model.response.ApplicationStatus;
import org.apache.camel.Endpoint;
import org.apache.camel.Exchange;
import org.apache.camel.builder.ExchangeBuilder;
import org.apache.camel.test.spring.CamelSpringTestSupport;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;

import static com.test.accountactivation.route.Route.ACCOUNT_ACTIVATION_ROUTE_ID;
import static com.test.accountactivation.route.Route.ACCOUNT_ACTIVATION_ROUTE_URI;


public class AccountActivationRouteTest extends CamelSpringTestSupport {

    @Override
    protected AbstractApplicationContext createApplicationContext() {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();
        ctx.scan("com.test.accountactivation");
        ctx.refresh();
        return ctx;
    }

    @Test
    public void shouldStartAccountActivationRoute() throws Exception {
        assertTrue(context().getRouteStatus(ACCOUNT_ACTIVATION_ROUTE_ID.value).isStarted());
    }

    @Test
    public void shouldProcessMessageByAccountActivationRoute() throws Exception {

        AccountDetails aCompany = new AccountDetails();
        aCompany.setFirstName("ABCD");
        final Endpoint endpoint = getMandatoryEndpoint(ACCOUNT_ACTIVATION_ROUTE_URI.value);
        final Exchange requestExchange = ExchangeBuilder.anExchange(context()).withBody(aCompany).build();
        final Exchange resultExchange = context().createProducerTemplate().send(endpoint, requestExchange);
        final AccountActivationResponse responseBody = resultExchange.getIn().getBody(AccountActivationResponse.class);
        assertTrue(responseBody.getApplicationStatus().equals(ApplicationStatus.ACCOUNT_ACTIVATED));
    }
}