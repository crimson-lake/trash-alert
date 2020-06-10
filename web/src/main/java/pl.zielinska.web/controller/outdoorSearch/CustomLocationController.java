package pl.zielinska.web.controller.outdoorSearch;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CustomLocationController {

    @GetMapping("/outdoor-search/new-location-form")
    public String newLocationForm(Model model) {
        return "fragments/new-location :: locationModal";
    }
}
