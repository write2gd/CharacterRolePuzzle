package com.gd.puzzle.domain.character.factory;

import java.io.File;
import java.io.IOException;
import java.util.ResourceBundle;

import com.gd.puzzle.domain.character.model.GameCharacter;
import com.gd.puzzle.enums.CharacterType;
import com.gd.puzzle.enums.Speciality;
import com.gd.puzzle.exception.CharacterInitializationException;
import com.gd.puzzle.exception.ResourceException;
import com.gd.puzzle.util.ResourceUtil;

public class CharacterFactory {
    public static GameCharacter createCharacter(File f) throws CharacterInitializationException {
        try {
            ResourceBundle resourceBundle = ResourceUtil.getResourceFromPath(f);
            String charName = ResourceUtil.getMessage("name", resourceBundle)
                                          .toUpperCase();
            String type = ResourceUtil.getMessage("type", resourceBundle);
            int punch = Integer.parseInt(ResourceUtil.getMessage("punch", resourceBundle));
            int hit = Integer.parseInt(ResourceUtil.getMessage("hit", resourceBundle));
            int kick = Integer.parseInt(ResourceUtil.getMessage("kick", resourceBundle));
            int attack = Integer.parseInt(ResourceUtil.getMessage("attack", resourceBundle));
            String speciality = ResourceUtil.getMessage("speciality", resourceBundle);
            return createCharacter(charName, type, speciality, punch, hit, kick, attack);
        } catch (IOException | ResourceException e) {
            throw new CharacterInitializationException(e.getMessage(), e);
        }
    }

    private static Speciality getSpeciality(String speciality) {
        Speciality s = Speciality.STRENGTH;
        if (Speciality.ARCH.name()
                           .equalsIgnoreCase(speciality)) {
            s = Speciality.ARCH;
        } else if (Speciality.ARCH.name()
                                  .equalsIgnoreCase(speciality)) {
            s = Speciality.ARCH;
        } else if (Speciality.FIRE.name()
                                  .equalsIgnoreCase(speciality)) {
            s = Speciality.FIRE;
        } else if (Speciality.FLY.name()
                                 .equalsIgnoreCase(speciality)) {
            s = Speciality.FLY;
        } else if (Speciality.MAGIC.name()
                                   .equalsIgnoreCase(speciality)) {
            s = Speciality.MAGIC;
        } else if (Speciality.RUN.name()
                                 .equalsIgnoreCase(speciality)) {
            s = Speciality.RUN;
        } else {
            s = Speciality.STRENGTH;
        }
        return s;
    }

    public static GameCharacter createCharacter(String charName, String type, String speciality, int punch, int hit, int kick, int attack) {
        GameCharacter character = new GameCharacter();
        character.setCharacterName(charName);
        if ("hero".equalsIgnoreCase(type)) {
            character.setType(CharacterType.HERO);
        } else {
            character.setType(CharacterType.VILIAN);
        }

        Speciality s = getSpeciality(speciality);
        character.setPunch(punch);
        character.setHit(hit);
        character.setKick(kick);
        character.setAttack(attack);
        character.setSpeciality(s);
        return character;
    }
}
