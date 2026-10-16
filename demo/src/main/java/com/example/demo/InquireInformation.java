package com.example.demo;
import com.example.OBJECT.Health;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.MapValueFactory;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.lang.reflect.Type;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class InquireInformation {
    @FXML
    private TableView<Map> dataTableView;
    @FXML
    private TableColumn<Map, Void> change;

    @FXML
    private TableColumn<Map, String> dateColumn;

    @FXML
    private TableColumn<Map, String> heightColumn;


    @FXML
    private Button search;

    @FXML
    private TableColumn<Map, String> sleepColumn;

    @FXML
    private TableColumn<Map, String> stepColumn;

    @FXML
    private TableColumn<Map, String> weightColumn;

    @FXML
    private DatePicker startDatePicker;
    @FXML
    private DatePicker endDatePicker;

    private ArrayList<Map> healthyList = new ArrayList();

    private final ObservableList<Map> observableList= FXCollections.observableArrayList();

    private void setTableViewData() {
        observableList.clear();
        for (Map map : healthyList) {
            observableList.addAll(FXCollections.observableArrayList(map));
        }
        dataTableView.setItems(observableList);
    }
    public void initialize()
    {
        dateColumn.setCellValueFactory(new MapValueFactory<>("date"));
        heightColumn.setCellValueFactory(new MapValueFactory<>("height"));
        weightColumn.setCellValueFactory(new MapValueFactory<>("weight"));
        sleepColumn.setCellValueFactory(new MapValueFactory<>("sleep"));
        stepColumn.setCellValueFactory(new MapValueFactory<>("step"));
        change.setCellFactory(col -> new TableCell<Map, Void>() {
            private final HBox hbox = new HBox();
            private final Button btn = new Button("修改");
            private final Button button=new Button("删除");
            {
                btn.setOnAction(event -> {
                    Map rowData = getTableView().getItems().get(getIndex());
                    try {
                        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("ChangeInformation.fxml"));
                        Stage stage = new Stage();
                        stage.setTitle("修改信息");
                        stage.setScene(new Scene(fxmlLoader.load()));
                        stage.show();
                        ChangeInformation changeInformation = fxmlLoader.getController();
                        changeInformation.init(rowData.get("date").toString());
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                });
                button.setOnAction(event -> {
                    Map rowData=getTableView().getItems().get(getIndex());
                    healthyList.remove(rowData);
                    try {
                        Main.connect.sendMessage("deleteRequest");
                        Main.connect.sendMessage(rowData.get("date").toString());
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }

                    setTableViewData();
                });
                hbox.getChildren().addAll(btn, button);
            }
            protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setGraphic(null);
                } else {
                    setGraphic(hbox);
                }
            }
        });
        dataTableView.setItems(observableList);
        searchRequest("", "", "","","","");
        System.out.println("this is init");
    }
    public void searchRequest(String startYear, String startMonth, String startDate,String endYear, String endMonth, String endDate) {
        try {
            Main.connect.sendMessage("searchRequest");
            Main.connect.sendMessage(startYear);
            Main.connect.sendMessage(startMonth);
            Main.connect.sendMessage(startDate);
            Main.connect.sendMessage(endYear);
            Main.connect.sendMessage(endMonth);
            Main.connect.sendMessage(endDate);
            ArrayList<Health> result = (ArrayList<Health>) Main.connect.getObject();
            healthyList.clear();
            for (Health health : result) {
                healthyList.add(Map.of("date", health.Date, "height", health.height, "weight", health.weight, "sleep", health.sleep, "step", health.step));
            }
            setTableViewData();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    @FXML
    void OnSearch() {
        LocalDate startDate = startDatePicker.getValue();
        LocalDate endDate = endDatePicker.getValue();
        if (startDate != null && endDate != null) {
            String startYear = String.valueOf(startDate.getYear());
            String startMonth = String.format("%02d", startDate.getMonthValue());
            startMonth = startMonth.replace("0", "");
            String startDateStr = String.format("%02d", startDate.getDayOfMonth());
            startDateStr = startDateStr.replace("0", "");

            String endYear = String.valueOf(endDate.getYear());
            String endMonth = String.format("%02d", endDate.getMonthValue());
            endMonth = endMonth.replace("0", "");
            String endDateStr = String.format("%02d", endDate.getDayOfMonth());
            endDateStr = endDateStr.replace("0", "");
            System.out.println(startYear + " " + startMonth + " " + startDateStr + " " + endYear + " " + endMonth + " " + endDateStr);
            searchRequest(startYear, startMonth, startDateStr, endYear, endMonth, endDateStr);
        } else {
            System.out.println("请选择起始和结束日期。");
        }
    }
}
