package pl.zielinska.web.controller.outdoorSearch;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.zielinska.model.domain.SortAndFilterArguments;
import pl.zielinska.model.domain.SortingArgument;
import pl.zielinska.outdoor.dto.AdDto;
import pl.zielinska.outdoor.service.AdService;

import java.util.List;

@Slf4j
@Controller
public class SortFilterAdsController {

    @Autowired
    private AdService adService;

    @Autowired
    private SortAndFilterArguments sortAndFilterArgs;

    @GetMapping("/outdoor-search/sort")
    public String sortedAds(@RequestParam(name = "sortBy") String sortArg, Model model) {
        SortingArgument sortingArg = SortingArgument.valueOf(sortArg);
        sortAndFilterArgs.setSortBy(Sort.by(sortingArg.getDirection(), sortingArg.getArgument()));
        List<AdDto> filteredAds = adService.findAllDto(sortAndFilterArgs);
        model.addAttribute("ads", filteredAds);
        return "fragments/board :: board";
    }

    @GetMapping("/outdoor-search/filter")
    public String filteredAds(@RequestParam(name = "filterBy") String filterBy, Model model) {
        sortAndFilterArgs.addToFilterList("tag", filterBy);
        List<AdDto> filteredAds = adService.findAllDto(sortAndFilterArgs);
        model.addAttribute("filtered", sortAndFilterArgs.isFiltered());
        model.addAttribute("ads", filteredAds);
        return "fragments/board :: board";
    }

    @GetMapping("/outdoor-search/clear")
    public String clearFilters(Model model) {
        sortAndFilterArgs.clearFilterList();
        List<AdDto> filteredAds = adService.findAllDto(sortAndFilterArgs);
        model.addAttribute("filtered", sortAndFilterArgs.isFiltered());
        model.addAttribute("ads", filteredAds);
        return "fragments/board :: board";
    }
}
