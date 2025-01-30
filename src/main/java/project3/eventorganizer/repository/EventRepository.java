package project3.eventorganizer.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import project3.eventorganizer.models.Event;
import project3.eventorganizer.models.enumerations.Category;

import java.util.List;

@Repository
public interface EventRepository extends JpaRepository<Event, Long> {

    @Query("SELECT E FROM Event AS E WHERE E.isDeleted = false " +
            "AND E.location.isDeleted = false AND E.category =:category")
    Page<Event> findByCategory(
            @Param("category") Category category,
            Pageable pageable
            );

    @Query("SELECT E FROM Event AS E WHERE E.isDeleted = false AND E.location.isDeleted = false ")
    Page<Event> findAllEvents(Pageable pageable);

}
