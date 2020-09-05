package pl.zielinska.web.controller.outdoorSearch;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.zielinska.model.domain.SortAndFilterArguments;
import pl.zielinska.model.domain.SortingArgument;
import pl.zielinska.outdoor.dto.AdDto;
import pl.zielinska.outdoor.service.AdService;
import pl.zielinska.web.utils.PaginationUtils;

import java.util.Optional;

@Slf4j
@Controller
@Scope("session")
public class SortFilterAdsController {

    @Autowired
    private AdService adService;

    @Autowired
    private SortAndFilterArguments sortAndFilterArgs;

    @GetMapping("/sort")
    public String sortedAds(@RequestParam(name = "sortBy") String sortArg,
                            @RequestParam("page") Optional<Integer> page,
                            @RequestParam("size") Optional<Integer> size,
                            Model model) {
        SortingArgument sortingArg = SortingArgument.valueOf(sortArg);
        sortAndFilterArgs.setSortBy(Sort.by(sortingArg.getDirection(), sortingArg.getArgument()));
        Pageable pageable = PaginationUtils.from(page, size, sortAndFilterArgs.getSortBy());
        Page<AdDto> filteredAds = adService.findAllDto(sortAndFilterArgs, pageable);
        model.addAttribute("ads", filteredAds);
        return "fragments/board :: board";
    }

    @GetMapping("/filter")
    public String filteredAds(@RequestParam(name = "filterBy") String filterBy,
                              @RequestParam("page") Optional<Integer> page,
                              @RequestParam("size") Optional<Integer> size,
                              Model model) {
        sortAndFilterArgs.addToFilterList("tag", filterBy);
        Pageable pageable = PaginationUtils.from(page, size, sortAndFilterArgs.getSortBy());
        Page<AdDto> filteredAds = adService.findAllDto(sortAndFilterArgs, pageable);
        model.addAttribute("filtered", sortAndFilterArgs.isFiltered());
        model.addAttribute("ads", filteredAds);
        return "fragments/board :: board";
    }

    @GetMapping("/clear")
    public String clearFilters(@RequestParam("page") Optional<Integer> page,
                               @RequestParam("size") Optional<Integer> size,
                               Model model) {
        sortAndFilterArgs.clearFilterList();
        Pageable pageable = PaginationUtils.from(page, size, sortAndFilterArgs.getSortBy());
        Page<AdDto> filteredAds = adService.findAllDto(sortAndFilterArgs, pageable);
        model.addAttribute("filtered", sortAndFilterArgs.isFiltered());
        model.addAttribute("ads", filteredAds);
        return "fragments/board :: board";
    }
}
