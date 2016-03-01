package learn.spring.web.controllers;

import learn.spring.core.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import static org.springframework.web.bind.annotation.RequestMethod.*;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class UserController {
    @Autowired
    UserService userService;

    @RequestMapping(value = "usersByName", method = GET)
    public ModelAndView getUsersByNameView(@RequestParam String name){
        ModelAndView mv = new ModelAndView("usersByNameView");
        if(name != null){
            mv.addObject("name", name);
            mv.addObject("usersModel", userService.getUsersByName(name));
        }
        return mv;
    }
}
