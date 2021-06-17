package boundary.ManagerStatistic;

import controller.StatisticController;
import entity.Statistic;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;

public class ManagerStatisticBoundary {
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

    public void initData() {

    }

    public void initialize() {
        String purchasedDate = null;
        statisticController.loadStatisticByDate(null);
    }

    @FXML
    public void doGetStatistic(ActionEvent event) {
        tableViewStatistic.getItems().clear();
        String purchasedDate = fieldPurchasedDate.getText();
        statisticController.loadStatisticByDate(purchasedDate);
        statistics = statisticController.getListStatisticByPurchaseDate();
        fieldPurchasedDate.clear();
        tableViewStatistic.setItems(statistics);
        Integer totalTurnover = statisticController.getTotalTurnover(statistics);
        labelTotal.setText(totalTurnover.toString());
    }

    @FXML
    public void doExportStatistic(ActionEvent event) {

    }
}
