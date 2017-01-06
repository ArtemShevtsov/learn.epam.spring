package learn.spring.web.controllers;

import learn.spring.core.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import static org.springframework.web.bind.annotation.RequestMethod.*;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("users")
public class UserController {
    @Autowired
    UserService userService;

    @RequestMapping(value = "by-name/{name}", method = GET)
    public ModelAndView getUsersByNameView(@PathVariable String name, @RequestParam String output){
        if(output.equals("pdf")){
            return new ModelAndView("testPdfView");
        }

        ModelAndView mv = new ModelAndView("usersByAttributeView");
        if(name != null){
            mv.addObject("attribute", "name");
            mv.addObject("attributeName", name);
            mv.addObject("usersModel", userService.getUsersByName(name));
        }
        return mv;
    }
}
