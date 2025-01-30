package project3.eventorganizer.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import project3.eventorganizer.models.Location;

import java.util.List;

public interface LocationService {

    Location findById(Long id);

    Page<Location> findAllLocations(Pageable pageable);

    Location createLocation(Location location);

    Location updateLocation(Long id, Location location);

    void softDelete(Long id);

}
