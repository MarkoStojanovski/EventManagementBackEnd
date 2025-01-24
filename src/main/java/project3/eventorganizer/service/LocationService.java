package project3.eventorganizer.service;

import project3.eventorganizer.models.Location;

import java.util.List;

public interface LocationService {

    Location findById(Long id);

    List<Location> findAll();

    Location createLocation(Location location);

    Location updateLocation(Long id, Location location);

    void softDelete(Long id);

}
