package ru.birdiecode.postman.object;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import ru.birdiecode.postman.intrafaces.MailInterface;

public class BoxList implements MailInterface {
    int[] num;
    Sprite box;
    Texture tt;

    public BoxList(int num1, int num2, int num3, int num4) {
        this.num = new int[] {num1, num2, num3, num4};
        this.box = new Sprite(new Texture("box/list.png"));
        this.box.setBounds( 0, 0, 454, 603);
    }

    public BoxList(int num1, int num2, int num3, int num4, float x, float y ) {
        this.num = new int[] {num1, num2, num3, num4};
        this.tt = new Texture("box/list.png");
        this.box = new Sprite(tt);
        this.box.setBounds( x, y, 454, 603);
    }

    @Override
    public void setPosition(float x, float y) {
        this.box.setPosition(x-box.getWidth()/2, y- box.getHeight());
    }

    @Override
    public void draw(SpriteBatch batch) {
        this.box.draw(batch);
    }

    @Override
    public boolean isMoving() {
        return false;
    }

    @Override
    public void setMove(boolean s) {

    }

    @Override
    public void dispose() {
        this.tt.dispose();
    }
}
