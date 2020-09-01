package br.com.awm.pantherproducts.part01_products.exceptions.handle;

import br.com.awm.pantherproducts.part01_products.enums.ErrorCode;
import br.com.awm.pantherproducts.part01_products.exceptions.ExecutionNotAuthorizedException;
import br.com.awm.pantherproducts.part01_products.exceptions.UnableToGetKeyListProductException;
import br.com.awm.pantherproducts.part01_products.exceptions.UnexpectedErrorException;
import org.springframework.beans.TypeMismatchException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

@ControllerAdvice
public class ProductExceptionHandler extends ResponseEntityExceptionHandler {
    @Override
    @ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
    protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        return ResponseEntity
                .status(HttpStatus.METHOD_NOT_ALLOWED)
                .body(HttpErrorResponseFactory.build(ErrorCode.METHOD_NOT_SUPPORTED));
    }

    @Override
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    protected ResponseEntity<Object> handleTypeMismatch(TypeMismatchException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        return ResponseEntity
                .badRequest()
                .body(HttpErrorResponseFactory.build(ErrorCode.INVALID_PATH_VARIABLE_TYPE));
    }

    @Override
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        return ResponseEntity
                .badRequest()
                .body(HttpErrorResponseFactory.build(ErrorCode.INVALID_PARAMETER));
    }

    @Override
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        HttpErrorResponse errorResponse = HttpErrorResponseFactory.build(ErrorCode.VALIDATION_FAILED_FOR_PARAMETER);
        for (FieldError error : ex.getBindingResult().getFieldErrors()) {
            errorResponse.getErrors().add(error.getField() + ": " + error.getDefaultMessage());
        }
        for (ObjectError error : ex.getBindingResult().getGlobalErrors()) {
            errorResponse.getErrors().add(error.getObjectName() + ": " + error.getDefaultMessage());
        }
        return ResponseEntity
                .badRequest()
                .body(errorResponse);
    }

    @Override
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    protected ResponseEntity<Object> handleMissingServletRequestParameter(MissingServletRequestParameterException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        String error = ex.getParameterName() + " é  obrigatório.";
        HttpErrorResponse errorResponse = HttpErrorResponseFactory.build(ErrorCode.MISSING_PARAMETER);
        errorResponse.setErrors(Arrays.asList(error));

        return ResponseEntity
                .badRequest()
                .body(errorResponse);

    }

    @ExceptionHandler(ExecutionNotAuthorizedException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public ResponseEntity<HttpErrorResponse> handleExecutionNotAuthorizedException(ExecutionNotAuthorizedException e, HttpServletRequest request) {
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(HttpErrorResponseFactory.build(
                e.getErrorCode(), e.getMessage())
        );
    }

    @ExceptionHandler(UnableToGetKeyListProductException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<HttpErrorResponse> handleUnableToGetKeyListProductException(UnableToGetKeyListProductException e, HttpServletRequest request) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(HttpErrorResponseFactory.build(
                e.getErrorCode(), e.getMessage())
        );
    }

    @ExceptionHandler(UnexpectedErrorException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<HttpErrorResponse> handleUnexpectedErrorException(UnexpectedErrorException e, HttpServletRequest request) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(HttpErrorResponseFactory.build(
                e.getErrorCode(), e.getMessage())
        );
    }

}
