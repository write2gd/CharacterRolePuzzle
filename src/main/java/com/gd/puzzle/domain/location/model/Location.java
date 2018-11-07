package com.gd.puzzle.domain.location.model;

import java.io.Serializable;
import java.util.Objects;

public class Location implements Serializable {
    private static final long serialVersionUID = 1l;
    private String name;
    private String locationType;

    public Location() {
    }

    public Location(String name) {
        this.name = name;
    }

    public String getLocationType() {
        return locationType;
    }

    public void setLocationType(String locationType) {
        this.locationType = locationType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Location location = (Location) o;
        return Objects.equals(name, location.name) && Objects.equals(locationType, location.locationType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, locationType);
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append(name);
        return builder.toString();
    }
}
