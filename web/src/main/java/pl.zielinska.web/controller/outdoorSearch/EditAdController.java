package pl.zielinska.web.controller.outdoorSearch;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.zielinska.model.domain.Ad;
import pl.zielinska.model.domain.Photo;
import pl.zielinska.outdoor.dto.AdDto;
import pl.zielinska.outdoor.service.AdService;
import pl.zielinska.outdoor.service.TagService;
import pl.zielinska.outdoor.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.IOException;
import java.security.Principal;

@Slf4j
@Controller
@Scope("session")
public class EditAdController {

    @Autowired
    private AdService adService;

    @Autowired
    private UserService userService;

    @Autowired
    private TagService tagService;

    @GetMapping("/edit-ad")
    public String editAd(@RequestParam(name = "id") int id, Model model) {
        model.addAttribute("ad", adService.findByIdDto(id));
        return "fragments/edit-ad-form :: editAdForm";
    }

    @PostMapping("/my-ads")
    public String saveAd(@Valid @ModelAttribute("ad") AdDto adDto,
                         BindingResult bindingResult,
                         HttpServletRequest request,
                         Model model,
                         @RequestParam(name = "editTags") String tags,
                         @RequestParam(name = "delete", required = false) int[] delete) throws IOException {
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
        updateAdWith(adDto, tags);
        return "redirect:my-ads";
    }

    private Ad updateAdWith(AdDto adDto, String tags) throws IOException {
        Ad ad = adService.findById(adDto.getId());
        ad.setTitle(adDto.getTitle());
        ad.setDetails(adDto.getDetails());
        ad.updateAdress(adDto.getCity(), adDto.getStreet());
        Photo.process(adDto.getPhotos(), ad);
        ad.setTags(tagService.makeTags(tags));
        adService.save(ad);
        return ad;
    }
}
