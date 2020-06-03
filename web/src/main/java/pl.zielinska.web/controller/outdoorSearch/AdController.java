package pl.zielinska.web.controller.outdoorSearch;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import pl.zielinska.outdoor.dto.AdDto;
import pl.zielinska.outdoor.service.AdService;

@Controller
public class AdController {

    @Autowired
    private AdService adService;

    @GetMapping("/outdoor-search/ad/{id}")
    public String ad(@PathVariable(name="id") int id, Model model) {
        AdDto ad = adService.findByIdDto(id);
        model.addAttribute("ad", ad);
        return "fragments/ad-modal :: adModal";
    }
}
