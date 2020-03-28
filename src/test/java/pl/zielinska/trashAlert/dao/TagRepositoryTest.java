package pl.zielinska.trashAlert.dao;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import pl.zielinska.trashAlert.TestVal;
import pl.zielinska.trashAlert.domain.Tag;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class TagRepositoryTest {

    @Autowired
    private TagRepository tagRepository;

    @Test
    void createTagTest() {
        Tag tag = Tag.builder()
                    .tag(TestVal.TEST_TAG_NAME)
                    .build();

        tagRepository.save(tag);
    }

    @Test
    void findByTagNameTest() {
        Tag tag = tagRepository.findByTag(TestVal.TEST_TAG_NAME);
        assertNotNull(tag);
        assertEquals(TestVal.TEST_TAG_NAME, tag.getTag());
    }

    @Test
    void deleteTagTest() {
        Tag tag = tagRepository.findByTag(TestVal.TEST_TAG_NAME);
        tagRepository.delete(tag);
        assertNull(tagRepository.findByTag(TestVal.TEST_TAG_NAME));
    }
}
