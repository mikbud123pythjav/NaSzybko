package com.mygdx.game.screens;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.screens.MainGameScreen;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.graphics.g2d.BitmapFont;

public class QuestionScreen implements Screen {
    MyGdxGame game;
    BitmapFont questionFont;
    public static String[] questions = new String[3];
    public static String[] answers = new String[6];
    public static int questionNumber = 0;
    public static int answer1 = 0;
    public static int answer2 = 1;

    public QuestionScreen(MyGdxGame game) {
        this.game = game;
        questions[0] = "8*8";
        questions[1] = "5+2";
        questions[2] = "12*13";


        answers[0] = "64";
        answers[1] = "7";
        answers[2] = "156";

        questionFont = new BitmapFont(Gdx.files.internal("fonts/score.fnt"));

    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        game.batch.begin();

        GlyphLayout questionLayout = new GlyphLayout(questionFont,
                "Choose the correct answer: \n\n" + questions[questionNumber],
                Color.WHITE
                , 0,
                Align.left, false);
        questionFont.draw(game.batch, questionLayout, Gdx.graphics.getWidth() / 2 - questionLayout.width / 2,
                Gdx.graphics.getHeight() - 30);
        
        GlyphLayout answer1Layout = new GlyphLayout(questionFont, answers[answer1]);
        GlyphLayout answer2Layout = new GlyphLayout(questionFont, answers[answer2]);
        GlyphLayout zadneLayout = new GlyphLayout(questionFont, "NONE");

        float answer1X = Gdx.graphics.getWidth() / 2 - answer1Layout.width / 2;
        float answer1Y = Gdx.graphics.getHeight() / 2 - answer1Layout.height / 2 - 100;
        float answer2X = Gdx.graphics.getWidth() / 2 - answer2Layout.width / 2;
        float answer2Y = Gdx.graphics.getHeight() / 2 - answer2Layout.height / 2 - answer1Layout.height - 130;
        float zadneX = Gdx.graphics.getWidth() / 2 - zadneLayout.width / 2 ;
        float zadneY = Gdx.graphics.getHeight() / 2 - zadneLayout.height / 2 - answer2Layout.height - 190;

        float touchX = Gdx.input.getX(), touchY = Gdx.graphics.getHeight() - Gdx.input.getY();

        //if try again and main menu is being pressed
        if(Gdx.input.isTouched()) {
            if (touchX > answer1X && touchX < answer1X + answer1Layout.width && touchY > answer1Y - answer1Layout.height && touchY < answer1Y) {
                if (MyGdxGame.LEVEL == 2){
                    MyGdxGame.SCORE += 1;
                }
                this.dispose();
                game.batch.end();
                game.setScreen(new MainGameScreen(game));
                questionNumber += 1;
                answer1 += 2;
                answer2 += 2;
                MyGdxGame.LEVEL += 1;

                System.out.println(MyGdxGame.SCORE);
                System.out.println(MyGdxGame.LEVEL);
                return;
            }

            if (touchX > answer2X && touchX < answer2X + answer2Layout.width && touchY > answer2Y - answer2Layout.height && touchY < answer2Y) {
                if (MyGdxGame.LEVEL == 1){
                    MyGdxGame.SCORE += 1;
                }
                this.dispose();
                game.batch.end();
                game.setScreen(new MainGameScreen(game));
                questionNumber += 1;
                answer1 += 2;
                answer2 += 2;
                MyGdxGame.LEVEL += 1;
                System.out.println(MyGdxGame.SCORE);
                System.out.println(MyGdxGame.LEVEL);
                return;
            }

            if (touchX > zadneX && touchX < zadneX + zadneLayout.width && touchY > zadneY - zadneLayout.height && touchY < zadneY) {
                if (MyGdxGame.LEVEL == 3){
                    MyGdxGame.SCORE += 1;
                }
                this.dispose();
                game.batch.end();
                game.setScreen(new MainGameScreen(game));
                questionNumber += 1;
                answer1 += 2;
                answer2 += 2;
                MyGdxGame.LEVEL += 1;
                System.out.println(MyGdxGame.SCORE);
                System.out.println(MyGdxGame.LEVEL);
                return;
            }
        }

        //Draw buttons
        questionFont.draw(game.batch, answer1Layout, answer1X, answer1Y);
        questionFont.draw(game.batch, answer2Layout, answer2X, answer2Y);
        questionFont.draw(game.batch, zadneLayout, zadneX, zadneY);

        game.batch.end();
    }

    @Override
    public void resize(int i, int i1) {

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

    }
}