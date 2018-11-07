package com.gd.puzzle.domain.character.model;

import java.io.Serializable;
import java.util.Objects;

import com.gd.puzzle.domain.location.model.Location;
import com.gd.puzzle.enums.CharacterType;
import com.gd.puzzle.enums.Speciality;

public class GameCharacter extends BaseCharacter implements Serializable {
    private String characterName;
    private int experience;
    private Location currentLocation;
    private CharacterType type;
    private int healthLevel = 100;

    public GameCharacter() {
    }

    public GameCharacter(String characterName, Location currentLocation, CharacterType type) {
        this.characterName = characterName;
        this.currentLocation = currentLocation;
        this.type = type;
    }

    public GameCharacter(int punch, int hit, int kick, int attack, Speciality speciality, String characterName, Location currentLocation, CharacterType type) {
        super(punch, hit, kick, attack, speciality);
        this.characterName = characterName;
        this.currentLocation = currentLocation;
        this.type = type;
    }

    public String getCharacterName() {
        return characterName;
    }

    public void setCharacterName(String characterName) {
        this.characterName = characterName;
    }

    public int getExperience() {
        return experience;
    }

    public void setExperience(int experience) {
        this.experience = experience;
    }

    public Location getCurrentLocation() {
        return currentLocation;
    }

    public void setCurrentLocation(Location currentLocation) {
        this.currentLocation = currentLocation;
    }

    public CharacterType getType() {
        return type;
    }

    public void setType(CharacterType type) {
        this.type = type;
    }

    public int getHealthLevel() {
        return healthLevel;
    }

    public void setHealthLevel(int healthLevel) {
        this.healthLevel = healthLevel;
    }

    public void punch(GameCharacter opponent) {
        opponent.setHealthLevel(opponent.getHealthLevel() - calculateActionPower(getPunch()));
    }

    public void attack(GameCharacter opponent) {
        opponent.setHealthLevel(opponent.getHealthLevel() - calculateActionPower(getAttack()));
    }

    public void kick(GameCharacter opponent) {
        opponent.setHealthLevel(opponent.getHealthLevel() - calculateActionPower(getKick()));
    }

    public void selfDefense(GameCharacter opponent) {
        opponent.setHealthLevel(opponent.getHealthLevel() - calculateActionPower(getDefense()));
    }

    public void counterAttack(GameCharacter opponent) {
        opponent.setHealthLevel(opponent.getHealthLevel() - calculateActionPower(getCounter_attack()));
    }

    public void hit(GameCharacter opponent) {
        opponent.setHealthLevel(opponent.getHealthLevel() - calculateActionPower(getHit()));
    }

    private int calculateActionPower(int power) {
        if (power > 10) {
            return Math.round(power / 10);
        } else if (power > 0) {
            return power;
        }
        return 0;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        GameCharacter character = (GameCharacter) o;
        return Objects.equals(characterName, character.characterName) && Objects.equals(currentLocation, character.currentLocation);
    }

    @Override
    public int hashCode() {
        return Objects.hash(characterName, currentLocation);
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("GameCharacter [characterName=");
        builder.append(characterName);
        builder.append(", currentLocation=");
        builder.append(currentLocation);
        builder.append(", experience=");
        builder.append(experience);
        builder.append(", healthLevel=");
        builder.append(healthLevel);
        builder.append(", type=");
        builder.append(type);
        builder.append("]");
        return builder.toString();
    }

}
