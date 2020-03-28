package pl.zielinska.trashAlert.dao;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import pl.zielinska.trashAlert.domain.Tag;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class TagRepositoryTest {

    @Autowired
    private TagRepository tagRepository;

    private final String testName = "TEST";

    @Test
    void createTagTest() {
        Tag tag = Tag.builder()
                    .tag(testName)
                    .build();

        tagRepository.save(tag);
    }

    @Test
    void findByTagNameTest() {
        Tag tag = tagRepository.findByTag(testName);
        assertNotNull(tag);
        assertEquals(testName, tag.getTag());
    }

    @Test
    void deleteTagTest() {
        Tag tag = tagRepository.findByTag(testName);
        tagRepository.delete(tag);
        assertNull(tagRepository.findByTag(testName));
    }
}
