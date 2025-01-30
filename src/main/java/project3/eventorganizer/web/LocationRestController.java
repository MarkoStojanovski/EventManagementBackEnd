package project3.eventorganizer.web;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import project3.eventorganizer.models.Location;
import project3.eventorganizer.service.LocationService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/locations")
public class LocationRestController {

    private final LocationService locationService;

    public LocationRestController(LocationService locationService) {
        this.locationService = locationService;
    }


    @GetMapping
    public ResponseEntity<Map<String, Object>> getAllLocations(
            @RequestParam(value = "page", defaultValue = "0") int page
    ) {
        Pageable pageable = PageRequest.of(page, 6);
        Page<Location> locations = this.locationService.findAllLocations(pageable);

        Map<String, Object> response = new HashMap<>();
        response.put("locations",locations.getContent());
        response.put("totalPages",locations.getTotalPages());
        response.put("currentPage",locations.getNumber());

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Location> findLocation(
            @PathVariable Long id
    ) {
        Location location = this.locationService.findById(id);
        return new ResponseEntity<>(location, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Location> createLocation(
            @RequestBody Location location
    ) {
        Location newLocation = this.locationService.createLocation(location);
        return new ResponseEntity<>(newLocation, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Location> updateLocation(
            @PathVariable Long id,
            @RequestBody Location location
    ) {
        Location updatedLocation = this.locationService.updateLocation(id, location);
        return new ResponseEntity<>(updatedLocation, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Location> deleteLocation(
            @PathVariable Long id
    ) {
        this.locationService.softDelete(id);
        return ResponseEntity.ok().build();
    }

}
