package ru.birdiecode.postman.screen;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.ScreenUtils;

import ru.birdiecode.postman.input.SendMail1InputController;
import sun.font.TrueTypeFont;

public class SendMail1 implements Screen {
    SpriteBatch batch;
    Texture img;
    Texture img2;
    Game game;
    OrthographicCamera camera;
    Sprite boxes[] = new Sprite[20];
    BitmapFont font;
    float wh;
    float ht;


    public SendMail1(Game game){
        this.game = game;
    }

    @Override
    public void show() {

        batch = new SpriteBatch();
        camera = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        camera.position.set(new Vector3(Gdx.graphics.getWidth()/2+(Gdx.graphics.getWidth()*0), Gdx.graphics.getHeight()/2, 0));

        img = new Texture("screen_sendmail1_background.jpg");
        img2 = new Texture("screen_sendmail1_box.png");
        wh = Gdx.graphics.getWidth()/6*5;
        ht = wh/9*5;
        font = new BitmapFont(Gdx.files.internal("font/2.fnt"),
                Gdx.files.internal("font/2.png"), false);


        for (int i = 0; i < boxes.length; i++) {
            boxes[i] = new Sprite(img2);
            boxes[i].setBounds((Gdx.graphics.getWidth() - wh)/2 + ((int) i/2 )*Gdx.graphics.getWidth(), Gdx.graphics.getHeight()-ht*(i%2==1 ? 1 : 2)-ht/2, wh, ht); //9:5
        }

        Gdx.input.setInputProcessor(new SendMail1InputController(camera));
    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(0, 0, 0, 1);

        camera.update();
        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        batch.draw( img, 0, 0, Gdx.graphics.getWidth()*10, Gdx.graphics.getHeight());
        for (Sprite wbox:boxes) wbox.draw(batch);
        for (int i = 0; i < 60; i++) {
            int inscreenbox = i/6;
            switch (i%6%3){
                case 0:
                    font.draw(batch, ""+(i+1), (inscreenbox+1) * Gdx.graphics.getWidth() - wh, Gdx.graphics.getHeight() - (ht * (1.1f + (((int)i/3)%2))));
                    break;
                case 1:
                    font.draw(batch, ""+(i+1), (inscreenbox+1) * Gdx.graphics.getWidth() - wh/ 3 * 2, Gdx.graphics.getHeight() - (ht * (1.1f + (((int)i/3)%2))));
                    break;
                case 2:
                    font.draw(batch, ""+(i+1), (inscreenbox+1) * Gdx.graphics.getWidth() - wh/ 3 * 1, Gdx.graphics.getHeight() - (ht * (1.1f + (((int)i/3)%2))));
                    break;
            }
        }
        batch.end();
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        batch.dispose();
        img.dispose();
    }
}
