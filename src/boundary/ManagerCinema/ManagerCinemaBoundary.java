package boundary.ManagerCinema;

import controller.CinemaController;
import entity.Cinema;
import entity.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class ManagerCinemaBoundary {
    @FXML
    public Button buttonImportCinema;
    @FXML
    public Button buttonExportCinema;
    private CinemaController cinemas;

    private User currentUser;

    @FXML
    public Button buttonAdd;
    @FXML
    public Button buttonUpdate;
    @FXML
    public Button buttonLock;
    @FXML
    public Button buttonUnlock;
    @FXML
    public TextField fieldFindCinema;
    @FXML
    public TableView<Cinema> tableViewCinema;

    public void initData(User user) {
        currentUser = user;
    }

    public void initialize() {
        cinemas = new CinemaController();
        tableViewCinema.setItems(cinemas.getCinemas());
    }

    @FXML
    public void doSearchCinema(KeyEvent keyEvent) {
        String cinemaNamePart = fieldFindCinema.getText();
        tableViewCinema.setItems(cinemas.getListCinema(cinemaNamePart));
    }

    @FXML
    public void doAddCinema(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/boundary/ManagerCinema/AddCinemaBoundary.fxml"));
        Parent parent = loader.load();
        Stage stage = new Stage(StageStyle.DECORATED);
        stage.setTitle("THÊM PHÒNG CHIẾU PHIM");
        stage.setScene(new Scene(parent));
        stage.initOwner((Stage) buttonAdd.getScene().getWindow());
        stage.initModality(Modality.WINDOW_MODAL);
        stage.showAndWait();
        cinemas.clearData();
        cinemas.loadCinemas();
        tableViewCinema.setItems(cinemas.getCinemas());
    }

    @FXML
    public void doUpdateCinema(ActionEvent event) throws IOException {
        Cinema selectedCinema = tableViewCinema.getSelectionModel().getSelectedItem();
        if (selectedCinema == null) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Chưa chọn phòng chiếu");
            alert.setHeaderText(null);
            alert.setContentText("Bạn chưa chọn phòng chiếu, hãy chọn một phòng chiếu để tiến hành cập nhật phòng chiếu");
            alert.show();
            return;
        }

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/boundary/ManagerCinema/UpdateCinemaBoundary.fxml"));
        Parent parent = loader.load();
        Stage stage = new Stage(StageStyle.DECORATED);
        stage.setTitle("CẬP NHẬT PHÒNG CHIẾU PHIM");
        stage.setScene(new Scene(parent));
        UpdateCinemaBoundary boundary = loader.getController();
        boundary.initData(selectedCinema);
        stage.initOwner((Stage) buttonUpdate.getScene().getWindow());
        stage.initModality(Modality.WINDOW_MODAL);
        stage.showAndWait();
        cinemas.clearData();
        cinemas.loadCinemas();
        tableViewCinema.setItems(cinemas.getCinemas());

    }

    @FXML
    public void doLockCinema(ActionEvent event) {
        Cinema selectedCinema = tableViewCinema.getSelectionModel().getSelectedItem();
        if (selectedCinema == null) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Chưa chọn phòng chiếu");
            alert.setHeaderText(null);
            alert.setContentText("Bạn chưa chọn phòng chiếu, hãy chọn một phòng chiếu để tiến hành khoá phòng chiếu");
            alert.show();
            return;
        }

        if (!selectedCinema.getCinemas_isActive()) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Phòng chiếu đã bị khoá");
            alert.setHeaderText(null);
            alert.setContentText("Phòng chiếu bạn chọn đã bị khoá, hãy chọn một phòng chiếu khác để tiến hành khoá phòng chiếu");
            alert.show();
            return;
        }

        cinemas.lockCinema(selectedCinema);
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Khoá thành công");
        alert.setHeaderText(null);
        alert.setContentText("Đã khoá phòng chiếu phim được chọn! Người dùng sẽ không thể mua vé xem phim tại phòng chiếu phim này");
        alert.show();
        cinemas.clearData();
        cinemas.loadCinemas();
        tableViewCinema.setItems(cinemas.getCinemas());
    }

    @FXML
    public void doUnlockCinema(ActionEvent event) {
        Cinema selectedCinema = tableViewCinema.getSelectionModel().getSelectedItem();
        if (selectedCinema == null) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Chưa chọn phòng chiếu");
            alert.setHeaderText(null);
            alert.setContentText("Bạn chưa chọn phòng chiếu, hãy chọn một phòng chiếu để tiến hành mở khoá phòng chiếu");
            alert.show();
            return;
        }

        if (selectedCinema.getCinemas_isActive()) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Phòng chiếu đã được mở khoá");
            alert.setHeaderText(null);
            alert.setContentText("Phòng chiếu bạn chọn đã được mở khoá, hãy chọn một phòng chiếu khác để tiến hành mở khoá phòng chiếu");
            alert.show();
            return;
        }

        cinemas.unlockCinema(selectedCinema);
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Mở khoá thành công");
        alert.setHeaderText(null);
        alert.setContentText("Đã mở khoá phòng chiếu phim được chọn! Người dùng sẽ có thể thể mua vé xem phim tại phòng chiếu phim này");
        alert.show();
        cinemas.clearData();
        cinemas.loadCinemas();
        tableViewCinema.setItems(cinemas.getCinemas());
    }

    @FXML
    public void doImportCinema(ActionEvent event) {

    }

    @FXML
    public void doExportCinema(ActionEvent event) {

    }
}
