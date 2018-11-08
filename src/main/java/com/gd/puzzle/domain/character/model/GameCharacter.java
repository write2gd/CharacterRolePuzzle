package com.gd.puzzle.domain.character.model;

import java.io.Serializable;
import java.util.Objects;

import com.gd.puzzle.enums.CharacterType;
import com.gd.puzzle.enums.Speciality;

public class GameCharacter extends BaseCharacter implements Serializable {
    private static final long serialVersionUID = 1l;
    private String characterName;
    private int experience;
    private CharacterType type;
    private int healthLevel = 100;

    public GameCharacter() {
    }

    public GameCharacter(String characterName, CharacterType type) {
        this.characterName = characterName;
        this.type = type;
    }

    public GameCharacter(int punch, int hit, int kick, int attack, Speciality speciality, String characterName, CharacterType type) {
        super(punch, hit, kick, attack, speciality);
        this.characterName = characterName;
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
            return power / 10;
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
        return Objects.equals(characterName, character.characterName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(characterName);
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("GameCharacter [characterName=");
        builder.append(characterName);
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
