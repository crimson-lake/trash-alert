package pl.zielinska.outdoor.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.zielinska.outdoor.validation.Address;

import javax.validation.constraints.NotBlank;

@Address
@Data @Builder
@NoArgsConstructor
@AllArgsConstructor
public class LocationDto implements Dto, Adress {

    @NotBlank
    private String name;
    @NotBlank
    private String street;
    @NotBlank
    private String city;
}
