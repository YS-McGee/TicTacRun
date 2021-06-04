package view;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.SubScene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.util.Duration;
import sample.MatchPairHandler;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class WaitingRoomManager {

    private AnchorPane gamePane;
    private Scene gameScene;
    private Scene animationScene;
    private Stage gameStage, menuStage;

    private StackPane rootPane;

    private static final int GAME_WIDTH = 1024;
    private static final int GAME_HEIGHT = 640;

    private final static String BACKGROUND_IMAGE = "view/resources/waiting_room_background.png";

    private final static String BD0 = "view/resources/animation/big_demon_0.png";
    private final static String BD1 = "view/resources/animation/big_demon_1.png";
    private final static String BD2 = "view/resources/animation/big_demon_2.png";
    private final static String BD3 = "view/resources/animation/big_demon_3.png";
    private final static String BD4 = "view/resources/animation/big_demon_4.png";
    private final static String BD5 = "view/resources/animation/big_demon_5.png";
    private final static String BD6 = "view/resources/animation/big_demon_6.png";
    private final static String BD7 = "view/resources/animation/big_demon_7.png";

    final static Image BIG_DEMON_0 = new Image(BD0);
    final static Image BIG_DEMON_1 = new Image(BD1);
    final static Image BIG_DEMON_2 = new Image(BD2);
    final static Image BIG_DEMON_3 = new Image(BD3);
    final static Image BIG_DEMON_4 = new Image(BD4);
    final static Image BIG_DEMON_5 = new Image(BD5);
    final static Image BIG_DEMON_6 = new Image(BD6);
    final static Image BIG_DEMON_7 = new Image(BD7);

    final ImageView big_demon0 = new ImageView(BIG_DEMON_0);
    final ImageView big_demon1 = new ImageView(BIG_DEMON_1);
    final ImageView big_demon2 = new ImageView(BIG_DEMON_2);
    final ImageView big_demon3 = new ImageView(BIG_DEMON_3);
    final ImageView big_demon4 = new ImageView(BIG_DEMON_4);
    final ImageView big_demon5 = new ImageView(BIG_DEMON_5);
    final ImageView big_demon6 = new ImageView(BIG_DEMON_6);
    final ImageView big_demon7 = new ImageView(BIG_DEMON_7);

    private Group group;

    ArrayList<Integer> clientList = new ArrayList<Integer>(Collections.nCopies(4, 0));

    static BufferedReader in;
    static PrintWriter out;
    ObjectOutputStream oos;
    ObjectInputStream  ois;

    private String name = null;

    public WaitingRoomManager() {

        group = new Group(big_demon0);
        group.setTranslateX(220);
        group.setTranslateY(365);

        Timeline t = new Timeline();
        t.setCycleCount(Timeline.INDEFINITE);
        t.getKeyFrames().add(new KeyFrame(Duration.millis(200), (ActionEvent event) -> {group.getChildren().setAll(big_demon1);}));
        t.getKeyFrames().add(new KeyFrame(Duration.millis(300), (ActionEvent event) -> {group.getChildren().setAll(big_demon2);}));
        t.getKeyFrames().add(new KeyFrame(Duration.millis(400), (ActionEvent event) -> {group.getChildren().setAll(big_demon3);}));
        t.getKeyFrames().add(new KeyFrame(Duration.millis(500), (ActionEvent event) -> {group.getChildren().setAll(big_demon4);}));
        t.getKeyFrames().add(new KeyFrame(Duration.millis(600), (ActionEvent event) -> {group.getChildren().setAll(big_demon5);}));
        t.getKeyFrames().add(new KeyFrame(Duration.millis(700), (ActionEvent event) -> {group.getChildren().setAll(big_demon6);}));
        t.getKeyFrames().add(new KeyFrame(Duration.millis(800), (ActionEvent event) -> {group.getChildren().setAll(big_demon7);}));
        t.play();

        initializeStage();

//        createKeyListener();
    }

    public void waitForGameBegin(String s, BufferedReader in, PrintWriter out) {
        name = s;

        MatchPairHandler matchPairHandler = new MatchPairHandler(in, out, name, gameStage, gamePane);
        matchPairHandler.start();

    }
    private void createPlayerAnimation() {

    }
    private void createBackground() {
        Image backgroundImage = new Image(BACKGROUND_IMAGE, 1024, 640, true, true);
        BackgroundImage background = new BackgroundImage(backgroundImage,
                                                        BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT,
                                                        BackgroundPosition.CENTER, null);
        gamePane.setBackground(new Background(background));
    }
    public void createWaitingRoom(Stage menuStage) {
        this.menuStage = menuStage;
        this.menuStage.hide();
        createBackground();
        gamePane.getChildren().add(group);
        gameStage.show();

    }
    private void initializeStage() {
        gamePane = new AnchorPane();
        gameScene = new Scene(gamePane, GAME_WIDTH, GAME_HEIGHT);
        gameStage = new Stage();
        gameStage.setScene(gameScene);
    }
}
