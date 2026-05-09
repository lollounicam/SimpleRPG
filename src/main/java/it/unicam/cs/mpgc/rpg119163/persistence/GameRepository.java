package it.unicam.cs.mpgc.rpg119163.persistence;

import it.unicam.cs.mpgc.rpg119163.model.Game;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;


public class GameRepository {
    private final Gson gson;

    public GameRepository() {
        this.gson = new GsonBuilder()
                .setPrettyPrinting()
                .create();
    }

    public void saveGame(final Game game, final String filePath) {
        try (FileWriter writer = new FileWriter(filePath)) {
            this.gson.toJson(game, writer);
        } catch (IOException e) {
            throw new IllegalStateException("unable to save the game", e);
        }
    }

    public Game loadGame(final String filePath){
        try (FileReader reader = new FileReader(filePath)) {
            return this.gson.fromJson(reader, Game.class);
        } catch (IOException e) {
            throw new IllegalStateException("unable to load the game", e);
        }
    }
}
