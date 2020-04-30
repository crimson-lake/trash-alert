package pl.zielinska.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import pl.zielinska.outdoor.domain.User;
import pl.zielinska.outdoor.service.UserService;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;

@Controller
public class MainPageController {

    @Autowired
    private UserService userService;

    @GetMapping("/")
    public String mainPage(Model model, HttpServletRequest request) {
        final Principal principal = request.getUserPrincipal();
        if (principal != null) {
            model.addAttribute("username", principal.getName());
        }
        return "main-page";
    }
}
