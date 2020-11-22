package com.test.accountactivation.service;

import com.test.accountactivation.model.request.AccountDetails;
import com.test.accountactivation.model.response.AccountActivationResponse;

public interface AccountActivationService {

    AccountActivationResponse activateAccount(AccountDetails accountDetails);
}
