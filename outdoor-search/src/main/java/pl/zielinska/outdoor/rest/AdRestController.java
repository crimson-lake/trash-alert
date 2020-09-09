package pl.zielinska.outdoor.rest;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.zielinska.model.domain.Photo;
import pl.zielinska.outdoor.dto.AdDto;
import pl.zielinska.outdoor.service.AdService;
import pl.zielinska.outdoor.service.PhotoService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/ads")
@AllArgsConstructor
@Slf4j
public class AdRestController {

    @Autowired
    private AdService adService;

    @Autowired
    private PhotoService photoService;

    @GetMapping(path = "/{id}")
    public AdDto findById(@PathVariable(name="id") int id) {
        return adService.findByIdDto(id);
    }

    @GetMapping(path = "/photos")
    public List<Integer> photosIds(@RequestParam("id") int id) {
        List<Photo> photos = photoService.photosByAdId(id);
        return photos.stream()
                .map(Photo::getId)
                .collect(Collectors.toList());
    }

}
