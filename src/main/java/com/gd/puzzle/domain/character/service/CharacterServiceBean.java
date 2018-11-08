package com.gd.puzzle.domain.character.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.gd.puzzle.domain.character.model.GameCharacter;
import com.gd.puzzle.enums.CharacterType;
import com.gd.puzzle.exception.CharacterServiceException;
import com.gd.puzzle.repository.GameRepository;
import com.gd.puzzle.repository.Repository;

public class CharacterServiceBean implements CharacterService {
    private static CharacterServiceBean characterService;
    private static Repository repository = GameRepository.getGameRepository();

    public static CharacterServiceBean getCharacterService() {
        if (characterService == null) {
            synchronized (CharacterServiceBean.class) {
                characterService = new CharacterServiceBean();
            }
        }
        return characterService;
    }

    private CharacterServiceBean() {
    }

    @Override
    public void addCharacter(GameCharacter character, String seriesName) throws CharacterServiceException {
        if (character.getType()
                     .ordinal() == CharacterType.HERO.ordinal()) {
            repository.addHeroCharacter(character, seriesName);
        } else {
            repository.addVillainCharacter(character, seriesName);
        }
    }

    @Override
    public List<GameCharacter> getAvailableHeroes(String selectedSeries) throws CharacterServiceException {
        Map<String, GameCharacter> heroes = repository.getAvailableHeroes(selectedSeries);
        if (heroes == null) {
            return new ArrayList<>();
        } else {
            return new ArrayList<>(heroes.values());
        }

    }

    @Override
    public List<GameCharacter> getAvailableVillains(String selectedSeries) throws CharacterServiceException {
        Map<String, GameCharacter> villains = repository.getAvailableVillains(selectedSeries);
        if (villains == null) {
            return new ArrayList<>();
        } else {
            return new ArrayList<>(villains.values());
        }
    }
}
