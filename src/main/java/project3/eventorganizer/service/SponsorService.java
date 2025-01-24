package project3.eventorganizer.service;

import project3.eventorganizer.models.Sponsor;

import java.util.List;

public interface SponsorService {


    Sponsor findById(Long id);

    List<Sponsor> findAll();

    Sponsor createSponsor(Sponsor sponsor);

    Sponsor updateSponsor(Long id, Sponsor sponsor);

    void softDelete(Long id);


}
