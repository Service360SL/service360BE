package com.service360.group50.dto;

import com.service360.group50.entity.TrainingSession;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;
public class TrainingSessionDTO {
    private TrainingSession trainingsessions;
    private List<ImagesDTO> trainingsessionimages;

    public TrainingSessionDTO() {
        this.trainingsessionimages = new ArrayList<>();
    }

    public TrainingSession getTrainingsessions() {
        return trainingsessions;
    }

    public void setTrainingsessions(TrainingSession trainingsessions) {
        this.trainingsessions = trainingsessions;
    }

    public List<ImagesDTO> getTrainingsessionimages() {
        return trainingsessionimages;
    }

    public void setTrainingsessionimages(List<ImagesDTO> trainingsessionimages) {
        this.trainingsessionimages = trainingsessionimages;
    }
}
