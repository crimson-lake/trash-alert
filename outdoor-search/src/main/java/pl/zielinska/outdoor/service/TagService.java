package pl.zielinska.outdoor.service;

import pl.zielinska.model.domain.Tag;

public interface TagService {
    Tag findByName(String name);
    Tag createTag(String name);
}
