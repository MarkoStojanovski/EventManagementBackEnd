package project3.eventorganizer.service;

import project3.eventorganizer.models.Feedback;

import java.util.List;

public interface FeedbackService {

    Feedback findById(Long id);

    List<Feedback> findAll();

    Feedback createFeedback(Feedback feedback);

    Feedback updateFeedback(Long id, Feedback feedback);

    void softDelete(Long id);




}
