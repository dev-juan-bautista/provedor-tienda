package co.com.linktic.api.util;

import co.com.linktic.model.error.BusinessException;
import co.com.linktic.validator.dto.response.ApiGenericResponse;
import co.com.linktic.validator.error.ValidationException;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

public class ResponseBuilder {

    private ResponseBuilder() {
    }

    public static ApiGenericResponse<Object> buildSuccessResponse(Object data, String message) {
        return ApiGenericResponse.builder()
                .success(true)
                .responseCode("LINKTIC_SERVICE_SUCCESS")
                .data(data)
                .message(message)
                .timestamp(LocalDateTime.now())
                .build();
    }

    public static ApiGenericResponse<Object> buildValidationErrorResponse(ValidationException ex) {

        return ApiGenericResponse.builder()
                .success(false)
                .errors(ex.getErrors())
                .responseCode("LINKTIC_SERVICE_VALIDATION_ERROR")
                .message(ex.getMessage())
                .timestamp(LocalDateTime.now())
                .build();
    }

    public static ApiGenericResponse<Object> buildBusinessErrorResponse(BusinessException ex) {
        return ApiGenericResponse.builder()
                .success(false)
                .errors(null)
                .responseCode(ex.getErrorCode())
                .message(ex.getMessage())
                .timestamp(LocalDateTime.now())
                .build();
    }

    public static ApiGenericResponse<Object> buildGenericErrorResponse(Throwable ex) {
        return ApiGenericResponse.builder()
                .success(false)
                .errors(null)
                .responseCode("LINKTIC_SERVICE_GENERIC_ERROR")
                .message("Ha ocurrido un error al consumir el servicio")
                .timestamp(LocalDateTime.now())
                .build();
    }

}
