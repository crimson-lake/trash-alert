package pl.zielinska.web.controller.outdoorSearch;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.zielinska.outdoor.service.AdService;

@Controller
public class FilterByTagsController {

    @Autowired
    private AdService adService;

    @GetMapping("/outdoor-search/filter")
    public String sortedAds(@RequestParam(name = "filterBy") String filterBy, Model model) {
        model.addAttribute("ads", adService.findByTagsName(filterBy));
        return "fragments/board :: board";
    }
}
