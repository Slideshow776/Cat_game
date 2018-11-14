package com.sandra.game.screens;
import com.sandra.game.Cat_game;

public class Level_loader extends Level {
    public Level_loader(Cat_game game, String level) {
        super(game, "levels/" + level + ".tmx");
    }
}
