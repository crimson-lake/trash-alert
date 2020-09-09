package pl.zielinska.web.controller.outdoorSearch;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import pl.zielinska.model.domain.Photo;
import pl.zielinska.outdoor.dto.AdDto;
import pl.zielinska.outdoor.service.AdService;
import pl.zielinska.outdoor.service.PhotoService;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
public class AdDetailsController {

    @Autowired
    private AdService adService;

    @Autowired
    private PhotoService photoService;

    @GetMapping("/ad/{id}")
    public String ad(@PathVariable(name="id") int id, Model model) {
        AdDto ad = adService.findByIdDto(id);
        model.addAttribute("ad", ad);
        return "fragments/ad-details :: adDetails";
    }

    @GetMapping("/photos/photo")
    public void displayPhoto(@RequestParam("id") int id,
                             HttpServletResponse response) throws IOException {
        Photo photo = photoService.photoById(id);
        response.setContentType("image/png");
        response.getOutputStream().write(photo.getPhoto());
        response.getOutputStream().close();
    }
}
