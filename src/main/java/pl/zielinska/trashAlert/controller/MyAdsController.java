package pl.zielinska.trashAlert.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import pl.zielinska.trashAlert.service.UserService;

import javax.servlet.http.HttpServletRequest;

@Controller
public class MyAdsController {

    @Autowired
    private UserService userService;

    @GetMapping("/my-ads")
    public String showMyAds(Model model, HttpServletRequest request) {
        final String activeUser = request.getUserPrincipal().getName();

        model.addAttribute("username", activeUser);
        model.addAttribute("myads", userService.usersAdsDto(activeUser));
        return "my-ads";
    }
}
