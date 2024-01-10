package com.mygdx.game;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.ScreenUtils;
import com.mygdx.game.screens.GameOverScreen;
import com.mygdx.game.screens.MainGameScreen;
import com.mygdx.game.screens.MainMenuScreen;
import com.mygdx.game.tools.GameCamera;
import com.mygdx.game.tools.ScrollingBackground;

public class MyGdxGame extends Game {
	public static final int WIDTH = 1024;
	public static final int HEIGHT = 768;
	public static boolean IS_MOBILE = false;
	public static float TIME;
	public static int SCORE;
	public static int LEVEL;
	public GameCamera cam;
	public ScrollingBackground scrollingBackground;
	public SpriteBatch batch;

	@Override
	public void create () {
		batch = new SpriteBatch();
		cam = new GameCamera(WIDTH, HEIGHT);
		this.scrollingBackground = new ScrollingBackground();
		this.setScreen(new MainMenuScreen(this));
	}

	@Override
	public void render () {
		batch.setProjectionMatrix(cam.combined());
		super.render();
	}

	@Override
	public void resize(int width, int height) {
		cam.update(width, height);
		super.resize(width, height);
	}

}


