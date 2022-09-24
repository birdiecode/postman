package ru.birdiecode.postman.screen;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.ScreenUtils;

import ru.birdiecode.postman.input.SendMail1InputController;

public class SendMail1 implements Screen {
    SpriteBatch batch;
    Texture img;
    Game game;
    OrthographicCamera camera;


    public SendMail1(Game game){
        this.game = game;
    }

    @Override
    public void show() {

        batch = new SpriteBatch();
        camera = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        camera.position.set(new Vector3(Gdx.graphics.getWidth()/2+(Gdx.graphics.getWidth()*0), Gdx.graphics.getHeight()/2, 0));
        Gdx.input.setInputProcessor(new SendMail1InputController(camera));
        img = new Texture("screen_sendmail1_background.jpg");

    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(0, 0, 0, 1);

        camera.update();
        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        batch.draw(img, 0, 0, Gdx.graphics.getWidth()*10, Gdx.graphics.getHeight());
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
