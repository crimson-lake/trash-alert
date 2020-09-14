package pl.zielinska.outdoor.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.zielinska.model.domain.Tag;
import pl.zielinska.model.repository.TagRepository;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class TagServiceImpl implements TagService {

    @Autowired
    private TagRepository tagRepository;

    @Override
    public Tag findByName(String name) {
        return tagRepository.findByName(name.toLowerCase());
    }

    @Override
    public Set<Tag> makeTags(String tags) {
        return Arrays.stream(tags.split("(,*\\s+#*)|(#+)"))
                .filter(x -> !x.isEmpty())
                .map(x -> createTag(x))
                .collect(Collectors.toSet());
    }

    private Tag createTag(String name) {
        Tag tag = returnTagIfAlreadyRegistered(name);
        if (tag == null) {
            tag = Tag.builder().name(name.toLowerCase()).build();
        }
        return tag;
    }

    private Tag returnTagIfAlreadyRegistered(String name) {
        return findByName(name);
    }
}
