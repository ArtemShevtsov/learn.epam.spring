package learn.spring.web.controllers;

import learn.spring.core.entity.User;
import learn.spring.core.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import static org.springframework.web.bind.annotation.RequestMethod.*;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.Arrays;
import java.util.Collections;

@Controller
@RequestMapping("users")
public class UserController {
    @Autowired
    UserService userService;

    @RequestMapping(value = "by-name/{name}", method = GET)
    public ModelAndView getUsersByNameView(@PathVariable String name){
        ModelAndView mv = new ModelAndView("usersByAttributeView");
        if(name != null && name.trim().length() > 0){
            mv.addObject("attribute", "name");
            mv.addObject("attributeName", name);
            mv.addObject("usersModel", userService.getUsersByName(name));
        }
        return mv;
    }

    @RequestMapping(value = "by-id/{id}", method = GET)
    public ModelAndView getUserByIdView(@PathVariable int id){
        ModelAndView mv = new ModelAndView("usersByAttributeView");
        if(id >= 0){
            mv.addObject("attribute", "ID");
            mv.addObject("attributeName", id);
            User user = userService.getUserById(id);
            mv.addObject("usersModel", Arrays.asList(user));
        }
        return mv;
    }

    @RequestMapping(value = "by-email", method = GET)
    public ModelAndView getUserByIdView(@RequestParam String mail){
        ModelAndView mv = new ModelAndView("usersByAttributeView");
        if(mail != null && mail.trim().length() > 0){
            mv.addObject("attribute", "e-mail");
            mv.addObject("attributeName", mail);
            User user = userService.getUserByEmail(mail);
            mv.addObject("usersModel", Arrays.asList(user));
        }
        return mv;
    }
}
