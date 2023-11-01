package com.service360.group50.scheduler;

import com.service360.group50.repo.TrainingSessionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import java.time.LocalDateTime;
import java.util.List;
import java.sql.Date;
import java.sql.Time;

import com.service360.group50.entity.*;

@Component
public class TrainingSessionScheduler {
    @Autowired
    private TrainingSessionRepository trainingSessionRepository;

    // This method will be scheduled to run every day at 6:00 AM
    @Scheduled(cron = "0 00 06 * * ?")
    public void updateCompletedTrainingSessions() {
        // Get the current date and time
        LocalDateTime currentDateTime = LocalDateTime.now();

        // Get the current date and time
        Date currentDate = new Date(System.currentTimeMillis());
        Time currentTime = new Time(System.currentTimeMillis());

        // Retrieve the list of training sessions from the database where the date is in the past and end time has passed
        List<TrainingSession> completedTrainingSessions = trainingSessionRepository.findByDateTimeBefore(currentDate, currentTime);

        // Update the status of each training session to "completed" in the database
        for (TrainingSession session : completedTrainingSessions) {
            session.setStatus("Completed");
            trainingSessionRepository.save(session);
        }
    }
}
