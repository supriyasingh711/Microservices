package com.eazybytes.cards.dto;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Data;


@Schema(name = "Cards",
        description = "Schema to holdCard details")
@Data
public class CardsDto {

        @NotEmpty(message = "Please enter mobile number")
        @Pattern(regexp = "^[0-9]{10}$", message = "Please enter valid mobile number")
        @Schema(description = "mobile number", example = "1234567890")
        private String mobileNumber;

        @NotEmpty(message = "Please enter card number")
        @Pattern(regexp = "^[0-9]{12}$", message = "Please enter valid card number")
        @Schema(description = "card number", example = "1234 1234 1234 1234")
        private String cardNumber;

        @NotEmpty(message = "Please enter card type")
        @Schema(description = "card type", example = "Credit")
        private String cardType;

        @Positive(message = "Please enter total limit greater than 0")
        @Schema(description = "total limit", example = "10000")
        private int totalLimit;

        @PositiveOrZero(message = "Please enter amount used which should be greater than or equal to 0")
        @Schema(description = "amount used", example = "5000")
        private int amountUsed;


        @PositiveOrZero(message = "Please enter available amount should be greater than or equal to 0")
        @Schema(description = "available amount", example = "5000")
        private int availableAmount;
}
