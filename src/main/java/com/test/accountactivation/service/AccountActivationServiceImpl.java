package com.test.accountactivation.service;

import com.test.accountactivation.model.request.AccountDetails;
import com.test.accountactivation.model.response.AccountActivationResponse;
import com.test.accountactivation.model.response.ApplicationStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AccountActivationServiceImpl implements AccountActivationService {

    private static final Logger LOGGER = LoggerFactory.getLogger(AccountActivationServiceImpl.class);

    private static final String SUCCSFUL_ACCOUNT_FIRST_NAME = "ABCD";
    private static final String SORT_CODE = "010203";
    private static final String AAOUNT_NUMBER = "12345678";

    public AccountActivationResponse activateAccount(AccountDetails bodyIn) {

        LOGGER.info("AccountActivationService.activateAccount method started ");

        AccountActivationResponse response = new AccountActivationResponse();

        if (bodyIn.getFirstName().equalsIgnoreCase(SUCCSFUL_ACCOUNT_FIRST_NAME)) {
            response.setApplicationStatus(ApplicationStatus.ACCOUNT_ACTIVATED);
            response.setSortCode(SORT_CODE);
            response.setAccountNumber(AAOUNT_NUMBER);
        } else {
            response.setApplicationStatus(ApplicationStatus.APPLICATION_RECEIVED);
        }
        return  response;
    }
}
