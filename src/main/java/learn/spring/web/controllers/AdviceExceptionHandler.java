package learn.spring.web.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class AdviceExceptionHandler {

    @ExceptionHandler
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ModelAndView handleException(Exception ex){
        ModelAndView mv = new ModelAndView();
        mv.addObject("ex", ex);
        mv.setViewName("default-error");
        return mv;
    }
}