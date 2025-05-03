package com.example.learningproject.utils;

import java.util.ArrayList;
import java.util.List;

public class ApiResponse<T> {
    private T data;
    private List<String> errors = new ArrayList<>();
    private List<String> success = new ArrayList<>();
    private boolean successful;
    private String message;

    public ApiResponse(T data){
        this.data = data;
    }
    public ApiResponse(){

    }
    public T getData() {
        return data;
    }
    public void setData(T data) {
        this.data = data;
    }
    public void addError(String error){
        this.errors.add(error);
    }
    public void addSuccess(String success){
        this.success.add(success);
    }
    public void setSuccessful(boolean successful){
        this.successful = successful;
    }
    public boolean isSuccessful(){
        return this.successful;
    }
    public List<String> getErrors(){
        return this.errors;
    }
    public List<String> getSuccess(){
        return this.success;
    }
    public int getErrorsCount(){
        return this.errors.size();
    }
    public int getSuccessCount(){
        return this.success.size();
    }
    public String getMessage(){
        return this.message;
    }
    public void setMessage(String message){
        this.message = message;
    }
}
