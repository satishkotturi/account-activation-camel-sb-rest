package com.test.accountactivation.route;

import com.test.accountactivation.model.request.AccountDetails;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.test.spring.CamelSpringTestSupport;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;

import static com.test.accountactivation.route.Route.VALIDATE_ROUTE_URI;

public class JsonValidateRouteTest extends CamelSpringTestSupport {

    @Override
    protected AbstractApplicationContext createApplicationContext() {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();
        ctx.scan("com.test.accountactivation");
        ctx.refresh();
        return ctx;
    }

    @Test
    public void shouldStartJsonValidateRoute() throws Exception {
        assertTrue(context().getRouteStatus(Route.VALIDATE_ROUTE_ID.value).isStarted());
    }

    @Test
    public void shouldReturnEndpointWhenValidRoute() throws Exception {
        AccountDetails accountDetails = new AccountDetails();
        accountDetails.setTitle("Mr");
        accountDetails.setFirstName("ABCD");
        accountDetails.setLastName("LastName");
        accountDetails.setEmailAddress("email@gmail.com");

        ProducerTemplate template = context().createProducerTemplate();
        template.sendBody(VALIDATE_ROUTE_URI.value, accountDetails);

        assertNotNull(this.context.getEndpoint(VALIDATE_ROUTE_URI.value));
    }

    @Test
    public void shouldPassValidationWhenTitleNotProvided() throws Exception {
        AccountDetails accountDetails = new AccountDetails();
        accountDetails.setFirstName("ABCD");
        accountDetails.setLastName("LastName");
        accountDetails.setEmailAddress("email@gmail.com");

        ProducerTemplate template = context().createProducerTemplate();
        template.sendBody(VALIDATE_ROUTE_URI.value, accountDetails);

        assertNotNull(this.context.getEndpoint(VALIDATE_ROUTE_URI.value));
    }

}