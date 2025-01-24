package project3.eventorganizer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import project3.eventorganizer.models.Location;

@Repository
public interface LocationRepository extends JpaRepository<Location, Long> {
}
