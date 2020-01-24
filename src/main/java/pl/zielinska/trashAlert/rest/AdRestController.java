package pl.zielinska.trashAlert.rest;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.zielinska.trashAlert.entity.Ad;
import pl.zielinska.trashAlert.service.AdService;

import java.util.List;

@RestController
@RequestMapping("/api/ads")
@AllArgsConstructor
public class AdRestController {

    @Autowired
    private AdService adService;

    @GetMapping(produces = {MediaType.APPLICATION_XML_VALUE,
                            MediaType.APPLICATION_JSON_VALUE})
    public List<Ad> findAll() {
        return adService.findAll();
    }


}
