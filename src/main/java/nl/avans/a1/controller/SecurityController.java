package nl.avans.a1.controller;

import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Controller
public class SecurityController {

    @GetMapping("/")
    public ModelAndView base(ModelAndView mav) {
        mav.setViewName("home");
        return mav;
    }

    @GetMapping("/home")
    public ModelAndView home(ModelAndView mav) {
        mav.setViewName("home");
        return mav;
    }

    @GetMapping("/login")
    public ModelAndView login(ModelAndView mav) {
        mav.setViewName("login");
        return mav;
    }

}
