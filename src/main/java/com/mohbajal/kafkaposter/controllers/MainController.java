package com.mohbajal.kafkaposter.controllers;

import com.mohbajal.kafkaposter.dto.KafkaPosterInput;
import com.mohbajal.kafkaposter.service.KafkaSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by bajal on 5/10/2018.
 * kafka-poster
 */
@RestController
@CrossOrigin
@RequestMapping( value = "/" )
public class MainController {

    @Autowired
    KafkaSender kafkaSender;

    @RequestMapping(value = "post_kafka", method = RequestMethod.POST)
    @ResponseBody
    public void customersImpactedByIncidentId1(@RequestBody KafkaPosterInput input) {
        kafkaSender.send(input.getMessageToPost(), input.getTopicName());
    }


}
