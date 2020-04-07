package com.majiku.demo;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.majiku.demo.api.TopicController;
import com.majiku.demo.business.TopicService;
import com.majiku.demo.data.Topic;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(TopicController.class)
public class TopicControllerTest {
    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @MockBean
    TopicService topicService;

    @Test
    public void testTopicWithId() throws Exception {
        Mockito.when(topicService.getTopic(1)).thenReturn(new Topic(1, "Harray Potter", "Amazing book from JK Rolin."));
        MvcResult result = mockMvc.perform(get("/topics/1")).andExpect(status().isOk()).andDo(print()).andReturn();
        System.out.println("testTopicWithId- " + result.getResponse().getContentAsString());
    }

    @Test
    public void testAddTopic() throws Exception {
        Topic topic = new Topic(2, "Angular", "Learn the web UI framework.");
        Mockito.when(topicService.addTopic(topic)).thenReturn(topic);
        MvcResult result = mockMvc.perform(post("/topics").
                content(objectMapper.writeValueAsString(topic)).
                contentType(MediaType.APPLICATION_JSON_VALUE)).
                andExpect(status().isOk()).andReturn();
        System.out.println("testAddTopic- " + result.getResponse().getContentAsString());
    }

    @Test
    public void testUpdateTopic() throws Exception {
        Topic topic = new Topic(2, "Angular", "Learn the web UI framework.");
        Mockito.when(topicService.updateTopic(topic)).thenReturn(topic);
        MvcResult result = mockMvc.perform(put("/topics").
                content(objectMapper.writeValueAsString(topic)).
                contentType(MediaType.APPLICATION_JSON_VALUE)).
                andExpect(status().isOk()).
                andReturn();
        System.out.println("testUpdateTopic- " + result.getResponse().getContentAsString());
    }

}
