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

public class SendMail2 implements Screen {
    Game game;
    OrthographicCamera camera;
    SpriteBatch batch;
    Texture texturebg;
    Texture texturebox;
    Sprite boxes[] = new Sprite[10];
    int gb_wh;
    int gb_ht;


    public SendMail2(Game game){
        this.game = game;
    }

    @Override
    public void show() {

        batch = new SpriteBatch();
        camera = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        camera.position.set(new Vector3(Gdx.graphics.getWidth()/2+(Gdx.graphics.getWidth()*0), Gdx.graphics.getHeight()/2, 0));

        texturebg = new Texture("screen_sendmail2_background.jpg");
        texturebox = new Texture("screen_sendmail2_box.png");

        gb_wh = Gdx.graphics.getWidth() - Gdx.graphics.getWidth()/45*16;
        gb_ht = gb_wh/3*4;

        for (int i = 0; i < boxes.length; i++) {
            boxes[i] = new Sprite(texturebox);
            boxes[i].setBounds( Gdx.graphics.getWidth()/45*8+Gdx.graphics.getWidth()*i, Gdx.graphics.getHeight()-gb_ht*1.25f, gb_wh, gb_ht); //9:5
        }

        Gdx.input.setInputProcessor(new SendMail1InputController(camera));
    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(0, 0, 0, 1);

        camera.update();
        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        batch.draw(texturebg, 0, 0, Gdx.graphics.getWidth()*10, Gdx.graphics.getHeight());
        for (Sprite wbox:boxes) wbox.draw(batch);
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
        texturebg.dispose();
        texturebox.dispose();
    }
}
