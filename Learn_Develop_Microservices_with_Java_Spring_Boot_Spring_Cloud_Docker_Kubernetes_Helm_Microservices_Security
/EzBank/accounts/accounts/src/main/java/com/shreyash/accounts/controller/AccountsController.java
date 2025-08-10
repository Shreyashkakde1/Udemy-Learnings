package com.shreyash.accounts.controller;

import com.shreyash.accounts.dto.AccountsContactsDto;
import com.shreyash.accounts.dto.CustomerDto;
import com.shreyash.accounts.dto.ResponseDto;
import com.shreyash.accounts.service.AccountsService;
import com.shreyash.accounts.utility.Constants;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import java.util.Map;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RestController
@RequestMapping(value = "/api/accounts",produces = MediaType.APPLICATION_JSON_VALUE)
//@AllArgsConstructor
@Validated
@Tag(
        name = "CRUD REST API for Accounts IN EazyBank",
        description = "CRUD REST API for Accounts IN EazyBank"
)
public class AccountsController {

    @Autowired
    private AccountsService accountsService;

    @Value("${build.version}")
    private String buildVersion;

    @Autowired
    private Environment environment;

    @Autowired
    private AccountsContactsDto accountsContactsDto;


    @Operation(
            summary = "Create Account",
            description = "Create Account"
    )
    @ApiResponse(
            responseCode = "201",
            description = "Account Created Successfully"
    )
    @PostMapping("/createAccount")
    public ResponseEntity<ResponseDto> createAccount(@Valid @RequestBody CustomerDto customerDto){
        accountsService.createAccount(customerDto);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new ResponseDto(Constants.STATUS_201,Constants.MESSAGE_201));
    }


    @Operation(
            summary = "Fetch Account",
            description = "Fetch Account"
    )
    @ApiResponse(
            responseCode = "201",
            description = "Account Fetch Successfully"
    )
    @GetMapping("/fetchAccount/{mobileNumber}")
    public ResponseEntity<CustomerDto> fetchAccount(@PathVariable
                                                        @Pattern(regexp = "^[0-9]{10}$",message = "Mobile number should be 10 digits")
                                                        String mobileNumber){
        CustomerDto customerDto = accountsService.fetchAccount(mobileNumber);
        return new ResponseEntity<>(customerDto,HttpStatus.OK);
    }

    @Operation(
            summary = "Update Account",
            description = "Update Account"
    )
    @ApiResponse(
            responseCode = "201",
            description = "Update Fetch Successfully"
    )
    @PutMapping("/updateAccount")
    public ResponseEntity<ResponseDto> updateAccount(@Valid @RequestBody CustomerDto customerDto){
        boolean isUpdated = accountsService.updateAccount(customerDto);
        if(isUpdated){
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(new ResponseDto(Constants.STATUS_200,Constants.MESSAGE_200));
        }else{
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ResponseDto(Constants.STATUS_500,Constants.MESSAGE_500));
        }
    }

    @Operation(
            summary = "Delete Account",
            description = "Delete Account"
    )
    @ApiResponse(
            responseCode = "201",
            description = "Delete Fetch Successfully"
    )
    @DeleteMapping("/delete")
    public ResponseEntity<ResponseDto> deleteAccount(@RequestParam
                                                         @Pattern(regexp = "^[0-9]{10}$",message = "Mobile number should be 10 digits")
                                                         String mobileNumber){
        boolean isDeleted = accountsService.deleteAccount(mobileNumber);
        if(isDeleted){
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(new ResponseDto(Constants.STATUS_200,Constants.MESSAGE_200));
        }else{
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ResponseDto(Constants.STATUS_500,Constants.MESSAGE_500));
        }
    }



    @GetMapping("/build-info")
    public ResponseEntity<Map<String, String>> getBuildVersion() {
        Map<String, String> response = new HashMap<>();
        String cleanBuildVersion = buildVersion.replaceAll("^\"|\"$", ""); // Remove surrounding quotes
        response.put("buildVersion", cleanBuildVersion);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/java-version")
    public ResponseEntity<Map<String, String>> getJavaVerision() {
        Map<String, String> response = new HashMap<>();
        String javaVersion = System.getProperty("java.version");
        response.put("javaVersion", javaVersion);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/contact-info")
    public ResponseEntity<AccountsContactsDto> getContactInfo() {
        return new ResponseEntity<>(accountsContactsDto, HttpStatus.OK);
    }
}
