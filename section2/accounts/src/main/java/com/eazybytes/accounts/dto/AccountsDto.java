package com.eazybytes.accounts.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.Data;




@Data
@Schema(
        name = "Accounts",
        description = "For Account details"
)
public class AccountsDto {

    @NotEmpty(message = "Account number cannot be empty")
    @Pattern(regexp = "(^$|[0-9]{10})",message = "Invalid account number format")
    @Schema(
            description = "Account number of the customer",
            example = "1234567890"
    )
    private Long accountNumber;

    @Schema(
            description = "Account type of the customer"
    )
    @NotEmpty(message = "Account type cannot be empty")
    private String accountType;

    @Schema(
            description = "Branch address of the customer",
            example = "123 New York Street"
    )
    @NotEmpty(message = "Branch address cannot be empty")
    private String branchAddress;

}
