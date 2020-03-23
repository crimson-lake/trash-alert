package pl.zielinska.trashAlert.DTO;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data @Builder
public class AdDto {

    @NotBlank
    private String title;

    @NotBlank
    private String city;

    @NotBlank
    private String street;

    private String created;

    private String details;
}
