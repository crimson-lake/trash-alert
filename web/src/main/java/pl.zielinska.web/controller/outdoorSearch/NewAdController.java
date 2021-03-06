package pl.zielinska.web.controller.outdoorSearch;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.zielinska.model.domain.Ad;
import pl.zielinska.model.domain.Photo;
import pl.zielinska.model.domain.User;
import pl.zielinska.outdoor.dto.AdDto;
import pl.zielinska.outdoor.service.AdService;
import pl.zielinska.outdoor.service.GeoService;
import pl.zielinska.outdoor.service.TagService;
import pl.zielinska.outdoor.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.IOException;
import java.security.Principal;

@Slf4j
@Controller
public class NewAdController {

    @Autowired
    private UserService userService;

    @Autowired
    private AdService adService;

    @Autowired
    private GeoService geoService;

    @Autowired
    private TagService tagService;

    @GetMapping("/new-ad")
    public String newAd(Model model, HttpServletRequest request) {
        Principal principal = request.getUserPrincipal();
        if (principal == null) {
            return "login";
        }
        model.addAttribute("newAd", new AdDto());
        model.addAttribute("username", request.getUserPrincipal().getName());
        return "new-ad";
    }

    @PostMapping("/new-ad")
    public String newAd(@Valid @ModelAttribute("newAd") AdDto adDto,
                        BindingResult bindingResult,
                        HttpServletRequest request,
                        @RequestParam(name = "tags") String tags) throws IOException {
        if (bindingResult.hasErrors()) {
            log.debug("New ad form has errors");
            return "new-ad";
        }
        final String activeUser = request.getUserPrincipal().getName();
        log.debug("active user:" + activeUser);

        User theUser = userService.findByUsername(activeUser);
        Ad theAd = adService.publishNewAd(adDto, theUser);
        theAd.setTags(tagService.makeTags(tags));
        Photo.process(adDto.getPhotos(), theAd);
        adService.save(theAd);
        userService.bindAdWithUser(theUser, theAd);

        return "redirect:/";
    }
}
