package com.gd.puzzle.domain.character.model;

import java.io.Serializable;

import com.gd.puzzle.enums.Speciality;

public class BaseCharacter implements Serializable {
    private int punch;
    private int hit;
    private int kick;
    private int attack;
    private Speciality speciality;

    public BaseCharacter() {
    }

    public BaseCharacter(int punch, int hit, int kick, int attack, Speciality speciality) {
        this.punch = punch;
        this.hit = hit;
        this.kick = kick;
        this.attack = attack;
        this.speciality = speciality;
    }

    public int getPunch() {
        return punch;
    }

    public int getHit() {
        return hit;
    }

    public int getKick() {
        return kick;
    }

    public int getAttack() {
        return attack;
    }

    public int getDefense() {
        return Math.round(attack / 3);
    }

    public Speciality getSpeciality() {
        return speciality;
    }

    public int getCounter_attack() {
        return Math.round(attack / 2);
    }

    public void setPunch(int punch) {
        this.punch = punch;
    }

    public void setHit(int hit) {
        this.hit = hit;
    }

    public void setKick(int kick) {
        this.kick = kick;
    }

    public void setAttack(int attack) {
        this.attack = attack;
    }

    public void setSpeciality(Speciality speciality) {
        this.speciality = speciality;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append(speciality);
        return builder.toString();
    }

}
