package pl.zielinska.outdoor.rest;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.zielinska.outdoor.dto.AdDto;
import pl.zielinska.outdoor.service.AdService;

import java.util.List;

@RestController
@RequestMapping("/api/ads")
@AllArgsConstructor
public class AdRestController {

    @Autowired
    private AdService adService;

    @GetMapping
    public List<AdDto> findAll() {
        return adService.findAllDto();
    }

    @GetMapping(path = "/{id}")
    public AdDto findById(@PathVariable(name="id") int id) {
        return adService.findById(id).toDto();
    }

}
