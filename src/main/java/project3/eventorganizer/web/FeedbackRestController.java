package project3.eventorganizer.web;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import project3.eventorganizer.models.Event;
import project3.eventorganizer.models.Feedback;
import project3.eventorganizer.service.FeedbackService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/feedbacks")
public class FeedbackRestController {

    private final FeedbackService feedbackService;

    public FeedbackRestController(FeedbackService feedbackService) {
        this.feedbackService = feedbackService;
    }

    @GetMapping
    public ResponseEntity<Map<String, Object>> getAllFeedbacks(
            @RequestParam(value = "page", defaultValue = "0") int page
    ) {
        Pageable pageable = PageRequest.of(page, 6);
        Page<Feedback> feedbacks = this.feedbackService.findAllFeedbacks(pageable);

        Map<String, Object> response = new HashMap<>();
        response.put("feedbacks",feedbacks.getContent());
        response.put("totalPages",feedbacks.getTotalPages());
        response.put("currentPage",feedbacks.getNumber());
        return new ResponseEntity<>(response, HttpStatus.OK);
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
