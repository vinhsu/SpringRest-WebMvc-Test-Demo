package com.majiku.demo;

import com.majiku.demo.business.TopicService;
import com.majiku.demo.data.Topic;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.*;

/**
 * use SpringBootTest is for integration test
 * https://spring.io/guides/gs/testing-web/
 * https://reflectoring.io/spring-boot-test/
 */
@RunWith(SpringRunner.class)
@SpringBootTest
class TopicServiceTest {
    @Autowired
    private TopicService topicService;

    @Test
    public void testGetTopic() {
        Topic topic = topicService.getTopic(1);
        assertNotNull(topic);
    }

    @Test
    public void testAddTopic() {
        topicService.addTopic(new Topic(3, "XML", "What is XML?"));
        Topic topic = topicService.getTopic(3);
        assertNotNull(topic);
    }

    @Test
    public void testUpdateTopic() {
        Topic topic = topicService.getTopic(1);
        topic.setDescription("Update to new Java 11");
        topicService.updateTopic(topic);
        assertEquals("Update to new Java 11", topicService.getTopic(1).getDescription());
    }

    @Test
    public void testDeleteTopic() {
        topicService.deleteTopic(2);
        Topic topic = topicService.getTopic(2);
        assertNull(topic);
    }
}
