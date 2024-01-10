package com.mygdx.game.entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.tools.CollisionReaction;

public class Cactus {

    public static final int SPEED = 120;
    public static final int WIDTH = 32;
    public static final int HEIGHT = 32;

    private static Texture texture;

    float x, y;
    CollisionReaction rect;
    public boolean remove = false;

    public Cactus(int x, int y){
        this.x = x;
        this.y = y;
        this.rect = new CollisionReaction(x, y, WIDTH, HEIGHT);

    }
    public void update (float deltaTime) {
        x -= SPEED * deltaTime;
        if (x < -WIDTH)
            remove = true;
            MyGdxGame.TIME += MyGdxGame.TIME;
        rect.move(x, y);
    }

    public void render (SpriteBatch batch) {
        batch.draw(texture, x, y, WIDTH, HEIGHT);
    }
    public CollisionReaction getCollisionRect () {
//        System.out.printf(""+x);
//        System.out.printf(""+y);
        return rect;
    }

    public float getX () {
        return x;
    }

    public float getY () {
        return y;
    }

    public static void setTexture(Texture texture) {
        Cactus.texture = texture;
    }
    public static Texture getTexture() {
        return texture;
    }
}