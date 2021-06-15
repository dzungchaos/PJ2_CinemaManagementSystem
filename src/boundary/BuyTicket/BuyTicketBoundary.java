package boundary.BuyTicket;

import boundary.Pay.PurchaseTicketBoundary;
import controller.TicketController;
import entity.Showtime;
import entity.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class BuyTicketBoundary {
    private User currentUser;
    private Showtime selectedShowtime;
    private TicketController tickets = new TicketController();
    private ObservableList<String> seatRowList = FXCollections.observableArrayList();
    private ObservableList<String> seatColumnList = FXCollections.observableArrayList();

    @FXML
    public Label labelUserName;
    @FXML
    public Label labelMovieName;
    @FXML
    public Label labelCinemaName;
    @FXML
    public Label labelTime;
    @FXML
    public Label labelDate;
    @FXML
    public Label labelPurchasedDate;
    @FXML
    public ComboBox<String> boxSeatRow;
    @FXML
    public ComboBox<String> boxSeatColumn;
    @FXML
    public Button buttonPay;
    @FXML
    public Button buttonExit;
    @FXML
    public Label labelPaidSeat;

    public void initData(User user, Showtime showtime) {
        currentUser = user;
        selectedShowtime = showtime;

        labelUserName.setText(currentUser.getUsers_name());
        labelMovieName.setText(selectedShowtime.getShowtimes_movies_name());
        labelCinemaName.setText(selectedShowtime.getShowtimes_cinemas_name());
        labelTime.setText(selectedShowtime.getShowtimes_time());
        labelDate.setText(selectedShowtime.getShowtimes_date());
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        labelPurchasedDate.setText(dtf.format(LocalDate.now()));

        seatRowList.add("A");
        seatRowList.add("B");
        seatRowList.add("C");
        seatRowList.add("D");
        seatRowList.add("E");
        boxSeatRow.setItems(seatRowList);

        seatColumnList.add("1");
        seatColumnList.add("2");
        seatColumnList.add("3");
        seatColumnList.add("4");
        seatColumnList.add("5");
        seatColumnList.add("6");
        boxSeatColumn.setItems(seatColumnList);

        labelPaidSeat.setText(tickets.getListBoughtSeatByShowtimeID(selectedShowtime.getShowtimes_id()).toString());
    }

    public void doPayTicket(ActionEvent event) throws IOException {
        String orderSeat = boxSeatRow.getSelectionModel().getSelectedItem() + boxSeatColumn.getSelectionModel().getSelectedItem();
        String purchasedDate = labelPurchasedDate.getText();

        if (tickets.isSeatBought(orderSeat, selectedShowtime.getShowtimes_id())) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("GHẾ NGỒI KHÔNG HỢP LÝ");
            alert.setHeaderText(null);
            alert.setContentText("Ghế bạn chọn đã được đặt! Vui lòng chọn chỗ ngồi khác!");
            alert.show();
            return;
        }

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/boundary/Pay/PurchaseTicketBoundary.fxml"));
        Parent parent = loader.load();
        Stage stage = new Stage(StageStyle.DECORATED);
        stage.setTitle("THANH TOÁN VÉ XEM PHIM");
        stage.setScene(new Scene(parent));
        PurchaseTicketBoundary boundary = loader.getController();
        boundary.initData(currentUser, selectedShowtime, purchasedDate, orderSeat);
        stage.initOwner((Stage) buttonPay.getScene().getWindow());
        stage.initModality(Modality.WINDOW_MODAL);
        stage.showAndWait();
        tickets.clearData();
        tickets.loadTickets();
        stage.close();
    }

    public void closeWindow(ActionEvent event) {
        Stage stage = (Stage) buttonExit.getScene().getWindow();
        stage.close();
    }


}
