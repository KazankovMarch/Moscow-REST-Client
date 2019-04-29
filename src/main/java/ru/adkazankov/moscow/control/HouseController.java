package ru.adkazankov.moscow.control;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import org.springframework.beans.factory.annotation.Autowired;
import ru.adkazankov.moscow.dao.CommentDao;
import ru.adkazankov.moscow.dao.HouseDao;
import ru.adkazankov.moscow.domain.Comment;
import ru.adkazankov.moscow.domain.House;
import ru.adkazankov.moscow.domain.WaterTest;

import java.util.List;

public class HouseController {

    @Autowired
    private CommentDao commentDao;

    private House house;

    @FXML
    private Label addressLabel;

    @FXML
    private Label areaLabel;

    @FXML
    private Label yearLabel;

    @FXML
    private ListView<WaterTest> waterTestsList;

    @FXML
    private ListView<Comment> commentsList;

    @FXML
    private Spinner<Integer> gradeSpinner;

    @FXML
    private TextField yourCommentArea;

    @FXML
    void initialize() {
        gradeSpinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(1,5,3));
    }

    @FXML
    void sendComment(ActionEvent event) {
        String text = yourCommentArea.getText();
        if(text!=null && text.length()>1){
            Comment comment = Comment.builder()
                    .body(text)
                    .grade(gradeSpinner.getValue())
                    .house(house)
                    .build();
            commentDao.post(comment);
            house.getComments().add(comment);
            commentsList.getItems().add(comment);
            commentsList.refresh();
        }
    }

    public void setHouse(House house) {
        this.house = house;
        addressLabel.setText(house.getAddress());
        areaLabel.setText(house.getArea().getName());
        yearLabel.setText(String.valueOf(house.getYear()));
        waterTestsList.getItems().setAll(house.getArea().getTests());
        commentsList.getItems().setAll(house.getComments());
    }

}
