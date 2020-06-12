package pl.zielinska.outdoor.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@pl.zielinska.outdoor.validation.Address
@Data @Builder
@NoArgsConstructor
@AllArgsConstructor
public class LocationDto implements Dto, Address {

    @NotBlank
    private String name;
    @NotBlank
    private String street;
    @NotBlank
    private String city;
}
