package sg.edu.iss.test.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import sg.edu.iss.test.model.User;
import sg.edu.iss.test.service.UserServiceImplementation;

import javax.servlet.http.HttpSession;

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

    @RequestMapping(path = "/authenticate")
    public String authenticate(@ModelAttribute("user") User user, Model model, HttpSession session) {
        if(userServices.authenticate(user))
        {
            User u = userServices.findUserByUserName(user.getUserName());
            session.setAttribute("usession", u);
            return "redirect:/inventory/list";
        }
        else
        	model.addAttribute("errormsg","wrong username or password");
            return "index";
    }

    @RequestMapping(path = "/logout")
    public String logout(HttpSession session) {
        session.removeAttribute("usession");
        return "logout";
    }
}
