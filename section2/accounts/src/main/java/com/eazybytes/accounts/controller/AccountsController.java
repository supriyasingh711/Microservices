package com.eazybytes.accounts.controller;


import com.eazybytes.accounts.constants.AccountsConstants;
import com.eazybytes.accounts.dto.CustomerDto;
import com.eazybytes.accounts.dto.ErrorResponseDto;
import com.eazybytes.accounts.dto.ResponseDto;
import com.eazybytes.accounts.service.IAccountService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Tag(
        name = "CRUD REST APIs for Accounts in bank",
        description = "CRUD Operations in Banks to CREATE,UPDATE,DELETE,GET ACCOUNTS"
)
@RestController
@RequestMapping(path = "/api",produces = {MediaType.APPLICATION_JSON_VALUE})
@AllArgsConstructor
@Validated
public class AccountsController {


    private IAccountService accountsService;

    /**
     * Creates a new account for a customer with the given details.
     *
     * @param customerDto the customer details
     * @return a response entity with the status code 201, indicating that the account was created successfully
     */
    @Operation(
            summary = "Create a new account for a customer",
            description = "Creates a new account for a customer with the given details"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "417",
                    description = "Account details not created successfully"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Account details not created successfully",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDto.class)
                    )
            )
    }
    )
    @PostMapping("/create")
    public ResponseEntity<ResponseDto> createAccount(@Valid @RequestBody CustomerDto customerDto)
        {
            accountsService.createAccount(customerDto);
            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body(new ResponseDto(AccountsConstants.STATUS_201, AccountsConstants.MESSAGE_201));


        } //To change body of generated methods, choose Tools | Templates>


    /**
     * Retrieves the account details for a customer with the given mobile number.
     *
     * @param mobileNumber the mobile number of the customer
     * @return a response entity with the status code 200, containing the customer details
     */
    @Operation(
            summary = "Fetch account details for a customer",
            description = "Retrieves the account details for a customer with the given mobile number"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Account details fetched successfully"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Account details not fetched successfully",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDto.class)
                    )
            )

    })
    @GetMapping("/fetch")
    public ResponseEntity<CustomerDto> fetchAccountDetails(@RequestParam
                                                               @Pattern(regexp = "(^$|[0-9]{10})",message = "Invalid mobile number format")
                                                               String mobileNumber) {
        CustomerDto customerDto = accountsService.fetchAccount(mobileNumber);
        return ResponseEntity.status(HttpStatus.OK).body(customerDto);
    }

    /**
     * Updates the account details for a customer with the given details.
     *
     * @param customerDto the customer details
     * @return a response entity with the status code 200 or 500, indicating the outcome of the update operation
     */
    @Operation(
            summary = "Update account details for a customer",
            description = "Updates the account details for a customer with the given details"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "417",
                    description = "Account details not updated successfully",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDto.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "200",
                    description = "Account details not updated successfully"
            ),
            @ApiResponse(
                    responseCode = "417",
                    description = "Exception failed"
            )
    }
    )
    @PutMapping("/update")
    public ResponseEntity<ResponseDto> updateAccount(@Valid @RequestBody CustomerDto customerDto) {
        boolean isUpdated = accountsService.updateAccount(customerDto);
        if (isUpdated) {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(new ResponseDto(AccountsConstants.STATUS_200, AccountsConstants.MESSAGE_200));
        }else {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ResponseDto(AccountsConstants.STATUS_417, AccountsConstants.MESSAGE_417_UPDATE));
        }
    }

    /**
     * Deletes the account associated with the given mobile number.
     *
     * @param mobileNumber the mobile number of the customer whose account is to be deleted
     * @return a response entity with the status code 200 if the account was deleted successfully,
     *         or 500 if the deletion failed
     */
    @Operation(
            summary = "Delete account for a customer",
            description = "Deletes the account associated with the given mobile number"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "500",
                    description = "Account not deleted successfully",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDto.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "200",
                    description = "Account deleted successfully"
            )
    }

    )
    @DeleteMapping("/delete")
    public ResponseEntity<ResponseDto> deleteAccount(@RequestParam String mobileNumber) {
        boolean isDeleted = accountsService.deleteAccount(mobileNumber);
        if (isDeleted) {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(new ResponseDto(AccountsConstants.STATUS_200, AccountsConstants.MESSAGE_200));
        } else {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ResponseDto(AccountsConstants.STATUS_417, AccountsConstants.MESSAGE_417_DELETE));
        }
    }

    }
