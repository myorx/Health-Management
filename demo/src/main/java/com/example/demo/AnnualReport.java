package com.example.demo;

import com.example.OBJECT.ReportData;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;

public class AnnualReport {

    @FXML
    private Label Label1;

    @FXML
    private ImageView Picture1;

    @FXML
    private ImageView Picture2;

    @FXML
    private ImageView Picture3;

    @FXML
    private Button Progress;

    @FXML
    private ProgressBar ProgressBar;

    @FXML
    private TextArea ReadTextArea;

    @FXML
    private TextField yearEntry;

    @FXML
    void OnProgress(ActionEvent event) throws Exception {
        String year = yearEntry.getText();
        Main.connect.sendMessage("ReportRequest");
        Main.connect.sendMessage(year);
        StringBuilder builder = new StringBuilder("在");
        ReportData reportData = (ReportData) Main.connect.getObject();
        String sb=builder.append(year).append("年中，您总共录入").append(reportData.getDayNumber()).append("天的健康数据。\n其中").append(reportData.getMostNumberMonth()).append("月记录得最多，为").append(reportData.getMostNumber()).append("天。\n").append(reportData.getLeastNumberMonth()).append("月记录得最少，为").append(reportData.getLeastNumber())
                .append("天。\n最低身高为").append(reportData.getLeastHeight()).append("cm,最高身高为").append(reportData.getHighestHeight()).append("cm。\n最低体重为").append(reportData.getLeastWeight()).append("kg，最高体重为").append(reportData.getHighestWeight()).append("kg。\n在").append(reportData.getMostStepMonth()).append("月").append(reportData.getMostStepDay())
                .append("日这一天您走了最多的步数，高达").append(reportData.getMostStep()).append("步。\n您的平均睡眠为").append(reportData.getAverageSleepHours()).append("小时，希望您在新的一年可以再接再厉，健康生活！").toString();
        Progress.setDisable(true);
        new Thread(() -> {
            for (int i = 1; i <= sb.length(); i++) {
                String partText = sb.substring(0, i);
                double progress = (double) i / sb.length();
                Platform.runLater(() -> {
                    ReadTextArea.setText(partText);
                    ProgressBar.setProgress(progress);
                });

                try {
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            Platform.runLater(() -> Progress.setDisable(false));
        }).start();
    }



}
