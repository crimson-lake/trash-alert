package pl.zielinska.web.utils;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.Optional;

public class PaginationUtils {

    public static Pageable from(Optional<Integer> page,
                                Optional<Integer> size,
                                Sort sort) {
        int currentPage = page.orElse(1) - 1;
        int pageSize = size.orElse(10);
        return PageRequest.of(currentPage, pageSize, sort);
    }
}
