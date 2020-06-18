package pl.zielinska.model.domain;

import lombok.Getter;
import lombok.Setter;
import org.springframework.context.annotation.Scope;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
@Getter @Setter
@Scope("session")
public class SortAndFilterArguments {
    private Sort sortBy;
    private Map<String, String> filterBy = new HashMap<>();

    public void addToFilterList(String attribute, String value) {
        filterBy.put(attribute, value);
    }

    public void removeFromFilterList(String attribute) {
        filterBy.remove(attribute);
    }

    public void clearFilterList() {
        filterBy.clear();
    }

    public boolean isFiltered() {
        return !filterBy.isEmpty();
    }
}
