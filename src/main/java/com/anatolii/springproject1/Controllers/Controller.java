package com.anatolii.springproject1.Controllers;

import org.springframework.boot.web.servlet.server.Jsp;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping("/")
public class Controller {
    @GetMapping("/")
    public ModelAndView getTasks(ModelAndView modelAndView) {
        return null;
    }
}
