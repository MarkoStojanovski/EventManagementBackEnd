package project3.eventorganizer.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project3.eventorganizer.models.Location;
import project3.eventorganizer.models.exceptions.LocationNotFoundException;
import project3.eventorganizer.repository.LocationRepository;
import project3.eventorganizer.service.LocationService;
import java.util.List;

@Service
public class LocationServiceImpl implements LocationService {

    private final LocationRepository locationRepository;

    public LocationServiceImpl(LocationRepository locationRepository) {
        this.locationRepository = locationRepository;
    }

    @Override
    @Transactional
    public Location createLocation(Location location) {
        Location newLocation = new Location(
                location.getName(),
                location.getCity(),
                location.getCountry(),
                location.getAddress(),
                location.getActiveSince(),
                location.getImageUrl()
        );
        return this.locationRepository.save(newLocation);
    }

    @Override
    @Transactional
    public Location updateLocation(Long id, Location location) {
        Location updatedLocation = this.locationRepository.findById(id)
                .orElseThrow(LocationNotFoundException::new);

        updatedLocation.setName(location.getName());
        updatedLocation.setCity(location.getCity());
        updatedLocation.setCountry(location.getCountry());
        updatedLocation.setAddress(location.getAddress());
        updatedLocation.setActiveSince(location.getActiveSince());
        updatedLocation.setImageUrl(location.getImageUrl());

        return this.locationRepository.save(updatedLocation);
    }

    @Override
    public Location findById(Long id) {
        return this.locationRepository.findById(id)
                .orElseThrow(LocationNotFoundException::new);
    }

    @Override
    public List<Location> findAll() {
        return this.locationRepository.findAll();
    }

    @Override
    public void softDelete(Long id) {
        Location location = this.locationRepository.findById(id)
                .orElseThrow(LocationNotFoundException::new);

        location.setDeleted(true);
        this.locationRepository.save(location);
    }
}
