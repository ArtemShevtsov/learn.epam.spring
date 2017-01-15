package learn.spring.web.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

/**
 * Created by aftor on 08.01.17.
 */
@Controller
public class MainController {
    @RequestMapping(method = GET, value = {"/", "index", "home"})
    public ModelAndView home(){
        return new ModelAndView("home");
    }

    @RequestMapping(method = GET, value = "accessDenied")
    public ModelAndView accessDenied(){
        return new ModelAndView("accessDenied");
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public ModelAndView login(
            @RequestParam(value = "error", required = false) String error,
            @RequestParam(value = "logout", required = false) String logout,
            ModelAndView mv) {
        if (error != null) {
            mv.addObject("error", "Wrong username or password");
        }

        if (logout != null) {
            mv.addObject("msg", "You logged out succesfully");
        }
        mv.setViewName("login");
        return mv;
    }
}
