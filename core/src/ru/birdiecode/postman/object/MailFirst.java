package ru.birdiecode.postman.object;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import ru.birdiecode.postman.intrafaces.MailInterface;

public class MailFirst implements MailInterface {

    private final Sprite texture;
    private final Sprite mail;
    private final String address;
    private final BitmapFont font;
    private final float width;
    private final float height;

    public MailFirst(String address) {
        this.texture = new Sprite(new Texture("mail/first/texture.png"));
        this.mail = new Sprite(new Texture("mail/first/mail.png"));
        this.font = new BitmapFont(Gdx.files.internal("font/8.fnt"),
                Gdx.files.internal("font/8.png"), false);
        this.font.getData().setScale(0.8f);
        this.address = address;
        this.width = Gdx.graphics.getWidth()/2.5f;
        this.height = width/556*286;
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
        font.draw(batch, address, mail.getX()+mail.getWidth()/10, mail.getY()+mail.getHeight()/2.5f);
    }
}
