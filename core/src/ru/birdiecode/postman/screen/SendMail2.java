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
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import java.util.List;

import ru.birdiecode.postman.input.InputControllerManager;
import ru.birdiecode.postman.input.SendMail1InputController;
import ru.birdiecode.postman.intrafaces.MailInterface;
import ru.birdiecode.postman.intrafaces.ObjectDirectorInterface;
import ru.birdiecode.postman.object.BoxList;

public class SendMail2 implements Screen {
    Game game;
    OrthographicCamera camera;
    Viewport viewport;
    CameraSwipe cs;
    SpriteBatch batch;
    int widthCamera = 720;
    int heightCamera = 1280;

    Texture texturebg;
    BoxList[] boxLists = new BoxList[10];

    List<MailInterface> mails;

    private static class CameraSwipe implements ObjectDirectorInterface {
        OrthographicCamera camera;
        int lx;
        float cx;
        float rx;

        float settingCamPos;
        boolean camScrol = false;


        public CameraSwipe(OrthographicCamera camera) {
            this.camera = camera;
            lx = 0;
        }

        @Override
        public boolean statementRegion(float x, float y) {
            return y < 900;
        }

        @Override
        public boolean touchDown(int screenX, int screenY, int pointer, int button) {
            if (!camScrol) {
                lx = screenX;
                cx = camera.position.x;
            }
            return false;
        }

        @Override
        public boolean touchUp(int screenX, int screenY, int pointer, int button) {
            camScrol = true;
            if (!(camera.position.x - camera.viewportWidth / 2 == 0 && lx - screenX < 0) && !(camera.position.x - camera.viewportWidth / 2 == camera.viewportWidth * 9 && lx - screenX > 0)) {
                if (rx > camera.viewportWidth / 3) {
                    settingCamPos = cx + camera.viewportWidth;
                } else if (-rx > camera.viewportWidth / 3) {
                    settingCamPos = cx - camera.viewportWidth;
                } else settingCamPos = cx;
            }
            else{
                camScrol = false;
            }
            lx = 0;
            return false;
        }

        @Override
        public boolean touchDragged(int screenX, int screenY, int pointer) {
            if (!camScrol) {
                if (!(camera.position.x - camera.viewportWidth / 2 == 0 && lx - screenX < 0) && !(camera.position.x - camera.viewportWidth / 2 == camera.viewportWidth * 9 && lx - screenX > 0)) {
                    rx = lx - screenX;
                    camera.position.x = cx + rx;
                }
            }
            return false;
        }

        void movingCamera() {
            if (camScrol) {
                if (camera.position.x == settingCamPos) camScrol = false;
                else {
                    if (Math.abs(camera.position.x - settingCamPos) > 50) {
                        if (camera.position.x > settingCamPos) camera.position.x -= 50;
                        else camera.position.x += 50;
                    } else camera.position.x = settingCamPos;
                }
            }

        }
    }

    private static class MailSwipe implements ObjectDirectorInterface {
        List<MailInterface> mails;
        OrthographicCamera camera;

        public MailSwipe(List<MailInterface> mails, OrthographicCamera camera) {
            this.mails = mails;
            this.camera = camera;
        }

        @Override
        public boolean statementRegion(float x, float y) {
            return y > 900;
        }

        @Override
        public boolean touchDown(int screenX, int screenY, int pointer, int button) {
            mails.get(mails.size() - 1).setMove(true);
            return false;
        }

        @Override
        public boolean touchUp(int screenX, int screenY, int pointer, int button) {
            mails.get(mails.size() - 1).setPosition(camera.position.x, camera.viewportHeight / 9);
            mails.get(mails.size() - 1).setMove(false);
            if (screenY > 900) {
                if (screenX > camera.position.x) {
                    MailInterface temp = mails.get(0);
                    mails.remove(temp);
                    mails.add(temp);
                } else {
                    MailInterface temp = mails.get(mails.size() - 1);
                    mails.remove(temp);
                    mails.add(0, temp);
                }
            }
            return false;
        }

        @Override
        public boolean touchDragged(int screenX, int screenY, int pointer) {
            mails.get(mails.size() - 1).setPosition(camera.position.x - camera.viewportWidth / 2 + screenX, camera.viewportHeight - screenY);
            return false;
        }
    }

    public SendMail2(Game game, List<MailInterface> mails) {
        this.game = game;
        this.mails = mails;
    }

    @Override
    public void show() {
        batch = new SpriteBatch();
        camera = new OrthographicCamera();
        viewport = new FitViewport(widthCamera, heightCamera, camera);
        camera.position.set(new Vector3(widthCamera / 2, heightCamera / 2, 0));

        texturebg = new Texture("screen_sendmail2_background.jpg");

        InputControllerManager icm = new InputControllerManager(viewport, widthCamera, heightCamera);
        cs = new CameraSwipe(camera);
        icm.addObjectInput(cs);
        icm.addObjectInput(new MailSwipe(this.mails, this.camera));
        Gdx.input.setInputProcessor(icm);

        for (int i = 0; i < boxLists.length; i++) {
            boxLists[i] = new BoxList(i * 4 + 1, i * 4 + 2, i * 4 + 3, i * 4 + 4);
            boxLists[i].setPosition(widthCamera / 2 + widthCamera * i, heightCamera / 9 * 8);
        }
        int i = 3;
        for (MailInterface m :
                this.mails) {
            m.setPosition((widthCamera / 2) + i, heightCamera / 9);
            i += 100;
        }
    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(0, 0, 0, 1);

        cs.movingCamera();

        for (MailInterface m : this.mails) {
            if (!m.isMoving()) m.setPosition(camera.position.x, heightCamera / 9);
        }

        camera.update();
        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        batch.draw(texturebg, 0, 0, widthCamera * 10, heightCamera);
        for (BoxList wbox : boxLists) wbox.draw(batch);
        for (MailInterface m : this.mails) m.draw(batch);
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
        batch.dispose();
        texturebg.dispose();
        for (BoxList wbox : boxLists) wbox.dispose();
    }
}
