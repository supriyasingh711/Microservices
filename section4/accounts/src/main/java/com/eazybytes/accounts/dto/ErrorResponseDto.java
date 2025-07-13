package com.eazybytes.accounts.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Data @AllArgsConstructor
@Schema(
        name = "ErrorResponse",
        description = "For Error Response information"
)
public class ErrorResponseDto {
    @Schema(
            description = "API Path of the request that caused the error"
    )
    private String apiPath;

    @Schema(
            description = "HTTP Status code of the error"
    )
    private HttpStatus errorCode;
    @Schema(
            description = "Error message"
    )
    private String errorMessage;
    @Schema(
            description = "Current time of the error"
    )
    private LocalDateTime errorTime;


}
