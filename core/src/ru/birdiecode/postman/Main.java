package ru.birdiecode.postman;

import com.badlogic.gdx.Game;

import ru.birdiecode.postman.screen.SendMail1;
import ru.birdiecode.postman.screen.SendMail2;

public class Main extends Game {

	@Override
	public void create () {
		setScreen(new SendMail2(this));
	}
}
