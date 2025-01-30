package project3.eventorganizer.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import project3.eventorganizer.models.Feedback;

@Repository
public interface FeedbackRepository extends JpaRepository<Feedback, Long> {

    @Query("SELECT F FROM Feedback AS F WHERE F.isDeleted = false " +
            "AND F.user.isDeleted = false  AND F.event.isDeleted = false ")
    Page<Feedback> findAllFeedbacks(Pageable pageable);

}
