package pl.zielinska.model.domain;

import lombok.Getter;
import org.springframework.data.domain.Sort;

@Getter
public enum SortingArgument {
    DATE_ASC("sorting.oldest", "created", Sort.Direction.ASC),
    DATE_DESC("sorting.newest", "created", Sort.Direction.DESC);

    private String text;
    private String argument;
    private Sort.Direction direction;

    SortingArgument(String text, String argument, Sort.Direction direction) {
        this.text = text;
        this.argument = argument;
        this.direction = direction;
    }
}
