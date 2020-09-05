package pl.zielinska.web.controller.outdoorSearch;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.zielinska.model.domain.SortAndFilterArguments;
import pl.zielinska.model.domain.User;
import pl.zielinska.outdoor.dto.AdDto;
import pl.zielinska.outdoor.service.AdService;
import pl.zielinska.outdoor.service.UserService;
import pl.zielinska.web.utils.PaginationUtils;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.Optional;

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
    public String home(Model model,
                       HttpServletRequest request,
                       @RequestParam("page") Optional<Integer> page,
                       @RequestParam("size") Optional<Integer> size) {
        Principal principal = request.getUserPrincipal();
        if (principal == null) {
            return "login";
        }
        Pageable pageable = PaginationUtils.from(page, size, sortAndFilterArgs.getSortBy());
        final User activeUser = userService.findByUsername(principal.getName());
        model.addAttribute("filtered", sortAndFilterArgs.isFiltered());
        model.addAttribute("username", activeUser.getUsername());
        model.addAttribute("locations", activeUser.getLocations());
        model.addAttribute("city", activeUser.getDefaultCity());
        model.addAttribute("ads", adService.findAllDto(sortAndFilterArgs, pageable));
        return "index";
    }

    @GetMapping("/board")
    public String board(@RequestParam("page") Optional<Integer> page,
                        @RequestParam("size") Optional<Integer> size,
                        Model model) {
        Pageable pageable = PaginationUtils.from(page, size, sortAndFilterArgs.getSortBy());
        Page<AdDto> filteredAds = adService.findAllDto(sortAndFilterArgs, pageable);
        model.addAttribute("filtered", sortAndFilterArgs.isFiltered());
        model.addAttribute("ads", filteredAds);
        return "fragments/board :: board";
    }
}
