package sg.edu.iss.test.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import sg.edu.iss.test.model.User;
import sg.edu.iss.test.service.UserServiceImplementation;

@Controller
@RequestMapping("")
public class LoginController {
    @Autowired
    private UserServiceImplementation userServices;

    @RequestMapping(path = "")
    public String login(Model model) {
        User u = new User();
        model.addAttribute("user", u);
        return "index";
    }

}
