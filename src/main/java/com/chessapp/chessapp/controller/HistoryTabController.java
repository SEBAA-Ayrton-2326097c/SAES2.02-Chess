package com.chessapp.chessapp.controller;

import com.chessapp.chessapp.model.HistoryEntry;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.File;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class HistoryTabController {

    @FXML
    private TableView<HistoryEntry> historyTable;

    @FXML
    private TableColumn<HistoryEntry, String> pseudo1Column;

    @FXML
    private TableColumn<HistoryEntry, String> pseudo2Column;

    @FXML
    private TableColumn<HistoryEntry, String> dateColumn;

    private final ObservableList<HistoryEntry> historyData = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        pseudo1Column.setCellValueFactory(new PropertyValueFactory<>("pseudo1"));
        pseudo2Column.setCellValueFactory(new PropertyValueFactory<>("pseudo2"));
        dateColumn.setCellValueFactory(new PropertyValueFactory<>("date"));

        loadHistoryData();
        historyTable.setItems(historyData);
    }

    private void loadHistoryData() {
        File directory = new File("Data/Historique");
        File[] files = directory.listFiles((dir, name) -> name.endsWith(".csv"));

        if (files != null) {
            Pattern pattern = Pattern.compile("^(.*)-(.*)-(\\d{2}-\\d{2}-\\d{4}_\\d{2}-\\d{2})\\.csv$");
            for (File file : files) {
                Matcher matcher = pattern.matcher(file.getName());
                if (matcher.matches()) {
                    String pseudo1 = matcher.group(1);
                    String pseudo2 = matcher.group(2);
                    String date = matcher.group(3).replace("-", ":");  // Replace "-" with ":" for proper time format

                    historyData.add(new HistoryEntry(pseudo1, pseudo2, date));
                }
            }
        }
    }
}
