package com.guet.qiusuo.fruittravel.config;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolationException;
import java.util.List;
import java.util.Locale;

import static java.lang.invoke.MethodHandles.lookup;
import static org.slf4j.LoggerFactory.getLogger;

@RestControllerAdvice
public class ExceptionHandleConfig {
    private static final Logger LOG = getLogger(lookup().lookupClass());

    @Autowired
    private MessageSource messageSource;

    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    public Response<Object> invalidArgument(ConstraintViolationException e, Locale locale) {
        Response<Object> response = new Response<>();
        response.setMsg(e.getMessage());
        return response;
    }

    @ExceptionHandler(value = Throwable.class)
    @ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR)
    public Response<Object> handle(Exception e, Locale locale) {
        LOG.error("", e);
        ErrorCode error = ErrorCode.UNKNOWN_ERROR;
        Response<Object> response = new Response<>();
        response.setCode(error.getCode());
        response.setMsg(error.getKey());

        response.setData(new ExceptionResponse(e));
        return response;
    }

    @ExceptionHandler(SystemException.class)
    public Response systemError(SystemException e) {
        Response response = new Response();
        response.setCode(e.getErrorCode().getCode());
        response.setMsg(messageSource.getMessage(e.getErrorCode().getKey(), null, "", null));
        return response;
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Response methodArgumentNotValidException(MethodArgumentNotValidException e) {
        StringBuilder sb = new StringBuilder();
        List<ObjectError> allErrors = e.getBindingResult().getAllErrors();
        allErrors.forEach(error -> sb.append(error.getDefaultMessage()));
        Response response = new Response();
        response.setCode(-1);
        response.setMsg(sb.toString());
        return response;
    }


    static class ExceptionResponse {
        private String trace;

        private String message;

        public ExceptionResponse(Exception e) {
//            this.trace = ExceptionUtils.getStackTrace(e);
            this.message = e.getMessage();
        }

        public String getTrace() {
            return trace;
        }

        public void setTrace(String trace) {
            this.trace = trace;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }
    }
}
