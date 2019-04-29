package ru.adkazankov.moscow.control;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import ru.adkazankov.moscow.domain.House;
import ru.adkazankov.moscow.domain.WaterTest;

import java.lang.reflect.Array;
import java.util.*;

public class CompareController {


    private List<House> houses;

    @FXML
    private GridPane grid;

    @FXML
    void initialize() {

    }

    public void setHouses(List<House> houses) {
        grid.getChildren().clear();
        grid.gridLinesVisibleProperty().setValue(true);
        this.houses = houses;
        List<List<String>> table = new ArrayList<>();
        List<String> firstList = new ArrayList<>();
        List<String> allTestNames = new ArrayList<>();
        firstList.add("Адрес");
        firstList.add("Район");
        firstList.add("Год постройки");
        table.add(firstList);
        for(House house : houses){
            List<WaterTest> nextTests = new ArrayList<>();
            nextTests.addAll(house.getArea().getTests());
            List<String> nextList = new ArrayList<>();
            nextList.add(house.getAddress());
            nextList.add(house.getArea().getName());
            nextList.add(String.valueOf(house.getYear()));
            for(String testName: allTestNames){
                String val = "-";
                Iterator<WaterTest> testIterator = nextTests.iterator();
                while (val.equals("-") && testIterator.hasNext()){
                    WaterTest nextTest = testIterator.next();
                    if(nextTest.getName().equals(testName)){
                        val = nextTest.getResVal();
                        nextTests.remove(nextTest);
                    }
                }
                nextList.add(val);
            }
            table.add(nextList);
            for(WaterTest waterTest : nextTests){
                allTestNames.add(waterTest.getName());
                for(int i = 1; i < table.size()-1; i++){
                    table.get(i).add("-");
                }
                table.get(table.size()-1).add(waterTest.getResVal());
            }
        }
        firstList.addAll(allTestNames);

        int i = 0;

        for(List<String> col : table){
            grid.addColumn(i, toNodeArray(col));
            i++;
        }
    }

    private Node[] toNodeArray(List<String> col) {
        System.out.println(col);
        Node[] nodes = new Node[col.size()];
        Iterator<String> iterator = col.iterator();
        for(int i = 0; i < col.size(); i++){
            nodes[i] = new Label(iterator.next());
        }
        System.out.println(Arrays.toString(nodes));
        return nodes;
    }
}
