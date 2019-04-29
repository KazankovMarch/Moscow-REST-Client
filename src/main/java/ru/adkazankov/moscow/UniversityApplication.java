package ru.adkazankov.moscow;

import javafx.scene.Scene;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Lazy;
import ru.adkazankov.moscow.config.ControllerConfiguration;

@Lazy
@SpringBootApplication
public class UniversityApplication extends AbstractJavaFxApplicationSupport {

    @Value("${ui.title:Главное окно}")//
    private String windowTitle;

    @Qualifier("startFrame")
    @Autowired
    private ControllerConfiguration.View view;

    @Override
    public void start(Stage stage) throws Exception {
        stage.setTitle(windowTitle);
        stage.setScene(new Scene(view.getParent()));
        stage.setResizable(true);
        stage.centerOnScreen();
        stage.show();
    }

    public static void main(String[] args) {
        launchApp(UniversityApplication.class, args);
    }

}

