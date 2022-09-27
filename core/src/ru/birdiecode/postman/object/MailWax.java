package ru.birdiecode.postman.object;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import ru.birdiecode.postman.intrafaces.MailInterface;

public class MailWax implements MailInterface {
    public static String TEXTURE1 = "texture1.png";
    public static String TEXTURE2 = "texture2.png";

    private final Sprite texture;
    private final Sprite mail;
    private final String address;
    BitmapFont font;
    private boolean movestatus;

    public MailWax(String texture, String address) {
        this.texture = new Sprite(new Texture("mail/wax/"+texture));
        this.mail = new Sprite(new Texture("mail/wax/mail.png"));
        this.font = new BitmapFont(Gdx.files.internal("font/8.fnt"),
                Gdx.files.internal("font/8.png"), false);
        this.font.getData().setScale(0.8f);
        this.address = address;
        float width = Gdx.graphics.getWidth()/2.5f;
        float height = width/556*286;
        this.mail.setBounds( 0, 0, width, height);
        this.texture.setBounds( 0, 0, width, height);
    }

    @Override
    public void setPosition(float x, float y) {
        mail.setPosition( x-mail.getWidth()/2, y-mail.getY()/2);
        texture.setPosition(x-mail.getWidth()/2, y-mail.getY()/2);
    }

    @Override
    public boolean isMoving() {
        return this.movestatus;
    }

    @Override
    public void setMove(boolean s) {
        this.movestatus = s;
    }

    @Override
    public void draw(SpriteBatch batch) {
        mail.draw(batch);
        texture.draw(batch);
        font.draw(batch,address, mail.getX()+mail.getWidth()/3, mail.getY()+mail.getHeight()/10);
    }

    @Override
    public void dispose() {

    }
}
