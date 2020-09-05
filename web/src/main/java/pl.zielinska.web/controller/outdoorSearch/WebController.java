package pl.zielinska.web.controller.outdoorSearch;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import pl.zielinska.model.domain.SortAndFilterArguments;
import pl.zielinska.model.domain.User;
import pl.zielinska.outdoor.service.AdService;
import pl.zielinska.outdoor.service.UserService;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;

@Controller
@Scope("session")
public class WebController {

    @Autowired
    private UserService userService;

    @Autowired
    private AdService adService;

    @Autowired
    private SortAndFilterArguments sortAndFilterArgs;

    @GetMapping("/")
    public String home(Model model, HttpServletRequest request) {
        Principal principal = request.getUserPrincipal();
        if (principal == null) {
            return "login";
        }
        final User activeUser = userService.findByUsername(principal.getName());
        model.addAttribute("filtered", sortAndFilterArgs.isFiltered());
        model.addAttribute("username", activeUser.getUsername());
        model.addAttribute("locations", activeUser.getLocations());
        model.addAttribute("city", activeUser.getDefaultCity());
        model.addAttribute("ads", adService.findAllDto(sortAndFilterArgs));
        return "index";
    }
}
