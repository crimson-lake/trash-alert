package pl.zielinska.web.controller.outdoorSearch;

import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.zielinska.model.domain.User;
import pl.zielinska.outdoor.dto.LocationDto;
import pl.zielinska.outdoor.service.AdService;
import pl.zielinska.outdoor.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.security.Principal;
import java.util.Optional;

@Slf4j
@Controller
public class CustomLocationController {

    @Autowired
    private UserService userService;

    @Autowired
    private AdService adService;

    @GetMapping("/new-location-form")
    public String newLocationForm(Model model) {
        model.addAttribute("newLocation", new LocationDto());
        return "fragments/new-location :: locationModal";
    }

    @PostMapping("/")
    public String addNewLocation(@Valid @ModelAttribute("newLocation") LocationDto locationDto,
                                 @RequestParam("page") Optional<Integer> page,
                                 @RequestParam("size") Optional<Integer> size,
                                 BindingResult bindingResult,
                                 Model model,
                                 HttpServletRequest request) throws JsonProcessingException {
        Principal principal = request.getUserPrincipal();
        if (principal == null) {
            return "login";
        }
        if (bindingResult.hasErrors()) {
            log.debug("New location form has errors");
            int currentPage = page.orElse(1) - 1;
            int pageSize = size.orElse(5);
            Pageable pageable = PageRequest.of(currentPage, pageSize);
            final User activeUser = userService.findByUsername(principal.getName());
            model.addAttribute("username", activeUser.getUsername());
            model.addAttribute("locations", activeUser.getLocations());
            model.addAttribute("city", activeUser.getDefaultCity());
            model.addAttribute("ads", adService.findAllDto(pageable));
            model.addAttribute("error", true);
            return "index";
        }
        final User activeUser = userService.findByUsername(principal.getName());
        userService.addNewLocationToUser(activeUser, locationDto);
        return "redirect:/";
    }
}
