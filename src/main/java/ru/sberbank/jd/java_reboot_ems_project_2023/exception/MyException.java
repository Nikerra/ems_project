package ru.sberbank.jd.java_reboot_ems_project_2023.exception;

import org.springframework.validation.BindException;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.Objects;

@ControllerAdvice
@Validated
public class MyException {

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    @ResponseBody
    public ModelAndView handleMethodArgumentNotValidException(MethodArgumentNotValidException exception) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("error", exception.getFieldError());
        modelAndView.setViewName("error");
        return modelAndView;
        }

    @ExceptionHandler(value = BindException.class)
    public ModelAndView handleBindException(BindException exception) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("error", Objects.requireNonNull(exception.getFieldError()).getDefaultMessage());
        modelAndView.setViewName("error");
        return modelAndView;
    }

    @ExceptionHandler(value = IllegalArgumentException.class)
    public ModelAndView handleIllegalArgumentException(IllegalArgumentException exception) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("error", exception.getMessage());
        modelAndView.setViewName("error");
        return modelAndView;
    }

}


