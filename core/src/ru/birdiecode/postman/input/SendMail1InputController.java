package ru.birdiecode.postman.input;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.OrthographicCamera;

public class SendMail1InputController implements InputProcessor {
    OrthographicCamera camera;
    int lx;
    float cx;
    float rx;

    public SendMail1InputController(OrthographicCamera camera){
        this.camera = camera;
        lx = 0;
    }

    @Override
    public boolean keyDown(int keycode) {
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        lx = screenX;
        cx = camera.position.x;
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {

        if(!(camera.position.x-Gdx.graphics.getWidth()/2 == 0 && lx-screenX < 0) && !(camera.position.x-Gdx.graphics.getWidth()/2 == Gdx.graphics.getWidth()*9 && lx-screenX > 0)) {
            if (rx > Gdx.graphics.getWidth() / 2) {
                camera.position.x = cx + Gdx.graphics.getWidth();
            } else if (-rx > Gdx.graphics.getWidth() / 2) {
                camera.position.x = cx - Gdx.graphics.getWidth();
            } else camera.position.x = cx;
        }
        lx = 0;
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        System.out.println(camera.position.x-Gdx.graphics.getWidth()/2);

        if( !(camera.position.x-Gdx.graphics.getWidth()/2 == 0 && lx-screenX < 0) && !(camera.position.x-Gdx.graphics.getWidth()/2 == Gdx.graphics.getWidth()*9 && lx-screenX > 0)){
            rx = lx-screenX;
            camera.position.x = cx + rx;
        }
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(float amountX, float amountY) {
        return false;
    }
}
