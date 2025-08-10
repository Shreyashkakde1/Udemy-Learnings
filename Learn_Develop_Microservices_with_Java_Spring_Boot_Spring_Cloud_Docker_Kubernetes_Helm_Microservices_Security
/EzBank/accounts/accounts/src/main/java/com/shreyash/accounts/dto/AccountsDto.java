package com.shreyash.accounts.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccountsDto {

     @NotEmpty(message = "Account number should not be empty")
     @Pattern(regexp = "^[0-9]{10}$")
     private Long accountNumber;

     @NotEmpty(message = "Account type should not be empty")
     private String accountType;

     @NotEmpty(message = "Branch address should not be empty")
     private String branchAddress;
}
