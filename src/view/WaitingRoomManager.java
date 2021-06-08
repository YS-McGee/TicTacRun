package view;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.scene.Group;
import javafx.scene.PerspectiveCamera;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class WaitingRoomManager extends Thread{

    private Thread thread;

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

    private final static String BZ0 = "view/resources/animation/big_zombie_0.png";
    private final static String BZ1 = "view/resources/animation/big_zombie_1.png";
    private final static String BZ2 = "view/resources/animation/big_zombie_2.png";
    private final static String BZ3 = "view/resources/animation/big_zombie_3.png";
    private final static String BZ4 = "view/resources/animation/big_zombie_4.png";
    private final static String BZ5 = "view/resources/animation/big_zombie_5.png";
    private final static String BZ6 = "view/resources/animation/big_zombie_6.png";
    private final static String BZ7 = "view/resources/animation/big_zombie_7.png";

    final static Image BIG_ZOMBIE_0 = new Image(BZ0);
    final static Image BIG_ZOMBIE_1 = new Image(BZ1);
    final static Image BIG_ZOMBIE_2 = new Image(BZ2);
    final static Image BIG_ZOMBIE_3 = new Image(BZ3);
    final static Image BIG_ZOMBIE_4 = new Image(BZ4);
    final static Image BIG_ZOMBIE_5 = new Image(BZ5);
    final static Image BIG_ZOMBIE_6 = new Image(BZ6);
    final static Image BIG_ZOMBIE_7 = new Image(BZ7);

    final ImageView big_zombie0 = new ImageView(BIG_ZOMBIE_0);
    final ImageView big_zombie1 = new ImageView(BIG_ZOMBIE_1);
    final ImageView big_zombie2 = new ImageView(BIG_ZOMBIE_2);
    final ImageView big_zombie3 = new ImageView(BIG_ZOMBIE_3);
    final ImageView big_zombie4 = new ImageView(BIG_ZOMBIE_4);
    final ImageView big_zombie5 = new ImageView(BIG_ZOMBIE_5);
    final ImageView big_zombie6 = new ImageView(BIG_ZOMBIE_6);
    final ImageView big_zombie7 = new ImageView(BIG_ZOMBIE_7);

    private final static String OGRE0 = "view/resources/animation/ogre_0.png";
    private final static String OGRE1 = "view/resources/animation/ogre_1.png";
    private final static String OGRE2 = "view/resources/animation/ogre_2.png";
    private final static String OGRE3 = "view/resources/animation/ogre_3.png";
    private final static String OGRE4 = "view/resources/animation/ogre_4.png";
    private final static String OGRE5 = "view/resources/animation/ogre_5.png";
    private final static String OGRE6 = "view/resources/animation/ogre_6.png";
    private final static String OGRE7 = "view/resources/animation/ogre_7.png";

    final static Image OGRE_0 = new Image(OGRE0);
    final static Image OGRE_1 = new Image(OGRE1);
    final static Image OGRE_2 = new Image(OGRE2);
    final static Image OGRE_3 = new Image(OGRE3);
    final static Image OGRE_4 = new Image(OGRE4);
    final static Image OGRE_5 = new Image(OGRE5);
    final static Image OGRE_6 = new Image(OGRE6);
    final static Image OGRE_7 = new Image(OGRE7);

    final ImageView ogre0 = new ImageView(OGRE_0);
    final ImageView ogre1 = new ImageView(OGRE_1);
    final ImageView ogre2 = new ImageView(OGRE_2);
    final ImageView ogre3 = new ImageView(OGRE_3);
    final ImageView ogre4 = new ImageView(OGRE_4);
    final ImageView ogre5 = new ImageView(OGRE_5);
    final ImageView ogre6 = new ImageView(OGRE_6);
    final ImageView ogre7 = new ImageView(OGRE_7);

    private final static String LIZARD0 = "view/resources/animation/lizard_0.png";
    private final static String LIZARD1 = "view/resources/animation/lizard_1.png";
    private final static String LIZARD2 = "view/resources/animation/lizard_2.png";
    private final static String LIZARD3 = "view/resources/animation/lizard_3.png";
    private final static String LIZARD4 = "view/resources/animation/lizard_4.png";
    private final static String LIZARD5 = "view/resources/animation/lizard_5.png";
    private final static String LIZARD6 = "view/resources/animation/lizard_6.png";
    private final static String LIZARD7 = "view/resources/animation/lizard_7.png";

    final static Image LIZARD_0 = new Image(LIZARD0);
    final static Image LIZARD_1 = new Image(LIZARD1);
    final static Image LIZARD_2 = new Image(LIZARD2);
    final static Image LIZARD_3 = new Image(LIZARD3);
    final static Image LIZARD_4 = new Image(LIZARD4);
    final static Image LIZARD_5 = new Image(LIZARD5);
    final static Image LIZARD_6 = new Image(LIZARD6);
    final static Image LIZARD_7 = new Image(LIZARD7);

    final ImageView lizard0 = new ImageView(LIZARD_0);
    final ImageView lizard1 = new ImageView(LIZARD_1);
    final ImageView lizard2 = new ImageView(LIZARD_2);
    final ImageView lizard3 = new ImageView(LIZARD_3);
    final ImageView lizard4 = new ImageView(LIZARD_4);
    final ImageView lizard5 = new ImageView(LIZARD_5);
    final ImageView lizard6 = new ImageView(LIZARD_6);
    final ImageView lizard7 = new ImageView(LIZARD_7);

    private Group group, bigZombieGroup, ogreGroup, lizardGroup;
    private boolean animation2 = false, animation3 = false, animation4 = false;

    ArrayList<Integer> clientList = new ArrayList<Integer>(Collections.nCopies(4, 0));

    Socket socket;
    static BufferedReader in;
    static PrintWriter out;
    ObjectOutputStream oos;
    ObjectInputStream  ois;

    private String name = null;

    static ArrayList isReady = new ArrayList();

    WaitingRoomManager waitingRoomManager;

    public WaitingRoomManager(BufferedReader in, PrintWriter out) {


        try {
            socket = new Socket("localhost", 8888);
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.in = in;
        this.out = out;
//        try {
//            oos = new ObjectOutputStream(socket.getOutputStream());
//            ois = new ObjectInputStream(socket.getInputStream());
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

        //Placement 1
        group = new Group(big_demon0);
        group.setTranslateX(220);
        group.setTranslateY(364);
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
        // Placement 2
        bigZombieGroup = new Group(big_zombie0);
        bigZombieGroup.setTranslateX(270);
        bigZombieGroup.setTranslateY(136);
        Timeline zT = new Timeline();
        zT.setCycleCount(Timeline.INDEFINITE);
        zT.getKeyFrames().add(new KeyFrame(Duration.millis(200), (ActionEvent event) -> {bigZombieGroup.getChildren().setAll(big_zombie1);}));
        zT.getKeyFrames().add(new KeyFrame(Duration.millis(300), (ActionEvent event) -> {bigZombieGroup.getChildren().setAll(big_zombie2);}));
        zT.getKeyFrames().add(new KeyFrame(Duration.millis(400), (ActionEvent event) -> {bigZombieGroup.getChildren().setAll(big_zombie3);}));
        zT.getKeyFrames().add(new KeyFrame(Duration.millis(500), (ActionEvent event) -> {bigZombieGroup.getChildren().setAll(big_zombie4);}));
        zT.getKeyFrames().add(new KeyFrame(Duration.millis(600), (ActionEvent event) -> {bigZombieGroup.getChildren().setAll(big_zombie5);}));
        zT.getKeyFrames().add(new KeyFrame(Duration.millis(700), (ActionEvent event) -> {bigZombieGroup.getChildren().setAll(big_zombie6);}));
        zT.getKeyFrames().add(new KeyFrame(Duration.millis(800), (ActionEvent event) -> {bigZombieGroup.getChildren().setAll(big_zombie7);}));
        zT.play();
        //Placement3
        ogreGroup = new Group(ogre0);
        ogreGroup.setTranslateX(270);
        ogreGroup.setTranslateY(533);
        Timeline oT = new Timeline();
        oT.setCycleCount(Timeline.INDEFINITE);
        oT.getKeyFrames().add(new KeyFrame(Duration.millis(200), (ActionEvent event) -> {ogreGroup.getChildren().setAll(ogre1);}));
        oT.getKeyFrames().add(new KeyFrame(Duration.millis(300), (ActionEvent event) -> {ogreGroup.getChildren().setAll(ogre2);}));
        oT.getKeyFrames().add(new KeyFrame(Duration.millis(400), (ActionEvent event) -> {ogreGroup.getChildren().setAll(ogre3);}));
        oT.getKeyFrames().add(new KeyFrame(Duration.millis(500), (ActionEvent event) -> {ogreGroup.getChildren().setAll(ogre4);}));
        oT.getKeyFrames().add(new KeyFrame(Duration.millis(600), (ActionEvent event) -> {ogreGroup.getChildren().setAll(ogre5);}));
        oT.getKeyFrames().add(new KeyFrame(Duration.millis(700), (ActionEvent event) -> {ogreGroup.getChildren().setAll(ogre6);}));
        oT.getKeyFrames().add(new KeyFrame(Duration.millis(800), (ActionEvent event) -> {ogreGroup.getChildren().setAll(ogre7);}));
        oT.play();
        //Placement4
        lizardGroup = new Group(lizard0);
        lizardGroup.setTranslateX(600);
        lizardGroup.setTranslateY(533);
        Timeline lT = new Timeline();
        lT.setCycleCount(Timeline.INDEFINITE);
        lT.getKeyFrames().add(new KeyFrame(Duration.millis(200), (ActionEvent event) -> {lizardGroup.getChildren().setAll(lizard1);}));
        lT.getKeyFrames().add(new KeyFrame(Duration.millis(300), (ActionEvent event) -> {lizardGroup.getChildren().setAll(lizard2);}));
        lT.getKeyFrames().add(new KeyFrame(Duration.millis(400), (ActionEvent event) -> {lizardGroup.getChildren().setAll(lizard3);}));
        lT.getKeyFrames().add(new KeyFrame(Duration.millis(500), (ActionEvent event) -> {lizardGroup.getChildren().setAll(lizard4);}));
        lT.getKeyFrames().add(new KeyFrame(Duration.millis(600), (ActionEvent event) -> {lizardGroup.getChildren().setAll(lizard5);}));
        lT.getKeyFrames().add(new KeyFrame(Duration.millis(700), (ActionEvent event) -> {lizardGroup.getChildren().setAll(lizard6);}));
        lT.getKeyFrames().add(new KeyFrame(Duration.millis(800), (ActionEvent event) -> {lizardGroup.getChildren().setAll(lizard7);}));
        lT.play();

        initializeStage();

        thread = new Thread(this);
        thread.start();

//        createKeyListener();
    }

    @Override
    public void run() {
        out.println("READY");
        String s = null;
        while (true) {
            try {
                s = in.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }

            System.out.println(s);

            String finalS = s;
            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    if(finalS.equals("2")) {
                        createPlayerAnimation2();
                        animation2 = true;
                    }
                    if (finalS.equals("3")) {
                        if (animation2 == false)
                            createPlayerAnimation2();
                        if (animation3 == false)
                            createPlayerAnimation3();
                        animation2 = true;
                        animation3 = true;
                    }
                    if (finalS.equals("4")) {
                        if (animation2 == false)
                            createPlayerAnimation2();
                        if (animation3 == false)
                            createPlayerAnimation3();
                        if (animation4 == false)
                            createPlayerAnimation4();
                        animation2 = true;
                        animation3 = true;
                        animation4 = true;
                        // If Player num == 4, begin the match after 2 sec
//                        try {
//                            Thread.sleep(2000);
//                        } catch (InterruptedException e) {
//                            e.printStackTrace();
//                        }
                        // GameBegin();
                        //TicTacToeManager ticTacToeManager = new TicTacToeManager(in, out, gameStage);
                        //ticTacToeManager.createBackground(gameStage);
                    }
                }
            });
        }
    }
    public void GameBegin() {

//        TicTacToeManager ticTacToeManager = new TicTacToeManager(in, out);
//        ticTacToeManager.createBackground(gameStage);

    }

    private void createPlayerAnimation2() {

        gamePane.getChildren().add(bigZombieGroup);

        //TicTacToeManager ticTacToeManager = new TicTacToeManager(in, out, gameStage);
    }
    private void createPlayerAnimation3() {
        gamePane.getChildren().add(ogreGroup);
    }
    private void createPlayerAnimation4() {

        gamePane.getChildren().add(lizardGroup);

        //TicTacToeManager ticTacToeManager = new TicTacToeManager(in, out, gameStage);
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
