package project3.eventorganizer.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import project3.eventorganizer.models.Sponsor;

@Repository
public interface SponsorRepository extends JpaRepository<Sponsor, Long> {

    @Query("SELECT S FROM Sponsor AS S WHERE S.isDeleted = false")
    Page<Sponsor> findAllSponsors(Pageable pageable);

}
