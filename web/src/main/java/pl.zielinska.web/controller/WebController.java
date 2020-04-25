package pl.zielinska.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.zielinska.trashAlert.domain.User;
import pl.zielinska.trashAlert.service.AdService;
import pl.zielinska.trashAlert.service.UserService;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;

@Controller
public class WebController {

    @Autowired
    private UserService userService;

    @Autowired
    private AdService adService;

    @RequestMapping("/")
    public String home(Model model, HttpServletRequest request) {
        Principal principal = request.getUserPrincipal();
        if (principal == null) {
            return "login";
        }
        final User activeUser = userService.findByUsername(principal.getName());
        model.addAttribute("username", activeUser.getUsername());
        model.addAttribute("city", activeUser.getDefaultCity());
        model.addAttribute("ads", adService.findAllDto());
        return "index";
    }
}
