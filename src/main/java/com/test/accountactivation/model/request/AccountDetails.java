package com.test.accountactivation.model.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Getter
@Setter
public class AccountDetails {

    @NotNull
    @Pattern(regexp = "[a-zA-Z]+", message = "must not contain numbers or special characters")
    private String title;

    @NotNull
    @Pattern(regexp = "[a-zA-Z\\s]+", message = "must not contain numbers or special characters")
    private String firstName;

    @NotNull
    @Pattern(regexp = "[a-zA-Z\\s]+", message = "must not contain numbers or special characters")
    private String lastName;

    @NotNull
    private String emailAddress;

}
