package project3.eventorganizer.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import project3.eventorganizer.models.Feedback;

import java.util.List;

public interface FeedbackService {

    Feedback findById(Long id);

    Page<Feedback> findAllFeedbacks(Pageable pageable);

    Feedback createFeedback(Feedback feedback);

    Feedback updateFeedback(Long id, Feedback feedback);

    void softDelete(Long id);




}
