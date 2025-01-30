package project3.eventorganizer.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import project3.eventorganizer.models.Sponsor;

import java.util.List;

public interface SponsorService {


    Sponsor findById(Long id);

    Page<Sponsor> findAllSponsors(Pageable pageable);

    Sponsor createSponsor(Sponsor sponsor);

    Sponsor updateSponsor(Long id, Sponsor sponsor);

    void softDelete(Long id);


}
