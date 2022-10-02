package ru.birdiecode.postman.screen;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import java.util.List;

import ru.birdiecode.postman.intrafaces.MailInterface;

public class Splash implements Screen {
    String SPRITE = "<`)     //<`)// (//(`>/ (`> (/  (\\\\  1  \\  \"birdiecode";

    int[][][] frame = {
        {{0, 6},
            {15, 19},
            {35, 39}}, // frame 0
        {{5, 10},
            {19, 24},
            {35, 39}}, // frame 1
        {{5, 10},
            {23, 28},
            {35, 39}}, // frame 2
        {{10, 15},
            {27, 32},
            {35, 39}}, // frame 3
        {{0, 6},
            {32, 36},
            {39, 43}}, // frame 4
        {{10, 15},
            {27, 32},
            {42, 44}}, // frame 5
        {{0, 6},
            {32, 36},
            {42, 44}}
    };

    int[] anim = {0,0,1,2,1,2,1,0,3,4,5,6,5,6,5,6,0};
    BitmapFont font;

    SpriteBatch batch;
    OrthographicCamera camera;
    Viewport viewport;

    int widthCamera = 720;
    int heightCamera = 1280;
    float time = 0;
    int a = 0;
    Game game;
    List<MailInterface> mails;

    public Splash(Game game, List<MailInterface> mails) {
        this.game = game;
        this.mails = mails;
    }

    @Override
    public void show() {
        batch = new SpriteBatch();
        camera = new OrthographicCamera();
        viewport = new FitViewport(widthCamera, heightCamera, camera);
        camera.position.set(new Vector3(widthCamera/2, heightCamera/2, 0));

        font = new BitmapFont(Gdx.files.internal("font/4.fnt"), Gdx.files.internal("font/4.png"), false);
    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(1, 1, 1, 1);
        time+=delta;
        camera.update();
        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        if (time > a*0.5)  a++;
        if(a>=anim.length) {
            game.setScreen(new SendMail2(game, mails));
            return;
        }

        int e = anim[a];


        font.draw(batch, SPRITE.substring(frame[e][0][0], frame[e][0][1]), widthCamera/2-60, heightCamera/2+48);
        font.draw(batch, SPRITE.substring(frame[e][1][0], frame[e][1][1]), widthCamera/2-60, heightCamera/2);
        font.draw(batch, SPRITE.substring(frame[e][2][0], frame[e][2][1]), widthCamera/2-60, heightCamera/2-48);
        font.draw(batch, SPRITE.substring(44, 54), widthCamera/2-120, heightCamera/2-96);

        batch.end();

    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height, true);
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

    }
}
