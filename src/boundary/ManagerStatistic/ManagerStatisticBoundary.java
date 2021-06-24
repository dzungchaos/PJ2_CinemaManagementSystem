package boundary.ManagerStatistic;

import controller.StatisticController;
import entity.Statistic;
import entity.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.FileChooser;

import java.io.File;
import java.io.IOException;

public class ManagerStatisticBoundary {
    private User currentUser;
    @FXML
    public TableView<Statistic> tableViewStatistic;
    @FXML
    public GridPane statisticPanel;
    @FXML
    public Button buttonGetStatistic;
    @FXML
    public Label labelTotal;
    @FXML
    public TextField fieldPurchasedDate;
    @FXML
    public Button buttonExportStatistic;

    private StatisticController statisticController = new StatisticController();
    private ObservableList<Statistic>  statistics = FXCollections.observableArrayList();
    private String purchasedDate;
    private Integer totalTurnover;

    public void initData(User user) {
        currentUser = user;
    }

    public void initialize() {
        purchasedDate = null;
        statisticController.loadStatisticByDate(null);
    }

    @FXML
    public void doGetStatistic(ActionEvent event) {
        tableViewStatistic.getItems().clear();
        purchasedDate = fieldPurchasedDate.getText();
        statisticController.loadStatisticByDate(purchasedDate);
        statistics = statisticController.getListStatisticByPurchaseDate();
        fieldPurchasedDate.clear();
        tableViewStatistic.setItems(statistics);
        totalTurnover = statisticController.getTotalTurnover(statistics);
        labelTotal.setText(totalTurnover.toString());
    }

    @FXML
    public void doExportStatistic(ActionEvent event) throws IOException {
        // System.out.println("Purchased Date: " + purchasedDate);
        FileChooser chooser = new FileChooser();
        chooser.setTitle("Xuất báo cáo");
        chooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Excel", "*.xlsx"),
                new FileChooser.ExtensionFilter("All files", "*.*")
        );

        File fileSave = chooser.showSaveDialog(buttonExportStatistic.getScene().getWindow());

        if (fileSave != null) {
            statisticController.saveStatisticToExcelFile(statistics, fileSave, currentUser, purchasedDate, totalTurnover);
            System.out.println("Create file: " + fileSave.getAbsolutePath());
        } else {
            System.out.println("Chooser was cancelled");
        }
    }












}
