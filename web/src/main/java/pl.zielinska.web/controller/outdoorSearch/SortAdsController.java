package pl.zielinska.web.controller.outdoorSearch;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.zielinska.model.domain.SortingArgument;
import pl.zielinska.outdoor.service.AdService;

@Slf4j
@Controller
public class SortAdsController {

    @Autowired
    private AdService adService;

    @GetMapping("/outdoor-search/sort")
    public String sortedAds(@RequestParam(name = "sortBy") String sortArg, Model model) {
        SortingArgument sortBy = SortingArgument.valueOf(sortArg);
        model.addAttribute("ads", adService.findAllDto(Sort.by(sortBy.getDirection(), sortBy.getArgument())));
        return "fragments/board :: board";
    }
}
