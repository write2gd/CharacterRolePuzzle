package com.gd.puzzle.domain.location.service;

import java.util.List;

import com.gd.puzzle.domain.location.model.Location;
import com.gd.puzzle.exception.LocationServiceException;

public interface LocationService {
    List<Location> getDestinations() throws LocationServiceException;

}
