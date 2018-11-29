package com.example.demo.handler;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.multipart.MaxUploadSizeExceededException;

@ControllerAdvice
@Controller
public class HandlerException{

    @ExceptionHandler(value = MaxUploadSizeExceededException.class)
    public String uploadFileException(Model model){
        model.addAttribute("msg", "MaxUploadSizeException");
        return "error";
    }
}
