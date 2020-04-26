package pl.zielinska.web.controller.outdoorSearch;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import pl.zielinska.outdoor.service.UserService;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;

@Controller
public class MyAdsController {

    @Autowired
    private UserService userService;

    @GetMapping("/outdoor-search/my-ads")
    public String showMyAds(Model model, HttpServletRequest request) {
        Principal principal = request.getUserPrincipal();
        if (principal == null) {
            return "login";
        }
        String activeUser = principal.getName();
        model.addAttribute("username", activeUser);
        model.addAttribute("myads", userService.usersAdsDto(activeUser));
        return "my-ads";
    }
}
