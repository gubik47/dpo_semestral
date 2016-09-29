package cz.fit.dpo.mvcshooter.memento;

import cz.fit.dpo.mvcshooter.model.Model;

/**
 * Created by Jakub on 03.12.2015.
 */
public class GameSave {

    private Model.Memento savedGame;

    public void saveGame(Model.Memento memento) {
        savedGame = memento;
    }

    public Model.Memento loadGame() {
        return savedGame;
    }
}
