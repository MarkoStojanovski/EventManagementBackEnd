package project3.eventorganizer.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project3.eventorganizer.models.Event;
import project3.eventorganizer.models.Feedback;
import project3.eventorganizer.models.User;
import project3.eventorganizer.models.exceptions.EventNotFoundException;
import project3.eventorganizer.models.exceptions.FeedbackNotFoundException;
import project3.eventorganizer.models.exceptions.FeedbackNotSameUserException;
import project3.eventorganizer.models.exceptions.UserNotFoundException;
import project3.eventorganizer.repository.EventRepository;
import project3.eventorganizer.repository.FeedbackRepository;
import project3.eventorganizer.repository.UserRepository;
import project3.eventorganizer.service.FeedbackService;

import java.util.List;

@Service
public class FeedbackServiceImpl implements FeedbackService {

    private final FeedbackRepository feedbackRepository;
    private final EventRepository eventRepository;
    private final UserRepository userRepository;

    public FeedbackServiceImpl(FeedbackRepository feedbackRepository, EventRepository eventRepository, UserRepository userRepository) {
        this.feedbackRepository = feedbackRepository;
        this.eventRepository = eventRepository;
        this.userRepository = userRepository;
    }

    @Override
    public Feedback findById(Long id) {
        return this.feedbackRepository.findById(id)
                .orElseThrow(FeedbackNotFoundException::new);
    }

    @Override
    @Transactional
    public Feedback createFeedback(Feedback feedback) {
        Event event = this.eventRepository.findById(feedback.getEvent().getId())
                .orElseThrow(EventNotFoundException::new);

        User user = this.userRepository.findById(feedback.getUser().getEmail())
                .orElseThrow(UserNotFoundException::new);

        Feedback newFeedback = new Feedback(
                feedback.getRating(),
                feedback.getComment(),
                event,
                user
        );
        return this.feedbackRepository.save(newFeedback);
    }

    @Override
    @Transactional
    public Feedback updateFeedback(Long id, Feedback feedback) {
        Feedback updatedFeedback = this.feedbackRepository.findById(id)
                .orElseThrow(FeedbackNotFoundException::new);

        Event event = this.eventRepository.findById(feedback.getEvent().getId())
                .orElseThrow(EventNotFoundException::new);

        User user = this.userRepository.findById(feedback.getUser().getEmail())
                .orElseThrow(UserNotFoundException::new);

        if (!user.getEmail().equals(feedback.getUser().getEmail())){
            throw new FeedbackNotSameUserException();
        }

        updatedFeedback.setRating(feedback.getRating());
        updatedFeedback.setComment(feedback.getComment());
        updatedFeedback.setEvent(event);
        updatedFeedback.setUser(user);

        return this.feedbackRepository.save(updatedFeedback);
    }

    @Override
    public List<Feedback> findAll() {
        return this.feedbackRepository.findAll();
    }

    @Override
    public void softDelete(Long id) {
        Feedback feedback = this.feedbackRepository.findById(id)
                .orElseThrow(FeedbackNotFoundException::new);
        feedback.setDeleted(true);
        this.feedbackRepository.save(feedback);
    }
}
