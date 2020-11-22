package com.test.accountactivation.route;

import org.apache.camel.test.spring.CamelSpringTestSupport;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;

public class JsonToXmlRouteTest extends CamelSpringTestSupport {

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
    public void marshallAccountDetailsJsonToXml() throws Exception {

        String request = "{\n" +
                "    \"title\": \"Mr\",\n" +
                "    \"firstName\": \"abcd\",\n" +
                "    \"lastName\": \"lastname\",\n" +
                "    \"emailAddress\": \"email@email.com\"\n" +
                "}";

        String expected = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\r\n" +
                "<o><emailAddress>email@email.com</emailAddress><firstName>abcd</firstName><lastName>lastname</lastName><title>Mr</title></o>\r\n";

        final String response = template.requestBody("direct:transform", request, String.class);
        assertEquals(expected,response);
    }
}
