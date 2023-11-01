package com.service360.group50.exception;

import com.service360.group50.auth.AuthenticationResponse;
import com.service360.group50.message.ResponseMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.multipart.MaxUploadSizeExceededException;

public class FilesUploadException {
    @ExceptionHandler(MaxUploadSizeExceededException.class)
    public ResponseEntity<AuthenticationResponse> handleMaxSizeException( MaxUploadSizeExceededException exc) {
        return ResponseEntity
                .status( HttpStatus.EXPECTATION_FAILED)
                .body(new AuthenticationResponse("One or more files are too large!"));
    }
}