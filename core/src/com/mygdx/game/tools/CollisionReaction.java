package com.mygdx.game.tools;

public class CollisionReaction {

    float x, y;
    int width, height;

    public CollisionReaction (float x, float y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public void move (float x, float y) {
        this.x = x;
        this.y = y;
    }

    public boolean collidesWith (CollisionReaction rect) {
        return x < rect.x + rect.width && y < rect.y + rect.height && x + width > rect.x && y + height > rect.y;
    }

}
