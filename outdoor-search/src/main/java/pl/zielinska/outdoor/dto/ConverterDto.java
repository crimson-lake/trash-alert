package pl.zielinska.outdoor.dto;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public interface ConverterDto<E, D extends Dto> {
    E createFrom(D dto);

    D createFrom(E entity);

    E updateEntity(E entity, D dto);

    default List createFromEntities(final Collection<E> entities) {
        return entities.stream()
                .map(this::createFrom)
                .collect(Collectors.toList());
    }

    default List createFromDtos(final Collection<D> dtos) {
        return dtos.stream()
                .map(this::createFrom)
                .collect(Collectors.toList());
    }
}
