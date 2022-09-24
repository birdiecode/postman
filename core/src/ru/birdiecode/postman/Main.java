package ru.birdiecode.postman;

import com.badlogic.gdx.Game;

import ru.birdiecode.postman.screen.SendMail;

public class Main extends Game {

	@Override
	public void create () {
		setScreen(new SendMail(this));
	}
}
