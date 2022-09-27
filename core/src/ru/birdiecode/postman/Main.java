package ru.birdiecode.postman;

import com.badlogic.gdx.Game;

import java.util.ArrayList;
import java.util.List;

import ru.birdiecode.postman.intrafaces.MailInterface;
import ru.birdiecode.postman.object.MailColor;
import ru.birdiecode.postman.object.MailWax;
import ru.birdiecode.postman.screen.SendMail1;
import ru.birdiecode.postman.screen.SendMail2;

public class Main extends Game {

	@Override
	public void create () {
		List<MailInterface> mails = new ArrayList<>();
		mails.add(new MailColor(MailColor.TEXTURE1, MailColor.MAIL_BLUE, "дом 1 кв 2"));
		mails.add(new MailColor(MailColor.TEXTURE2, MailColor.MAIL_RED, "дом 1 кв 2"));
		mails.add(new MailColor(MailColor.TEXTURE1, MailColor.MAIL_YELLOW, "дом 1 кв 2"));
		mails.add(new MailColor(MailColor.TEXTURE1, MailColor.MAIL_BLUE, "дом 1 кв 2"));
		mails.add(new MailColor(MailColor.TEXTURE5, MailColor.MAIL_PAPER, "дом 1 кв 2"));
		mails.add(new MailColor(MailColor.TEXTURE3, MailColor.MAIL_PURPLE, "дом 1 кв 2"));
		mails.add(new MailColor(MailColor.TEXTURE1, MailColor.MAIL_YELLOW, "дом 1 кв 2"));
		mails.add(new MailColor(MailColor.TEXTURE4, MailColor.MAIL_PURPLE, "дом 1 кв 2"));
		mails.add(new MailColor(MailColor.TEXTURE1, MailColor.MAIL_RED, "дом 1 кв 2"));



		setScreen(new SendMail2(this, mails));
	}
}
