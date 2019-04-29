package ru.adkazankov.moscow.config;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import ru.adkazankov.moscow.control.CompareController;
import ru.adkazankov.moscow.control.HouseController;
import ru.adkazankov.moscow.control.StartController;
import java.io.IOException;
import java.io.InputStream;

@Configuration
public class ControllerConfiguration {

    @Bean(name = "compareFrame")
    public View getCompareFrame() throws IOException {
        return loadView("compareFrame.fxml");
    }
    @Bean
    public CompareController getCompareController() throws IOException {
        return (CompareController) getCompareFrame().getController();
    }


    @Bean(name = "houseFrame")
    public View getHouseFrame() throws IOException {
        return loadView("houseFrame.fxml");
    }
    @Bean
    public HouseController getHouseController() throws IOException {
        return (HouseController) getHouseFrame().getController();
    }




    @Bean(name = "startFrame")
    public View getStartFrame() throws IOException {
        return loadView("startFrame.fxml");
    }

    /**
     * Именно благодаря этому методу мы добавили контроллер в контекст спринга,
     * и заставили его произвести все необходимые инъекции.
     */
    @Bean
    public StartController getStartController() throws IOException {
        return (StartController) getStartFrame().getController();
    }

    /**
     * Самый обыкновенный способ использовать FXML загрузчик.
     * Как раз-таки на этом этапе будет создан объект-контроллер,
     * произведены все FXML инъекции и вызван метод инициализации контроллера.
     */
    protected View loadView(String url) throws IOException {
        InputStream fxmlStream = null;
        try {
            fxmlStream = getClass().getClassLoader().getResourceAsStream(url);
            FXMLLoader loader = new FXMLLoader();
            Node node = loader.load(fxmlStream);
            return new View(loader.getRoot(), loader.getController(), node);
        } finally {
            if (fxmlStream != null) {
                fxmlStream.close();
            }
        }
    }

    /**
     * Класс - оболочка: контроллер мы обязаны указать в качестве бина,
     * а view - представление, нам предстоит использовать в точке входа.
     */
    public class View {
        private Parent parent;
        private Object controller;
        private Node node;

        public View(Parent parent, Object controller, Node node) {
            this.parent = parent;
            this.controller = controller;
            this.node = node;
        }

        public Parent getParent() {
            return parent;
        }

        public void setParent(Parent parent) {
            this.parent = parent;
        }

        public Object getController() {
            return controller;
        }

        public void setController(Object controller) {
            this.controller = controller;
        }

        public Node getNode() {
            return node;
        }

        public void setNode(Node node) {
            this.node = node;
        }
    }

}
