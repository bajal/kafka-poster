package com.mohbajal.kafkaposter.dto;

/**
 * Created by bajal on 5/10/2018.
 * kafka-poster
 */
public class KafkaPosterInput {
    private String topicName;
    private String messageToPost;

    public String getTopicName() {
        return topicName;
    }

    public void setTopicName(String topicName) {
        this.topicName = topicName;
    }

    public String getMessageToPost() {
        return messageToPost;
    }

    public void setMessageToPost(String messageToPost) {
        this.messageToPost = messageToPost;
    }
}
