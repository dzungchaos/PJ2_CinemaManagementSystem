package boundary.ManagerStatistic;

import controller.StatisticController;
import entity.Statistic;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableView;
import javafx.scene.layout.GridPane;

import java.time.format.DateTimeFormatter;

public class ManagerStatisticBoundary {
    @FXML
    public TableView<Statistic> tableViewStatistic;
    @FXML
    public GridPane statisticPanel;
    @FXML
    public DatePicker datePickerPurchasedDate;
    @FXML
    public Button buttonGetStatistic;

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
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        String purchasedDate = dtf.format(datePickerPurchasedDate.getValue());
        statisticController.loadStatisticByDate(purchasedDate);
        statistics = statisticController.getListStatisticByPurchaseDate();
        tableViewStatistic.setItems(statistics);
    }
}
