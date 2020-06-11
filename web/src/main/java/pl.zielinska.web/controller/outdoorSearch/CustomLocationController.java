package pl.zielinska.web.controller.outdoorSearch;

import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import pl.zielinska.model.domain.User;
import pl.zielinska.outdoor.dto.LocationDto;
import pl.zielinska.outdoor.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.security.Principal;

@Slf4j
@Controller
public class CustomLocationController {

    @Autowired
    private UserService userService;

    @GetMapping("/outdoor-search/new-location-form")
    public String newLocationForm(Model model) {
        model.addAttribute("newLocation", new LocationDto());
        return "fragments/new-location :: locationModal";
    }

    @PostMapping("/outdoor-search")
    public String addNewLocation(@Valid @ModelAttribute("newLocation") LocationDto locationDto,
                                 BindingResult bindingResult,
                                 HttpServletRequest request) throws JsonProcessingException {
        if (bindingResult.hasErrors()) {
            log.debug("New location form has errors");
            return "redirect:/outdoor-search";
        }
        Principal principal = request.getUserPrincipal();
        if (principal == null) {
            return "login";
        }
        final User activeUser = userService.findByUsername(principal.getName());
        userService.addNewLocationToUser(activeUser, locationDto);
        return "redirect:/outdoor-search";
    }
}
