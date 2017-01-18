package learn.spring.web.controllers;

import learn.spring.core.entity.Event;
import learn.spring.core.entity.User;
import learn.spring.core.services.EventService;
import learn.spring.core.services.UserService;
import learn.spring.exception.UnprocessableFileFormatExcepption;
import learn.spring.utils.JsonFilesParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

/**
 * Created by aftor on 07.01.17.
 */
@Controller
public class UploadController {
    @Autowired
    UserService userService;

    @Autowired
    EventService eventService;

    @Autowired
    PasswordEncoder passwordEncoder;

    @RequestMapping(value = "upload", method = GET)
    public ModelAndView showUploadPage(){
//        @ModelAttribute("ID") int id
//        System.out.println("\n\n\n\n------------showUploadPage--" + id + "--------\n\n\n\n");
        System.out.println("\n\n\n\n------------showUploadPage----------\n\n\n\n");
        ModelAndView mv = new ModelAndView("uploadPage");
        return mv;
    }

    @ModelAttribute("ID")
    public int modelAttr(@RequestParam(value = "id", required = false) Integer id) {
        System.out.println("\n\n\n\n------------modelAttr---" + id + "-------\n\n\n\n");
        return id;
    }

    @RequestMapping(value = "upload", method = POST)
    public ModelAndView uploadFile(@RequestParam MultipartFile users, @RequestParam MultipartFile events) throws UnprocessableFileFormatExcepption, IOException {
        ModelAndView mv = new ModelAndView("successUploadPage");
        if(
            !users.isEmpty() && !users.getContentType().equals(MimeTypeUtils.APPLICATION_JSON_VALUE) ||
            !events.isEmpty() && !events.getContentType().equals(MimeTypeUtils.APPLICATION_JSON_VALUE)
        ){
            throw new UnprocessableFileFormatExcepption("File should be a JSON File!");
        }

        JsonFilesParser jsonFilesParser = new JsonFilesParser();

        if(!users.isEmpty()){
            List<User> userList = jsonFilesParser.parseUsers(users, passwordEncoder);
            userList.forEach(user -> {
                userService.register(user);
            });
        }
        if(!events.isEmpty()){
            List<Event> eventList = jsonFilesParser.parseEvents(events);
            eventList.forEach(event -> {
                eventService.create(event);
            });
        }
        return mv;
    }
}
