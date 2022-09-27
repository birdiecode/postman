package ru.birdiecode.postman.object;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import ru.birdiecode.postman.intrafaces.MailInterface;

public class MailColor implements MailInterface {
    public static String MAIL_BLUE   = "mail_blue.png";
    public static String MAIL_GREEN  = "mail_green.png";
    public static String MAIL_ORANGE = "mail_orange.png";
    public static String MAIL_PAPER  = "mail_paper.png";
    public static String MAIL_PURPLE = "mail_purpel.png";
    public static String MAIL_RED    = "mail_red.png";
    public static String MAIL_YELLOW = "mail_yellow.png";

    public static String TEXTURE1 = "texture1.png";
    public static String TEXTURE2 = "texture2.png";
    public static String TEXTURE3 = "texture3.png";
    public static String TEXTURE4 = "texture4.png";
    public static String TEXTURE5 = "texture5.png";

    private final Sprite texture;
    private final Sprite mail;
    private final String address;
    BitmapFont font;
    private float width;
    private float height;


    public MailColor(String texture, String mail, String address) {
        this.texture = new Sprite(new Texture("mail/color/" + texture));
        this.mail = new Sprite(new Texture("mail/color/" + mail));
        font = new BitmapFont(Gdx.files.internal("font/8.fnt"),
                Gdx.files.internal("font/8.png"), false);
        this.address = address;
        width = Gdx.graphics.getWidth()/2.5f;
        height = width/556*286;
    }

    @Override
    public void setPosition(float x, float y) {
        mail.setBounds( x-width/2, y-height/2, width, height);
        texture.setBounds( x-width/2, y-height/2, width, height);

    }

    @Override
    public void draw(SpriteBatch batch) {
        mail.draw(batch);
        texture.draw(batch);
        font.draw(batch,address, mail.getX()+mail.getWidth()/3, mail.getY()+50);
    }
}
