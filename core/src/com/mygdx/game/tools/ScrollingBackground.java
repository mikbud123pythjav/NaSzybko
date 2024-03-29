package com.mygdx.game.tools;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import com.mygdx.game.MyGdxGame;
public class ScrollingBackground {

    public static final int DEFAULT_SPEED = 80;
    public static final int ACCELERATION = 50;
    public static final int GOAL_REACH_ACCELERATION = 200;

    Texture image;
    float y1, y2;
    int speed;//In pixels / second
    int goalSpeed;
    float imageScale;
    boolean speedFixed;

    public ScrollingBackground () {
        image = new Texture("rolkaDoMenuGłównego.jpg");

        y1 = image.getHeight();
        y2 = 0;
        speed = 0;
        goalSpeed = DEFAULT_SPEED;
        imageScale = MyGdxGame.WIDTH / image.getWidth();
        speedFixed = true;
    }

    public void updateAndRender (float deltaTime, SpriteBatch batch) {
        //Speed adjustment to reach goal
        if (speed < goalSpeed) {
            speed += GOAL_REACH_ACCELERATION * deltaTime;
            if (speed > goalSpeed)
                speed = goalSpeed;
        } else if (speed > goalSpeed) {
            speed -= GOAL_REACH_ACCELERATION * deltaTime;
            if (speed < goalSpeed)
                speed = goalSpeed;
        }

        if (!speedFixed)
            speed += ACCELERATION * deltaTime;

        y1 -= speed * deltaTime;
        y2 -= speed * deltaTime;

        //if image reached the bottom of screen and is not visible, put it back on top
        if (y2 + image.getHeight() * imageScale <= 0)
            y1 = y2 + image.getHeight() * imageScale;

        if (y2 + image.getHeight() * imageScale <= 0)
            y1 = y2 + image.getHeight() * imageScale;

        //Render
        batch.draw(image, y2, 0, MyGdxGame.WIDTH, image.getHeight() * imageScale);
        batch.draw(image, y1, 0, MyGdxGame.WIDTH, image.getHeight() * imageScale);
    }

    public void setSpeed (int goalSpeed) {
        this.goalSpeed = goalSpeed;
    }

    public void setSpeedFixed (boolean speedFixed) {
        this.speedFixed = speedFixed;
    }

}