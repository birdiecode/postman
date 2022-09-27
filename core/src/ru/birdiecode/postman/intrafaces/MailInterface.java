package ru.birdiecode.postman.intrafaces;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public interface MailInterface {
    void setPosition(float x, float y);

    boolean isMoving();

    void setMove(boolean s);

    void draw(SpriteBatch batch);

    void dispose();
}
