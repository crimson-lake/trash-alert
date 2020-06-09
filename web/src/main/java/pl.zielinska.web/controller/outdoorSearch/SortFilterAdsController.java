package pl.zielinska.web.controller.outdoorSearch;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.zielinska.model.domain.SortingArgument;
import pl.zielinska.outdoor.dto.AdDto;
import pl.zielinska.outdoor.service.AdService;

import java.util.List;

@Slf4j
@Controller
public class SortFilterAdsController {

    @Autowired
    private AdService adService;

    private String filterBy;
    private Sort sortBy;

    @GetMapping("/outdoor-search/sort")
    public String sortedAds(@RequestParam(name = "sortBy") String sortArg, Model model) {
        SortingArgument sortingArg = SortingArgument.valueOf(sortArg);
        sortBy = Sort.by(sortingArg.getDirection(), sortingArg.getArgument());
        List<AdDto> filteredAds;
        if (filterBy != null) {
            filteredAds = adService.findByTagsName(filterBy, sortBy);
        } else {
            filteredAds = adService.findAllDto(sortBy);
        }
        model.addAttribute("ads", filteredAds);
        return "fragments/board :: board";
    }

    @GetMapping("/outdoor-search/filter")
    public String filteredAds(@RequestParam(name = "filterBy") String filterBy, Model model) {
        this.filterBy = filterBy;
        List<AdDto> filteredAds = adService.findByTagsName(filterBy, sortBy);
        model.addAttribute("ads", filteredAds);
        return "fragments/board :: board";
    }
}
