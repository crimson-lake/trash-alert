package pl.zielinska.web.controller.outdoorSearch;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.zielinska.model.domain.Ad;
import pl.zielinska.outdoor.dto.AdDto;
import pl.zielinska.outdoor.service.AdService;
import pl.zielinska.outdoor.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.security.Principal;

@Controller
public class EditAdController {

    @Autowired
    private AdService adService;

    @Autowired
    private UserService userService;

    @GetMapping("/edit-ad")
    public String editAd(@RequestParam(name = "id") int id, Model model) {
        model.addAttribute("ad", adService.findByIdDto(id));
        return "fragments/edit-ad-form :: editAdForm";
    }

    @PostMapping("/my-ads")
    public String saveAd(@Valid @ModelAttribute("ad") AdDto adDto,
                         BindingResult bindingResult,
                         HttpServletRequest request,
                         Model model) {
        Principal principal = request.getUserPrincipal();
        if (principal == null) {
            return "login";
        }
        if (bindingResult.hasErrors()) {
            String activeUser = principal.getName();
            model.addAttribute("error", true);
            model.addAttribute("username", activeUser);
            model.addAttribute("myads", userService.usersAdsDto(activeUser));
            return "my-ads";
        }
        Ad ad = adService.findById(adDto.getId());
        ad.setTitle(adDto.getTitle());
        ad.setDetails(adDto.getDetails());
        ad.setCity(adDto.getCity());
        ad.setStreet(ad.getStreet());
        adService.save(ad);
        return "redirect:my-ads";
    }
}
