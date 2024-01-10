package com.mygdx.game.tools;

import com.badlogic.gdx.graphics.OrthographicCamera;

public class Camera {
    private OrthographicCamera camera;

    public void initialize(float viewportWidth, float viewportHeight) {
        camera = new OrthographicCamera(viewportWidth, viewportHeight);
        camera.position.set(viewportWidth / 2f, viewportHeight / 2f, 0);
        camera.update();
    }

    public void update() {
        // Przesuń kamerę w prawo
        camera.position.x += 2; // Możesz dostosować wartość przesunięcia według potrzeb

        // Aktualizuj kamerę
        camera.update();
    }

    public OrthographicCamera getCamera() {
        return camera;
    }
}
