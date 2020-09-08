package pl.zielinska.outdoor.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotBlank;
import java.util.Set;

@pl.zielinska.outdoor.validation.Address
@Data @Builder
@NoArgsConstructor
@AllArgsConstructor
public class AdDto implements Dto, Address {

    private int id;

    @NotBlank
    @Length(max=50)
    private String title;

    @NotBlank
    @Length(max=50)
    private String city;

    @NotBlank
    @Length(max=50)
    private String street;

    private String created;

    private String details;

    private Set<String> tags;

    private MultipartFile photo;
}
