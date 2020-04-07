package com.majiku.demo.api;

import com.majiku.demo.business.TopicService;
import com.majiku.demo.data.Topic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/topics")
public class TopicController {
    private final TopicService topicService;

    @Autowired
    public TopicController(TopicService topicService) {
        this.topicService = topicService;
    }

    @GetMapping
    public Iterable<Topic> gettAllTopics(){
        return topicService.getAllTopics();
    }

//    @GetMapping
//    public Topic getTopic(@RequestParam int id) {
//        return topicService.getTopic(id);
//    }

    @GetMapping("/{id}")
    public Topic getTopic(@PathVariable("id") int id) {
        return topicService.getTopic(id);
    }

    // topic class needs @XmlRootElement
    @GetMapping(path = "/xml", produces = "application/xml")
    public Topic getTopicInXml(){
        return topicService.getTopic(1);
    }

    @PostMapping
    public Topic addTopic(@RequestBody Topic topic) {
       return topicService.addTopic(topic);
    }

    @PutMapping
    public Topic updateTopic(@RequestBody Topic topic) {
       return topicService.updateTopic(topic);
    }

//    @PutMapping
//    public ResponseEntity<Topic> updateTopic(@RequestBody Topic topic) {
//        Topic t = topicService.updateTopic(topic);
//        return new ResponseEntity<Topic>(t, HttpStatus.OK);
//    }

    @DeleteMapping("/{id}")
    public void deleteTopic(@PathVariable int id){
        topicService.deleteTopic(id);
    }
}
