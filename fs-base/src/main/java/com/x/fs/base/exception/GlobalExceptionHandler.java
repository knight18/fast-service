package com.x.fs.base.exception;

import com.x.fs.common.dto.ResponseResultDTO;
import com.x.fs.common.exception.FsServiceException;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * 全局异常处理
 * @author x
 */
@ControllerAdvice
public class GlobalExceptionHandler {
    @ResponseBody
    @ExceptionHandler(value = FsServiceException.class)
    public ResponseResultDTO handle(FsServiceException e) {
        if (e.getErrorCode() != null) {
            return ResponseResultDTO.failed(e.getErrorCode());
        }
        return ResponseResultDTO.failed(e.getMessage());
    }

    @ResponseBody
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ResponseResultDTO handleValidException(MethodArgumentNotValidException e) {
        return getResponseResultDTO(e.getBindingResult());
    }

    @ResponseBody
    @ExceptionHandler(value = BindException.class)
    public ResponseResultDTO handleValidException(BindException e) {
        return getResponseResultDTO(e.getBindingResult());
    }

    private ResponseResultDTO getResponseResultDTO(BindingResult bindingResult2) {
        BindingResult bindingResult = bindingResult2;
        String message = null;
        if (bindingResult.hasErrors()) {
            FieldError fieldError = bindingResult.getFieldError();
            if (fieldError != null) {
                message = fieldError.getField()+fieldError.getDefaultMessage();
            }
        }
        return ResponseResultDTO.validateFailed(message);
    }

}
