package project3.eventorganizer.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project3.eventorganizer.models.Sponsor;
import project3.eventorganizer.models.exceptions.SponsorNotFoundException;
import project3.eventorganizer.repository.SponsorRepository;
import project3.eventorganizer.service.SponsorService;

import java.util.List;

@Service
public class SponsorServiceImpl implements SponsorService {

    private final SponsorRepository sponsorRepository;

    public SponsorServiceImpl(SponsorRepository sponsorRepository) {
        this.sponsorRepository = sponsorRepository;
    }

    @Override
    public Sponsor findById(Long id) {
        return this.sponsorRepository.findById(id)
                .orElseThrow(SponsorNotFoundException::new);
    }

    @Override
    public List<Sponsor> findAll() {
        return this.sponsorRepository.findAll();
    }

    @Override
    @Transactional
    public Sponsor createSponsor(Sponsor sponsor) {
        Sponsor newSponsor = new Sponsor(
          sponsor.getName(),
          sponsor.getFoundedIn(),
          sponsor.getEmail(),
          sponsor.getImageUrl(),
          sponsor.getAmountGiven(),
          sponsor.getSponsorStatus()
        );
        return this.sponsorRepository.save(newSponsor);
    }

    @Override
    @Transactional
    public Sponsor updateSponsor(Long id, Sponsor sponsor) {
        Sponsor updatedSponsor = this.sponsorRepository.findById(id)
                .orElseThrow(SponsorNotFoundException::new);

        updatedSponsor.setName(sponsor.getName());
        updatedSponsor.setFoundedIn(sponsor.getFoundedIn());
        updatedSponsor.setEmail(sponsor.getEmail());
        updatedSponsor.setImageUrl(sponsor.getImageUrl());
        updatedSponsor.setAmountGiven(sponsor.getAmountGiven());
        updatedSponsor.setSponsorStatus(sponsor.getSponsorStatus());

        return this.sponsorRepository.save(updatedSponsor);
    }

    @Override
    public void softDelete(Long id) {
        Sponsor sponsor = this.sponsorRepository.findById(id)
                .orElseThrow(SponsorNotFoundException::new);

        sponsor.setDeleted(true);
        this.sponsorRepository.save(sponsor);
    }
}
