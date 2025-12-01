package com.app.LureCo;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

import org.jboss.weld.environment.se.Weld;
import org.jboss.weld.environment.se.WeldContainer;

import org.apache.catalina.startup.Tomcat;

import java.io.File;

public class App extends Application {

    private static Tomcat tomcat;
    private static final int PORT = 8080;
    private static final String CONTEXT_PATH = "/LureCo";

    public static void main(String[] args) {
        // Set temporary WebView user data directory to avoid "already in use"
        System.setProperty("javafx.userDataDirectory",
                System.getProperty("user.home") + File.separator + ".LureCoWebView");

        // Start Weld container
        Weld weld = new Weld();
        WeldContainer container = weld.initialize();
        System.out.println("Weld container initialized: " + container);

        // Launch JavaFX
        launch(args);

        // Shutdown Weld when app exits
        weld.shutdown();
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        startEmbeddedTomcat();

        WebView webView = new WebView();
        WebEngine engine = webView.getEngine();

        // Wait for Tomcat to fully start before loading JSF page
        new Thread(() -> {
            while (!isTomcatRunning()) {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            Platform.runLater(() -> {
                String url = "http://localhost:" + PORT + CONTEXT_PATH + "/index.xhtml";
                System.out.println("Navigating to: " + url);
                engine.load(url);
            });
        }).start();

        BorderPane root = new BorderPane();
        root.setCenter(webView);
        Scene scene = new Scene(root, 512, 384);
        primaryStage.getIcons().add(new Image(getClass().getResourceAsStream("/LureCo.png")));
        primaryStage.setScene(scene);
        primaryStage.setTitle("LureCo");
        primaryStage.show();
    }

    private void startEmbeddedTomcat() throws Exception {
        if (tomcat != null) return; // already started

        tomcat = new Tomcat();
        tomcat.setPort(PORT);
        tomcat.getConnector(); // initialize connector

        String webappDirLocation = new File("src/main/webapp/").getAbsolutePath();
        tomcat.addWebapp(CONTEXT_PATH, webappDirLocation);

        new Thread(() -> {
            try {
                tomcat.start();
                System.out.println("Embedded Tomcat started on port " + PORT);
                tomcat.getServer().await();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();
    }

    private boolean isTomcatRunning() {
        try {
            return tomcat != null && tomcat.getServer().getState().isAvailable();
        } catch (Exception e) {
            return false;
        }
    }
}
