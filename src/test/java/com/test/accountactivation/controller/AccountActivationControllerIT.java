package com.test.accountactivation.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.test.accountactivation.model.request.AccountDetails;
import com.test.accountactivation.service.AccountActivationService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import javax.ws.rs.core.MediaType;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
public class AccountActivationControllerIT {

    @Autowired
    ObjectMapper objectMapper;
    @MockBean
    AccountActivationService accountActivationService;
    @Autowired
    private MockMvc mockMvc;

    @Before
    public void setUp() throws Exception {

    }

    @Test
    public void shouldReturnHTTP201_whenValidJsonRequest() throws Exception {

        String validJsonRequest = "{\n" +
                "    \"title\": \"Mr\",\n" +
                "    \"firstName\": \"abcd\",\n" +
                "    \"lastName\": \"lastname\",\n" +
                "    \"emailAddress\": \"email@gmail.com\"\n" +
                "\n" +
                "}";

        this.mockMvc.perform(MockMvcRequestBuilders.post("/AccountActivation")
                .content(validJsonRequest)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print()).andExpect(status().isCreated());

        verify(accountActivationService, times(1)).activateAccount(any(AccountDetails.class));
    }


    @Test
    public void shouldReturnHTTP400_whenMissingMandatoryField() throws Exception {

        AccountDetails accountDetails = new AccountDetails();
        accountDetails.setFirstName("first name");

        this.mockMvc.perform(MockMvcRequestBuilders.post("/AccountActivation")
                .content(objectMapper.writeValueAsString(accountDetails))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print()).andExpect(status().isBadRequest());

        verify(accountActivationService, times(0)).activateAccount(any(AccountDetails.class));
    }


    @Test
    public void shouldReturnHTTP400_whenInvalidAmountField() throws Exception {

        String inValidJsonRequest = "{\n" +
                "    \"title\": \"12\",\n" +
                "    \"firstName\": \"ab\",\n" +
                "    \"lastName\": \"lastname\",\n" +
                "    \"emailAddress\": \"email@email.com\"\n" +
                "}";

        this.mockMvc.perform(MockMvcRequestBuilders.post("/AccountActivation")
                .content(inValidJsonRequest)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print()).andExpect(status().isBadRequest());

        verify(accountActivationService, times(0)).activateAccount(any(AccountDetails.class));
    }

}

