package pl.zielinska.outdoor.dto;

import org.springframework.stereotype.Component;
import pl.zielinska.model.domain.Ad;
import pl.zielinska.model.domain.Tag;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.stream.Collectors;

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
        Set<String> tags = entity.getTags()
                .stream()
                .map(Tag::getName)
                .map(String::toLowerCase)
                .collect(Collectors.toSet());

        StringBuilder builder = new StringBuilder();
        tags.stream().forEach(x -> builder.append(x + " "));

        return AdDto.builder()
                .id(entity.getId())
                .title(entity.getTitle())
                .details(entity.getDetails() == null ? "" : entity.getDetails())
                .city(entity.getCity())
                .street(entity.getStreet())
                .created(entity.getFormattedDate())
                .tags(tags)
                .editTags(builder.toString())
                .build();
    }

    @Override
    public Ad updateEntity(Ad entity, AdDto dto) {
        return null;
    }
}
