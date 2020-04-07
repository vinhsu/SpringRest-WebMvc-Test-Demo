package com.majiku.demo.business;

import com.majiku.demo.data.Topic;
import com.majiku.demo.data.TopicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TopicService {
    private final TopicRepository topicRepository;

    @Autowired
    public TopicService(TopicRepository topicRepository) {
        this.topicRepository = topicRepository;
    }

    public List<Topic> getAllTopics(){
        List<Topic> topics = new ArrayList<>();
        topicRepository.findAll().forEach(topics::add);
        return topics;
    }

    public Topic getTopic(int id){
        Optional<Topic> optional = topicRepository.findById(id);
        if (!optional.isPresent())
            return null;
        return topicRepository.findById(id).get();
    }

    public Topic addTopic(Topic topic){
        return topicRepository.save(topic);
    }

    public Topic updateTopic(Topic topic){
        return topicRepository.save(topic);
    }

    public void deleteTopic(int id) {
        topicRepository.deleteById(id);
    }
}
