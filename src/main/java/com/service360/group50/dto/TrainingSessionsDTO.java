package com.service360.group50.dto;

import com.service360.group50.entity.TrainingSession;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

public class TrainingSessionsDTO {
    private List<ImagesDTO> trainingsessionimages;
    private List<TrainingSession> trainingsessions;

    public TrainingSessionsDTO() {
        this.trainingsessions = new ArrayList<>();
    }

    public List<ImagesDTO> getTrainingsessionimages() {
        return trainingsessionimages;
    }

    public void setTrainingsessionimages(List<ImagesDTO> trainingsessionimages) {
        this.trainingsessionimages = trainingsessionimages;
    }

    public List<TrainingSession> getTrainingsessions() {
        return trainingsessions;
    }

    public void setTrainingsessions(List<TrainingSession> trainingsessions) {
        this.trainingsessions = trainingsessions;
    }
}
