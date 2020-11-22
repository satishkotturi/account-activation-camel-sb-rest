package com.test.accountactivation.model.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AccountActivationResponse {

    private ApplicationStatus applicationStatus;

    private String sortCode;

    private String accountNumber;
}
