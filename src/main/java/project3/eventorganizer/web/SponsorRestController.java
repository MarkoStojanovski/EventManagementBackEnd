package project3.eventorganizer.web;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import project3.eventorganizer.models.Sponsor;
import project3.eventorganizer.service.SponsorService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/sponsors")
public class SponsorRestController {

    private final SponsorService sponsorService;

    public SponsorRestController(SponsorService sponsorService) {
        this.sponsorService = sponsorService;
    }

    @GetMapping
    public ResponseEntity<Map<String, Object>> getAllSponsors(
            @RequestParam(value = "page", defaultValue = "0") int page
    ) {
        Pageable pageable = PageRequest.of(page, 6);
        Page<Sponsor> sponsors = this.sponsorService.findAllSponsors(pageable);

        Map<String, Object> response = new HashMap<>();
        response.put("sponsors", sponsors.getContent());
        response.put("totalPages", sponsors.getTotalPages());
        response.put("currentPage", sponsors.getNumber());

        return new ResponseEntity<>(response, HttpStatus.OK);
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
