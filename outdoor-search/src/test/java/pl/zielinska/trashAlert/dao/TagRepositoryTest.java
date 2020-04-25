package pl.zielinska.trashAlert.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;
import pl.zielinska.trashAlert.TestVal;
import pl.zielinska.trashAlert.domain.Tag;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@RunWith(SpringRunner.class)
@DataJpaTest
public class TagRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private TagRepository tagRepository;

    @Test
    public void findByTagNameTest() {
        Tag testTag = Tag.builder()
                .tag(TestVal.TEST_TAG_NAME)
                .build();

        entityManager.persistAndFlush(testTag);

        Tag tag = tagRepository.findByTag(TestVal.TEST_TAG_NAME);
        assertNotNull(tag);
        assertEquals(TestVal.TEST_TAG_NAME, tag.getTag());
    }

}
