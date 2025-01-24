package project3.eventorganizer.web;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import project3.eventorganizer.models.Feedback;
import project3.eventorganizer.service.FeedbackService;

import java.util.List;

@RestController
@RequestMapping("/api/feedbacks")
public class FeedbackRestController {

    private final FeedbackService feedbackService;

    public FeedbackRestController(FeedbackService feedbackService) {
        this.feedbackService = feedbackService;
    }

    @GetMapping
    public ResponseEntity<List<Feedback>> getAllFeedbacks() {
        List<Feedback> feedbacks = this.feedbackService.findAll();
        return new ResponseEntity<>(feedbacks, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Feedback> getFeedback(
            @PathVariable Long id
    ) {
        Feedback feedback = this.feedbackService.findById(id);
        return new ResponseEntity<>(feedback, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Feedback> createFeedback(
            @RequestBody Feedback feedback
    ) {
        Feedback newFeedback = this.feedbackService.createFeedback(feedback);
        return new ResponseEntity<>(newFeedback, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Feedback> updateFeedback(
            @PathVariable Long id,
            @RequestBody Feedback feedback
    ) {
        Feedback updatedFeedback = this.feedbackService.updateFeedback(id, feedback);
        return new ResponseEntity<>(updatedFeedback, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Feedback> deleteFeedback(
            @PathVariable Long id
    ) {
        this.feedbackService.softDelete(id);
        return ResponseEntity.ok().build();
    }
}
