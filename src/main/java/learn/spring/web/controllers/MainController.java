package learn.spring.web.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

/**
 * Created by aftor on 08.01.17.
 */
@Controller
public class MainController {
    @RequestMapping(method = GET)
    public ModelAndView home(){
        return new ModelAndView("home");
    }
}
