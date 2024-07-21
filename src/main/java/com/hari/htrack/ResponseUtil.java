package com.hari.htrack;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ResponseUtil {

    // Generic success response
    public static <T> ResponseEntity<BaseResponse<T>> success(T data) {
        BaseResponse<T> response = new BaseResponse<>(HttpStatus.OK.value(), "Success", data);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    // Success response with no data
    public static ResponseEntity<BaseResponse<Void>> success() {
        return success(null);
    }

    // Generic error response
    public static <T> ResponseEntity<BaseResponse<T>> error(HttpStatus status, String message) {
        BaseResponse<T> response = new BaseResponse<>(status.value(), message, null);
        return new ResponseEntity<>(response, status);
    }

    // Error response with 400 Bad Request
    public static <T> ResponseEntity<BaseResponse<T>> badRequest(String message) {
        return error(HttpStatus.BAD_REQUEST, message);
    }
}