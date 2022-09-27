package ru.birdiecode.postman.intrafaces;

public interface ObjectDirectorInterface {
    boolean statementRegion(float x, float y);

    boolean touchDown(int screenX, int screenY, int pointer, int button);

    boolean touchUp(int screenX, int screenY, int pointer, int button);

    boolean touchDragged(int screenX, int screenY, int pointer);
}
