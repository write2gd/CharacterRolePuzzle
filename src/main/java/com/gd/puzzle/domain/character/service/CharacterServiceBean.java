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
    public List<GameCharacter> getAvailableHeros(String selectedSeries) throws CharacterServiceException {
        Map<String, GameCharacter> heros = repository.getAvailableHeros(selectedSeries);
        if (heros == null) {
            return new ArrayList<>();
        } else {
            return new ArrayList<>(heros.values());
        }

    }

    @Override
    public List<GameCharacter> getAvailableVilians(String selectedSeries) throws CharacterServiceException {
        Map<String, GameCharacter> villans = repository.getAvailableVillains(selectedSeries);
        if (villans == null) {
            return new ArrayList<>();
        } else {
            return new ArrayList<>(villans.values());
        }
    }
}
