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
public class ManageStage {

    @FXML
    private TableView<Map> dataTableView;

    @FXML
    private TableColumn<Map, Void> inquire;

    @FXML
    private TableColumn<Map, String> personColumn;
    private ArrayList<Map> personList = new ArrayList();
    public static ObservableList<Map> observableList= FXCollections.observableArrayList();
    private void setTableViewData() {
        observableList.clear();
        for (Map map : personList) {
            observableList.addAll(FXCollections.observableArrayList(map));
        }
        dataTableView.setItems(observableList);
    }
    public void initialize()
    {
        personColumn.setCellValueFactory(new MapValueFactory<>("person"));
        inquire.setCellFactory(col -> new TableCell<Map, Void>() {
            private final HBox hbox = new HBox();
            private final Button btn = new Button("查询");
            private final Button button=new Button("删除");
            {
                btn.setOnAction(event -> {
                    Map rowData = getTableView().getItems().get(getIndex());
                    try {
                        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("PersonInformation.fxml"));
                        Stage stage = new Stage();
                        stage.setTitle("用户信息");
                        stage.setScene(new Scene(fxmlLoader.load()));
                        stage.show();

                        PersonInformation personInformation = fxmlLoader.getController();
                        personInformation.init(rowData.get("person").toString());
                        System.out.println("OK");
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                });
                button.setOnAction(event -> {
                    Map rowData=getTableView().getItems().get(getIndex());
                    personList.remove(rowData);
                    try {
                        Main.connect.sendMessage("deletePersonRequest");
                        Main.connect.sendMessage(rowData.get("person").toString());
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
        searchPerson();
        System.out.println("this is init");
    }
    public void searchPerson() {
        try {
            Main.connect.sendMessage("searchPersonRequest");
            ArrayList<String> result = (ArrayList<String>) Main.connect.getObject();
            personList.clear();
            for (String s : result) {
                personList.add(Map.of("person", s));
            }
            setTableViewData();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
