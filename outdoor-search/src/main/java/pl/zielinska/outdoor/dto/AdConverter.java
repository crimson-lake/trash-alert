package pl.zielinska.outdoor.dto;

import org.springframework.stereotype.Component;
import pl.zielinska.model.domain.Ad;

import java.time.LocalDateTime;

@Component
public class AdConverter implements ConverterDto<Ad, AdDto> {
    @Override
    public Ad createFrom(AdDto dto) {
        return Ad.builder()
                .title(dto.getTitle())
                .details(dto.getDetails())
                .city(dto.getCity())
                .street(dto.getStreet())
                .created(LocalDateTime.now())
                .build();
    }

    @Override
    public AdDto createFrom(Ad entity) {
        return AdDto.builder()
                .id(entity.getId())
                .title(entity.getTitle())
                .details(entity.getDetails() == null ? "" : entity.getDetails())
                .city(entity.getCity())
                .street(entity.getStreet())
                .created(entity.getFormattedDate())
                .build();
    }

    @Override
    public Ad updateEntity(Ad entity, AdDto dto) {
        return null;
    }
}
