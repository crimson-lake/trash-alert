package pl.zielinska.trashAlert.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import pl.zielinska.trashAlert.domain.Ad;
import pl.zielinska.trashAlert.domain.User;
import pl.zielinska.trashAlert.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.time.LocalDateTime;

@Slf4j
@Controller
public class WebController {

    @Autowired
    private UserService userService;

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
    }

    @RequestMapping("/")
    public String home() {
        return "index";
    }

    @GetMapping("/new-ad")
    public String newAd(Model model) {
        model.addAttribute("newAd", new Ad());
        return "new-ad";
    }

    @PostMapping("/new-ad")
    public String newAd(@Valid @ModelAttribute("newAd") Ad theAd, BindingResult bindingResult, HttpServletRequest request) {
        if (bindingResult.hasErrors()) {
            log.debug("New ad form has errors");
            return "new-ad";
        }
        theAd.setCreated(LocalDateTime.now());
        log.debug(theAd.toString());

        final String activeUser = request.getUserPrincipal().getName();
        User theUser = userService.findByUsername(activeUser);
        log.debug("active user:" + activeUser);

        theUser.addNewAd(theAd);
        userService.save(theUser);

        return "redirect:/";
    }
}
