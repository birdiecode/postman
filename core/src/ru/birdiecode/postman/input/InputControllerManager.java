package ru.birdiecode.postman.input;

import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.utils.viewport.Viewport;

import java.util.ArrayList;
import java.util.List;

import ru.birdiecode.postman.intrafaces.ObjectDirectorInterface;

public class InputControllerManager implements InputProcessor {

    List<ObjectDirectorInterface> ObjectInputList = new ArrayList<>();
    ObjectDirectorInterface ObjectInputMaster;
    Viewport viewport;
    int widthCamera, heightCamera;

    public InputControllerManager(Viewport viewport, int widthCamera, int heightCamera) {
        this.viewport = viewport;
        this.widthCamera = widthCamera;
        this.heightCamera = heightCamera;
    }

    public int[] convertingСoordinates(int screenX, int screenY){
        int[] returnresult = {-1, -1};
        if( screenX < viewport.getScreenX() || screenX > viewport.getScreenX() + viewport.getScreenWidth()) return returnresult;
        if( screenY < viewport.getScreenY() || screenY > viewport.getScreenY() + viewport.getScreenHeight()) return returnresult;

        float lw = ((float) viewport.getScreenWidth())/((float) widthCamera);
        double lh = ((float)viewport.getScreenHeight())/((float)heightCamera);

        returnresult[0] = (int) (((float) screenX-viewport.getScreenX())/lw);
        returnresult[1] = (int) (((float) screenY-viewport.getScreenY())/lh);

        return returnresult;
    }

    public void addObjectInput(ObjectDirectorInterface oi){
        ObjectInputList.add(oi);
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
        int[] cc = convertingСoordinates(screenX, screenY);
        if (cc[0] != -1) {
            for (ObjectDirectorInterface odi: ObjectInputList) {
                if (odi.statementRegion(cc[0], cc[1])) {
                    ObjectInputMaster = odi;
                    return ObjectInputMaster.touchDown(cc[0], cc[1], pointer, button);
                }
            }
        }
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        if (ObjectInputMaster != null) {
            int[] cc = convertingСoordinates(screenX, screenY);
            if (cc[0] != -1) {
                boolean returncode = ObjectInputMaster.touchUp(cc[0], cc[1], pointer, button);
                ObjectInputMaster = null;
                return returncode;
            }
        }
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        if (ObjectInputMaster != null) {
            int[] cc = convertingСoordinates(screenX, screenY);
            if (cc[0] != -1) {
                return ObjectInputMaster.touchDragged(cc[0], cc[1], pointer);
            }
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
