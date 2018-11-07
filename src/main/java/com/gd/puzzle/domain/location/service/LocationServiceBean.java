package com.gd.puzzle.domain.location.service;

import java.util.List;

import com.gd.puzzle.domain.location.model.Location;
import com.gd.puzzle.exception.LocationServiceException;
import com.gd.puzzle.repository.GameRepository;
import com.gd.puzzle.repository.Repository;

public class LocationServiceBean implements LocationService {
    Repository repository = GameRepository.getGameRepository();
    private static LocationServiceBean locationService = null;

    public static LocationServiceBean getLocationService() {
        if (locationService == null) {
            synchronized (LocationServiceBean.class) {
                locationService = new LocationServiceBean();
            }
        }
        return locationService;
    }

    private LocationServiceBean() {
    }

    @Override
    public List<Location> getDestinations() throws LocationServiceException {
        return repository.getLocation();
    }
}
