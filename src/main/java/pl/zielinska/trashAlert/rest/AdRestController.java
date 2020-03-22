package pl.zielinska.trashAlert.rest;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.zielinska.trashAlert.domain.Ad;
import pl.zielinska.trashAlert.service.AdService;

import java.util.List;

@RestController
@RequestMapping("/api/ads")
@AllArgsConstructor
public class AdRestController {

    @Autowired
    private AdService adService;

    @GetMapping
    public List<Ad> findAll() {
        return adService.findAll();
    }

    @GetMapping(path = "/{id}")
    public Ad findById(@PathVariable(name="id") int id) {
        return adService.findById(id);
    }

}
