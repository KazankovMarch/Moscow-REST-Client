package ru.adkazankov.moscow.util;

import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import ru.adkazankov.moscow.config.ControllerConfiguration;
import ru.adkazankov.moscow.control.CompareController;
import ru.adkazankov.moscow.control.HouseController;
import ru.adkazankov.moscow.domain.House;

import java.util.List;

@Component
public class InterfaceUtils {

    @Autowired
    @Qualifier(value = "houseFrame")
    private ControllerConfiguration.View houseView;

    @Autowired
    @Qualifier(value = "compareFrame")
    private ControllerConfiguration.View compareView;


    public static void showError(String main, String context){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Ошибка");
        alert.setHeaderText(main);
        alert.setContentText(context);
        alert.show();
    }

    public static boolean showConfirm(String main, String context){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Подтверждение действия");
        alert.setHeaderText(main);
        alert.setContentText(context);
        return alert.showAndWait().get().equals(ButtonType.OK);
    }

    public static boolean showInfo(String main, String context){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Информация");
        alert.setHeaderText(main);
        alert.setContentText(context);
        alert.showAndWait();
        return !alert.getResult().getButtonData().isCancelButton();
    }


    public void openHouseFrame(House house){
        Stage stage = new Stage();
        stage.setTitle(house.getAddress());
        Scene scene = houseView.getParent().getScene();
        if(scene==null) {
            stage.setScene(new Scene(houseView.getParent()));
        }else {
            stage.setScene(scene);
        }
        stage.setResizable(true);
        stage.centerOnScreen();
        stage.initModality(Modality.APPLICATION_MODAL);
        HouseController controller = (HouseController)houseView.getController();
        controller.setHouse(house);
        stage.showAndWait();
    }

    public void openCompareFrame(List<House> houses){
        Stage stage = new Stage();
        stage.setTitle("Сравнение");
        Scene scene = compareView.getParent().getScene();
        if(scene==null) {
            stage.setScene(new Scene(compareView.getParent()));
        }else {
            stage.setScene(scene);
        }
        stage.setResizable(true);
        stage.centerOnScreen();
        stage.initModality(Modality.APPLICATION_MODAL);
        CompareController controller = (CompareController)compareView.getController();
        controller.setHouses(houses);
        stage.showAndWait();
    }

}
