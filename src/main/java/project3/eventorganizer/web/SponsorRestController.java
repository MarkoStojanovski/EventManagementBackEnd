package project3.eventorganizer.web;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import project3.eventorganizer.models.Sponsor;
import project3.eventorganizer.service.SponsorService;

import java.util.List;

@RestController
@RequestMapping("/api/sponsors")
public class SponsorRestController {

    private final SponsorService sponsorService;

    public SponsorRestController(SponsorService sponsorService) {
        this.sponsorService = sponsorService;
    }

    @GetMapping
    public ResponseEntity<List<Sponsor>> getAllSponsors() {
        List<Sponsor> sponsors = this.sponsorService.findAll();
        return new ResponseEntity<>(sponsors, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Sponsor> getSponsor(
            @PathVariable Long id
    ) {
        Sponsor sponsor = this.sponsorService.findById(id);
        return new ResponseEntity<>(sponsor, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Sponsor> createSponsor(
            @RequestBody Sponsor sponsor
    ) {
        Sponsor newSponsor = this.sponsorService.createSponsor(sponsor);
        return new ResponseEntity<>(newSponsor, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Sponsor> updateSponsor(
            @PathVariable Long id,
            @RequestBody Sponsor sponsor
    ) {
        Sponsor updatedSponsor = this.sponsorService.updateSponsor(id, sponsor);
        return new ResponseEntity<>(updatedSponsor, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Sponsor> deleteSponsor(
            @PathVariable Long id
    ) {
        this.sponsorService.softDelete(id);
        return ResponseEntity.ok().build();
    }

}
