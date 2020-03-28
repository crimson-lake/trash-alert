package pl.zielinska.trashAlert.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import pl.zielinska.trashAlert.DTO.AdDto;
import pl.zielinska.trashAlert.domain.Ad;
import pl.zielinska.trashAlert.domain.User;
import pl.zielinska.trashAlert.service.AdService;
import pl.zielinska.trashAlert.service.GeoService;
import pl.zielinska.trashAlert.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@Slf4j
@Controller
public class NewAdController {

    @Autowired
    private UserService userService;

    @Autowired
    private AdService adService;

    @Autowired
    private GeoService geoService;


    @GetMapping("/new-ad")
    public String newAd(Model model, HttpServletRequest request) {
        model.addAttribute("newAd", new AdDto());
        model.addAttribute("username", request.getUserPrincipal().getName());
        return "new-ad";
    }

    @PostMapping("/new-ad")
    public String newAd(@Valid @ModelAttribute("newAd") AdDto adDto, BindingResult bindingResult, HttpServletRequest request) {
        if (bindingResult.hasErrors()) {
            log.debug("New ad form has errors");
            return "new-ad";
        }
        final String activeUser = request.getUserPrincipal().getName();
        log.debug("active user:" + activeUser);

        User theUser = userService.findByUsername(activeUser);
        Ad theAd = adService.publishNewAd(adDto, theUser);
        userService.bindAdWithUser(theUser, theAd);
        geoService.adNewCoordinates(theAd);

        return "redirect:/";
    }
}
