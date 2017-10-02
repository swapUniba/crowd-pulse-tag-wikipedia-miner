package com.github.frapontillo.pulse.crowd.tag.wikipediaminer;

import java.util.List;

/**
 * @author Francesco Pontillo
 */
public class WikifyResponse {
    private List<DetectedTopic> detectedTopics;

    public List<DetectedTopic> getDetectedTopics() {
        return detectedTopics;
    }

    public void setDetectedTopics(List<DetectedTopic> detectedTopics) {
        this.detectedTopics = detectedTopics;
    }

    public class DetectedTopic {
        private long id;
        private String title;
        private double weight;

        public long getId() {
            return id;
        }

        public void setId(long id) {
            this.id = id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public double getWeight() {
            return weight;
        }

        public void setWeight(double weight) {
            this.weight = weight;
        }
    }
}
