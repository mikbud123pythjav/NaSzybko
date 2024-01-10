package com.mygdx.game.entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.tools.CollisionReaction;

public class Banana {

    public static final int SPEED = 80;
    public static final int WIDTH = 32;
    public static final int HEIGHT = 32;

    private static Texture texture;

    float x, y;
    CollisionReaction rect;
    public boolean remove = false;

    public Banana(int y){
        this.x = MyGdxGame.WIDTH;
        this.y = y;
        this.rect = new CollisionReaction(x, y, WIDTH, HEIGHT);

    }
    public void update (float deltaTime) {
        y -= SPEED * deltaTime;
        if (y < -HEIGHT)
            remove = true;

        rect.move(x, y);
    }

    public void render (SpriteBatch batch) {
        batch.draw(texture, x, y, WIDTH, HEIGHT);
    }

    public CollisionReaction getCollisionRect () {
        return rect;
    }

    public float getX () {
        return x;
    }

    public float getY () {
        return y;
    }
}
