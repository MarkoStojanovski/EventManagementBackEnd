package project3.eventorganizer.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import project3.eventorganizer.models.Location;

@Repository
public interface LocationRepository extends JpaRepository<Location, Long> {

    @Query("SELECT L FROM Location AS L WHERE L.isDeleted = false")
    Page<Location> findAllLocations(Pageable pageable);

}
