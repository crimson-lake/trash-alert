package pl.zielinska.trashAlert.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.zielinska.trashAlert.entity.Ad;
import pl.zielinska.trashAlert.entity.User;
import pl.zielinska.trashAlert.service.UserService;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@Slf4j
@Controller
public class WebController {

    @Autowired
    private UserService userService;

    @RequestMapping("/")
    public String home() {
        return "index";
    }

    @GetMapping("/new-ad")
    public String newAd(Model model) {
        model.addAttribute("newAd", new Ad());
        model.addAttribute("username", new String());
        return "new-ad";
    }

    @PostMapping("/new-ad")
    public String newAd(Ad theAd, HttpServletRequest request) {
        theAd.setCreated(new Date());
        log.debug(theAd.toString());

        final String activeUser = request.getUserPrincipal().getName();
        User theUser = userService.findByUsername(activeUser);
        log.debug("active user:" + activeUser);

        theUser.addNewAd(theAd);
        userService.save(theUser);

        return "redirect:/";
    }
}
