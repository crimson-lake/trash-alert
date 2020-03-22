package pl.zielinska.trashAlert.DTO;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class AdDto {

    @NotBlank
    private String title;

    @NotBlank
    private String city;

    @NotBlank
    private String street;

    private String details;
}
