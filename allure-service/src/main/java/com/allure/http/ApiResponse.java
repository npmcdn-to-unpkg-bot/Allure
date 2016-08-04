package com.allure.http;

import com.google.gson.Gson;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 8/2/2016.
 */
public class ApiResponse {

    private int status;

    private List<Error> filedErrors;

    private List<Error> globalErrors;

    private Object result;

    private ApiResponse() {

    }

    public int getStatus() {
        return status;
    }

    public List<Error> getFiledErrors() {
        return filedErrors;
    }

    public List<Error> getGlobalErrors() {
        return globalErrors;
    }

    public Object getResult() {
        return result;
    }

    public String toJson() {
        return new Gson().toJson(this);
    }

    public static class Builder {
        private Status status = Status.Success;
        private List<Error> fieldErrors = new ArrayList<>();
        private List<Error> globalErrors = new ArrayList<>();
        private Object result;

        public Builder status(Status status) {
            Assert.notNull(status);
            this.status = status;
            return this;
        }

        public Builder success() {
            this.status = Status.Success;
            return this;
        }

        public Builder exception() {
            this.status = Status.Exception;
            return this;
        }

        public Builder filedError(Error error) {
            Assert.notNull(error);
            if (status != Status.Success) {
                status = Status.Exception;
            }
            this.fieldErrors.add(error);
            return this;
        }

        public Builder filedError(String code, String message) {
            Error error = new Error(code, message);
            return filedError(error);
        }


        public Builder globalError(Error error) {
            Assert.notNull(error);
            if (status != Status.Success) {
                status = Status.Exception;
            }
            this.globalErrors.add(error);
            return this;
        }

        public Builder globalError(String code, String message) {
            Error error = new Error(code, message);
            return globalError(error);
        }

        public Builder result(Object result) {
            this.result = result;
            return this;
        }

        public ApiResponse build() {
            ApiResponse apiResponse = new ApiResponse();
            apiResponse.status = status.code;
            apiResponse.filedErrors = fieldErrors.isEmpty() ? null : fieldErrors;
            apiResponse.globalErrors = globalErrors.isEmpty() ? null : globalErrors;
            apiResponse.result = result;
            return apiResponse;
        }

    }


    public enum Status {
        Success(0),
        Exception(1);
        private int code;

        Status(int code) {
            this.code = code;
        }
    }

    public static class Error {
        private String code;

        private String message;

        public Error(String code, String message) {
            this.code = code;
            this.message = message;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }
    }

}
