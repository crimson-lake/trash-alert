package pl.zielinska.outdoor.service;

import pl.zielinska.model.domain.Tag;

import java.util.Set;

public interface TagService {

    Tag findByName(String name);
    Set<Tag> makeTags(String name);
}
