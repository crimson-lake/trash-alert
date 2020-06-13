package pl.zielinska.web.controller.outdoorSearch;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.zielinska.outdoor.service.AdService;
import pl.zielinska.outdoor.service.UserService;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;

@Controller
public class DeleteAdController {

    @Autowired
    private AdService adService;

    @Autowired
    private UserService userService;

    @GetMapping("/outdoor-search/my-ads/confirm-delete")
    public String confirmDeleting(@RequestParam(name = "id") int id, Model model) {
        model.addAttribute("id", id);
        return "fragments/confirm-delete :: confirmDeleteModal";
    }

    @GetMapping("/outdoor-search/my-ads/delete")
    public String deleteAd(@RequestParam(name = "id") int id,
                           Model model,
                           HttpServletRequest request) {
        adService.deleteById(id);
        Principal principal = request.getUserPrincipal();
        if (principal == null) {
            return "login";
        }
        String activeUser = principal.getName();
        model.addAttribute("username", activeUser);
        model.addAttribute("myads", userService.usersAdsDto(activeUser));
        return "fragments/board-my-ads :: board-my-ads";
    }
}
