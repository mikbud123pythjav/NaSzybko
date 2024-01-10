package com.mygdx.game.screens;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.entities.*;
import com.mygdx.game.tools.Camera;
import com.mygdx.game.tools.CollisionReaction;
import com.mygdx.game.tools.GameCamera;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import java.util.ArrayList;
import java.util.Random;


public class MainGameScreen implements Screen {
    public static final int MONKEY_WIDTH_PIXEL = 17;
    public static final int MONKEY_HEIGHT_PIXEL = 32;
    public static final float MONKEY_ANIMATION_SPEED = 0.5f;
    public static final float MIN_BANANA_SPAWN_TIME = 0.05f;
    public static final float MAX_BANANA_SPAWN_TIME = 0.1f;
    public static float TIMER_SWITCH_TIME = 0.1f;



    Camera camera;
    private Texture backgroundTexture;

    float stateTime;
    float x;
    float y;
    Stage stage;
    Texture player;
    Texture banan;
    float Speed = 150.0f;
    float timeSeconds = 0f;
    private float period = 1f;
    public MainMenuScreen mainMenuScreen;
    Texture blank;
    public boolean life = true;

    public MyGdxGame game;
    float bananaSpawnTime;
    float cactusSpawnTime;

    CollisionReaction playerRect;
    float health;
    BitmapFont scoreFont;
    int roll;
    float rollTimer;
    Animation[] rolls;

    Random random;

    ArrayList<Banana> bananaList;
    ArrayList<Cactus> cactusList;
    ArrayList<Chart> chartList;
    ArrayList<Explosion> explosions;
    ArrayList<Cactus> cactuses;
    private float speed = 200; // Prędkość poruszania się
    public Texture cactusTexture;
    Texture bananTexture;
    Texture malpaTexture;
    Malpa malpa;
    Cactus cactusOne;
    Cactus cactusTwo;
    Cactus cactusThree;
    Cactus cactusFour;
    Cactus cactusFive;
    Cactus cactusSix;
    Cactus cactusSeven;
    Cactus cactusEight;
    Cactus cactusNine;
    Cactus cactusTen;
    Cactus cactusEleven;
    Cactus cactusTwelve;




    private boolean isJumping;
    public GameCamera cam;


    public MainGameScreen (MyGdxGame game){
        this.game = game;

        cactusList = new ArrayList<Cactus>();
        bananaList = new ArrayList<Banana>();
        cactusList = new ArrayList<Cactus>();
        chartList = new ArrayList<Chart>();
        explosions = new ArrayList<Explosion>();
        scoreFont = new BitmapFont(Gdx.files.internal("fonts/score.fnt"));

        bananTexture = new Texture("Banan.jpg");
//        cactuses = new ArrayList<>();
//
//        for(int i = 0; i < 20; i++){
//            Cactus cactus = new Cactus(1000, 10);
//            cactuses.add(cactus);
//        }

        health = 1;
        cactusTexture = new Texture("kaktus.png");
        Cactus.setTexture(cactusTexture);
        malpaTexture = new Texture("malpka_preview_rev_1.png");
        Malpa.setTexture(malpaTexture);
        malpa = new Malpa(50,10);

        playerRect = new CollisionReaction(malpa.getX(),malpa.getY(),MONKEY_WIDTH_PIXEL, MONKEY_HEIGHT_PIXEL);

        blank = new Texture("blank.png");

        camera = new Camera();

        Random random = new Random();
        bananaSpawnTime = random.nextFloat() * (MAX_BANANA_SPAWN_TIME - MIN_BANANA_SPAWN_TIME) + MIN_BANANA_SPAWN_TIME;


        roll = 2;
        rollTimer = 0;
        rolls = new Animation[5];

        TextureRegion[][] rollSpriteSheet = TextureRegion.split(new Texture("malpka_preview_rev_1.png"), MONKEY_WIDTH_PIXEL, MONKEY_HEIGHT_PIXEL);

        backgroundTexture = new Texture("tłoGry.jpg");

        try {
            rolls[0] = new Animation(MONKEY_ANIMATION_SPEED, rollSpriteSheet[2]);
            rolls[1] = new Animation(MONKEY_ANIMATION_SPEED, rollSpriteSheet[1]);
            rolls[2] = new Animation(MONKEY_ANIMATION_SPEED, rollSpriteSheet[0]);
            rolls[3] = new Animation(MONKEY_ANIMATION_SPEED, rollSpriteSheet[3]);
            rolls[4] = new Animation(MONKEY_ANIMATION_SPEED, rollSpriteSheet[4]);
        }catch (ArrayIndexOutOfBoundsException e){
            e.getStackTrace();
        }

        game.scrollingBackground.setSpeedFixed(false);


        this.cactusOne = new Cactus(1000, 10);

         }


    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {

        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        game.batch.begin();

        game.scrollingBackground.updateAndRender(delta, game.batch);

        GlyphLayout scoreLayout = new GlyphLayout(scoreFont, "" + MyGdxGame.SCORE);
        scoreFont.draw(game.batch, scoreLayout, MyGdxGame.WIDTH / 2 - scoreLayout.width / 2, MyGdxGame.HEIGHT - scoreLayout.height - 10);

        game.batch.draw(backgroundTexture, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        game.batch.draw(Malpa.getTexture(), malpa.getX(), malpa.getY());
        playerRect.move(malpa.getX(), malpa.getY());

        cactusOne.update(delta);
        Texture przezroczysty = new Texture("kaktus.png");
        Cactus.setTexture(przezroczysty);
        game.batch.draw(Cactus.getTexture(), cactusOne.getX(), cactusOne.getY(), 200, 150);
        if(cactusOne.getCollisionRect().collidesWith(playerRect)){
            health -= 0.1;
        }

        cactusTwo.update(delta);
        game.batch.draw(Cactus.getTexture(), cactusTwo.getX(), cactusTwo.getY(), 200, 150);
        if(cactusTwo.getCollisionRect().collidesWith(playerRect)){
            health -= 0.1;
        }

        cactusThree.update(delta);
        game.batch.draw(Cactus.getTexture(), cactusThree.getX(), cactusThree.getY(), 200, 150);
        if(cactusThree.getCollisionRect().collidesWith(playerRect)){
            health -= 0.1;
        }

        cactusFour.update(delta);
        game.batch.draw(Cactus.getTexture(), cactusFour.getX(), cactusFour.getY(), 200, 150);
        if(cactusFour.getCollisionRect().collidesWith(playerRect)){
            health -= 0.1;
        }

        cactusFive.update(delta);
        game.batch.draw(Cactus.getTexture(), cactusFive.getX(), cactusFive.getY(), 200, 150);
        if(cactusFive.getCollisionRect().collidesWith(playerRect)){
            health -= 0.1;
        }

        cactusSix.update(delta);
        game.batch.draw(Cactus.getTexture(), cactusSix.getX(), cactusSix.getY(), 200, 150);
        if(cactusSix.getCollisionRect().collidesWith(playerRect)){
            health -= 0.1;
        }

        cactusSeven.update(delta);
        game.batch.draw(Cactus.getTexture(), cactusSeven.getX(), cactusSeven.getY(), 200, 150);
        if(cactusSeven.getCollisionRect().collidesWith(playerRect)) {
            health -= 0.1;
        }

        cactusEight.update(delta);
        game.batch.draw(Cactus.getTexture(), cactusEight.getX(), cactusEight.getY(), 200, 150);
        if(cactusEight.getCollisionRect().collidesWith(playerRect)) {
            health -= 0.1;
        }

        cactusNine.update(delta);
        game.batch.draw(Cactus.getTexture(), cactusNine.getX(), cactusNine.getY(), 200, 150);
        if(cactusNine.getCollisionRect().collidesWith(playerRect)) {
            health -= 0.1;
        }

        cactusTen.update(delta);
        game.batch.draw(Cactus.getTexture(), cactusTen.getX(), cactusTen.getY(), 200, 150);
        if(cactusTen.getCollisionRect().collidesWith(playerRect)) {
            health -= 0.1;
        }

        cactusEleven.update(delta);
        game.batch.draw(Cactus.getTexture(), cactusEleven.getX(), cactusEleven.getY(), 200, 150);
        if(cactusEleven.getCollisionRect().collidesWith(playerRect)) {
            health -= 0.1;
        }

        cactusTwelve.update(delta);
        game.batch.draw(Cactus.getTexture(), cactusTwelve.getX(), cactusTwelve.getY(), 200, 150);
        if(cactusTwelve.getCollisionRect().collidesWith(playerRect)) {
            health -= 0.1;
        }




        MyGdxGame.TIME += Gdx.graphics.getDeltaTime();
        if(MyGdxGame.TIME > TIMER_SWITCH_TIME){
            MyGdxGame.SCORE += 10;
            MyGdxGame.TIME = 0;
        }

//        if(MyGdxGame.SCORE == 100 || MyGdxGame.SCORE == 300 || MyGdxGame.SCORE == 800){
//            game.setScreen(new QuestionScreen(game));
//        }


        //Draw health
        if (health > 0.6f)
            game.batch.setColor(Color.GREEN);
        else if (health > 0.2f)
            game.batch.setColor(Color.ORANGE);
        else
            game.batch.setColor(Color.RED);

        if(health <= 0){
            this.dispose();
            game.batch.end();
            game.setScreen(new GameOverScreen(game));
            MyGdxGame.SCORE = 0;
            return;
        }

        if(handleInput()){
            jump();
        }
        else {
            malpa.setY(10);
        }


        game.batch.draw(blank, 0, 0, MyGdxGame.WIDTH * health, 5);
        game.batch.setColor(Color.WHITE);

        game.batch.end();
    }

    private boolean handleInput() {
        if ((Gdx.input.isKeyPressed(Keys.SPACE) || Gdx.input.isKeyPressed(Keys.W))) {
            return true;
        }
        return false;
    }


    private void jump() {
        malpa.setY(120);
    }
    private void reJump() {
        malpa.setY(120);
    }


    public void cactusMove() {


    }
    private boolean isRight () {
        return Gdx.input.isKeyPressed(Keys.RIGHT) || (Gdx.input.isTouched() && game.cam.getInputInGameWorld().x >= MyGdxGame.WIDTH / 2);
    }

    private boolean isLeft () {
        return Gdx.input.isKeyPressed(Keys.LEFT) || (Gdx.input.isTouched() && game.cam.getInputInGameWorld().x < MyGdxGame.WIDTH / 2);
    }

    private boolean isJustRight () {
        return Gdx.input.isKeyJustPressed(Keys.RIGHT) || (Gdx.input.justTouched() && game.cam.getInputInGameWorld().x >= MyGdxGame.WIDTH / 2);
    }

    private boolean isJustLeft () {
        return Gdx.input.isKeyJustPressed(Keys.LEFT) || (Gdx.input.justTouched() && game.cam.getInputInGameWorld().x < MyGdxGame.WIDTH / 2);
    }

    @Override
    public void resize(int width, int height) {

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
    public void handleEventBanana() {

    }
    public void catchBananaEvent(){

    }

    public void handleEventQuestion() {

    }
    public void handleEvenetAnswerQuestion(){

    }
}
