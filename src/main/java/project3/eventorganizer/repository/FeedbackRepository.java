package project3.eventorganizer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import project3.eventorganizer.models.Feedback;

@Repository
public interface FeedbackRepository extends JpaRepository<Feedback, Long> {
}
