package com.majiku.demo;

import com.majiku.demo.data.Topic;
import com.majiku.demo.data.TopicRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
public class TopicRepositoryTest {
    @Autowired
    TestEntityManager entityManager;
    
    @Autowired
    TopicRepository topicRepository;
    
    @Test
    public void testSaveTopic() {
        Topic topic = new Topic(3, "Harray Potter", "Amazing book from JK Rolin.");
        Topic savedDB = entityManager.persist(topic);
        Topic fromDB =  topicRepository.findById(3).get();
        assertThat(fromDB).isEqualTo(savedDB);
    }
}
