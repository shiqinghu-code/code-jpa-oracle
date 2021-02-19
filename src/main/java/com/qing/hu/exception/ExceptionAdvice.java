package com.qing.hu.exception;


import java.util.List;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.ValidationException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.qing.hu.common.Result;
 
/**
 * 异常处理 
 * spring mvc提供了ControllerAdvice注解对异常进行统一的处理
 * @author Admin
 * @date 2019年9月2日18:12:59
 */
@ControllerAdvice
public class ExceptionAdvice {
 
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    
    @ExceptionHandler(ValidationException.class)
    @ResponseBody
    public Result handle(ValidationException exception) {
    	StringBuilder msg=new StringBuilder();
        if(exception instanceof ConstraintViolationException){
            ConstraintViolationException exs = (ConstraintViolationException) exception;

            Set<ConstraintViolation<?>> violations = exs.getConstraintViolations();
            for (ConstraintViolation<?> item : violations) {
                //打印验证不通过的信息
                //System.out.println("111111111111111::::::::::"+item.getMessage());
            	msg.append(item.getMessage()+",");
            }
        }
        String msgs=msg.toString();
        if(!"".equals(msgs)) {
        	msgs=msgs.substring(0, msgs.length()-1);
        }
        return Result.failed(msgs);
    }
 
    /**
     * 这里统一处理参数异常可根据自己需要封装返回信息
     * 当不符合注解上的验证规则，会被该方法捕获到
     * @param ex 方法参数异常
     * @return BaseResponse
     */
    @ExceptionHandler
    @ResponseBody
    public Result handleParamsException(MethodArgumentNotValidException ex){
        BindingResult bindingResult = ex.getBindingResult();
        List<FieldError> errors = bindingResult.getFieldErrors();
        StringBuilder errorMsg = new StringBuilder();
        errorMsg.append("param exception ");
        for (FieldError error : errors){
            errorMsg.append(error.getField());
            errorMsg.append(":");
            errorMsg.append(error.getDefaultMessage());
            errorMsg.append("; ");
        }
 
        //return new BaseResponse(BaseResponse.PARAM_ERROR,errorMsg.toString());
        return Result.failed(errorMsg.toString());
    }
 
    /**
     * 处理方法的顶级异常信息，可以处理事务等等
     * 比如我controller里面的1/0异常就会被该方法捕获到
     * controller里面就不再捕获了
     * @param ex 异常
     * @return BaseResponse
     */
    @ResponseBody
    @ExceptionHandler(value = Exception.class)
    public Result handleParamsException(Exception ex){
        logger.error("system error:{}",ex);
       // return new BaseResponse(BaseResponse.SYSTEM_ERROR,ex.getMessage());
        return Result.failed(ex.getMessage());
    }
}
