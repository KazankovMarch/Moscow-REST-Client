package ru.adkazankov.moscow.control;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import org.springframework.beans.factory.annotation.Autowired;
import ru.adkazankov.moscow.dao.HouseDao;
import ru.adkazankov.moscow.domain.House;
import ru.adkazankov.moscow.util.InterfaceUtils;

import java.util.HashMap;
import java.util.Map;

public class StartController {

    @Autowired
    private HouseDao houseDao;
    @Autowired
    private InterfaceUtils interfaceUtils;

    private Map<String, String> houseProperties = new HashMap<>();

    @FXML
    private TextField areaTextField;

    @FXML
    private TextField streetTextField;

    @FXML
    private TextField houseTextField;

    @FXML
    private ListView<House> matchedHousesList;

    @FXML
    private ListView<House> compareList;


    @FXML
    void initialize() {

        houseTextField.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue,
                                String newValue) {
                if (!newValue.matches("\\d*")) {
                    houseTextField.setText(newValue.replaceAll("[^\\d]", ""));
                }
            }
        });

        areaTextField.textProperty().addListener((observable, oldValue, newValue) -> {
            houseProperties.put("area", newValue);
            search(oldValue, newValue);
        });
        streetTextField.textProperty().addListener((observable, oldValue, newValue) -> {
            houseProperties.put("street", newValue);
            search(oldValue, newValue);
        });
        houseTextField.textProperty().addListener((observable, oldValue, newValue) -> {
            houseProperties.put("building", newValue);
            search(oldValue, newValue);
        });

        compareList.setOnMouseClicked(mouseEvent -> showHouse(compareList.getSelectionModel().getSelectedItem(), mouseEvent));
        matchedHousesList.setOnMouseClicked(mouseEvent -> showHouse(matchedHousesList.getSelectionModel().getSelectedItem(), mouseEvent));
    }


    private void search(String oldValue, String newValue) {
        if(!oldValue.equals(newValue)) {
            System.out.println(houseProperties);
            matchedHousesList.getItems().setAll(houseDao.get10LikeThis(houseProperties));
            matchedHousesList.refresh();
        }
    }


    @FXML
    void addToCompareList(ActionEvent event) {
        House house = matchedHousesList.getSelectionModel().getSelectedItem();
        if(house!=null && !compareList.getItems().contains(house)){
            compareList.getItems().add(house);
            compareList.refresh();
        }
    }

    @FXML
    void removeFromCompareList(ActionEvent event) {
        House house = compareList.getSelectionModel().getSelectedItem();
        if(house!=null){
            compareList.getItems().remove(house);
            compareList.refresh();
        }
    }

    @FXML
    void clearCompare(ActionEvent event){
        compareList.getItems().clear();
        compareList.refresh();
    }

    @FXML
    void showCompare(ActionEvent event) {
        if(!compareList.getItems().isEmpty()){
            interfaceUtils.openCompareFrame(compareList.getItems());
        }
    }

    private void showHouse(House selectedItem, MouseEvent mouseEvent) {
        if(mouseEvent.getClickCount()>1)
            interfaceUtils.openHouseFrame(selectedItem);
    }

}
