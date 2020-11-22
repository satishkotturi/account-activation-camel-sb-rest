package com.test.accountactivation.controller;

import com.test.accountactivation.model.request.AccountDetails;
import com.test.accountactivation.model.response.AccountActivationResponse;
import org.apache.camel.CamelContext;
import org.apache.camel.Exchange;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.builder.ExchangeBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

import static com.test.accountactivation.route.Route.*;


@RestController
public class AccountActivationController {

    private static final Logger LOGGER = LoggerFactory.getLogger(AccountActivationController.class);

    @Autowired
    private ProducerTemplate producerTemplate;

    @Autowired
    private CamelContext camelContext;

    @PostMapping(path = "/AccountActivation", consumes = "application/json", produces = "application/json")
    @ResponseBody
    public ResponseEntity<AccountActivationResponse> processVAT(@Valid @RequestBody AccountDetails accountDetails) {

        LOGGER.info("routing request to JsonValidateRoute to validate with json schema");
        producerTemplate.sendBody(VALIDATE_ROUTE_URI.value, accountDetails);

        LOGGER.info("routing request to AccountActivation for activating using backend mock service");
        final Exchange requestExchange = ExchangeBuilder.anExchange(camelContext).withBody(accountDetails).build();

        final Exchange responseExchange = producerTemplate.send(ACCOUNT_ACTIVATION_ROUTE_URI.value, requestExchange);

        final AccountActivationResponse responseBody = responseExchange.getIn().getBody(AccountActivationResponse.class);

        final int responseCode = responseExchange.getIn().getHeader(Exchange.HTTP_RESPONSE_CODE, Integer.class).intValue();

        return ResponseEntity.status(responseCode).body(responseBody);
    }
}



