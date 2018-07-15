package com.sandra.game.handlers;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;


public class MenuInputListener extends InputListener {

	private boolean enter;
	private boolean touched;

	public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
		touched = true;
		return true;
	}

	public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
		enter = true;
	}

	public void exit(InputEvent event, float x, float y, int pointer, Actor toActor) {
		enter = false;
	}

	public boolean getEnter() {return enter;}
	public boolean getTouched() {return touched;}
	public void setTouched(boolean b) {touched = b;}
}