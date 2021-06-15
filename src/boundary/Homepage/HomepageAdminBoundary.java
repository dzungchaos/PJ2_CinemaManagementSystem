package boundary.Homepage;

import boundary.ManagerCinema.ManagerCinemaBoundary;
import boundary.ManagerMovie.ManagerMovieBoundary;
import boundary.ManagerTicket.ManagerTicketBoundary;
import boundary.ManagerUser.ManagerUserBoundary;
import boundary.PersonalInfo.ChangePasswordBoundary;
import boundary.PersonalInfo.ModifyPersonalInfoBoundary;
import controller.UserController;
import entity.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class HomepageAdminBoundary {
    @FXML
    public Button buttonTicket;
    @FXML
    public Button buttonStatistic;
    @FXML
    public Button buttonUser;
    @FXML
    public Button buttonCinema;

    private UserController users = new UserController();

    private User currentUser;

    @FXML
    public Button buttonMovie;

    @FXML
    public Button buttonLogout;

    @FXML
    public Button buttonModify;

    @FXML
    public Button buttonChangePassword;

    @FXML
    public Label labelName;

    @FXML
    public Label labelPhone;

    @FXML
    public Label labelGender;

    @FXML
    public Label labelBirthday;

    @FXML
    public Label labelAddress;

    @FXML
    public ImageView imageViewUserAVT;

    public void initData(User user) {
        currentUser = user;
        labelName.setText(currentUser.getUsers_name());
        labelPhone.setText(currentUser.getUsers_phone());
        labelGender.setText(currentUser.getUsers_gender());
        labelBirthday.setText(currentUser.getUsers_birthday());
        labelAddress.setText(currentUser.getUsers_address());
    }

    @FXML
    public void doLogOut(ActionEvent event) {
        currentUser.setUsers_isOnline(false);
        Stage stage = (Stage) buttonLogout.getScene().getWindow();
        stage.close();
    }

    @FXML
    public void doModify() throws IOException {
        Integer currentID = currentUser.getUsers_id();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/boundary/PersonalInfo/ModifyPersonalInfoBoundary.fxml"));
        Parent parent = loader.load();
        Stage stage = new Stage(StageStyle.DECORATED);
        stage.setTitle("CẬP NHẬT THÔNG TIN");
        stage.setScene(new Scene(parent));
        ModifyPersonalInfoBoundary boundary = loader.getController();
        boundary.initData(currentUser);
        stage.initOwner((Stage) buttonModify.getScene().getWindow());
        stage.initModality(Modality.WINDOW_MODAL);
        stage.showAndWait();
        users.clearData();
        users.loadUsers();
        currentUser = users.getUser(currentID);
        labelName.setText(currentUser.getUsers_name());
        labelPhone.setText(currentUser.getUsers_phone());
        labelGender.setText(currentUser.getUsers_gender());
        labelBirthday.setText(currentUser.getUsers_birthday());
        labelAddress.setText(currentUser.getUsers_address());
    }

    @FXML
    public void doChangePassword() throws IOException {
        Integer currentID = currentUser.getUsers_id();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/boundary/PersonalInfo/ChangePasswordBoundary.fxml"));
        Parent parent = loader.load();
        Stage stage = new Stage(StageStyle.DECORATED);
        stage.setTitle("ĐỔI MẬT KHẨU");
        stage.setScene(new Scene(parent));
        ChangePasswordBoundary boundary = loader.getController();
        boundary.initData(currentUser);
        stage.initOwner((Stage) buttonChangePassword.getScene().getWindow());
        stage.initModality(Modality.WINDOW_MODAL);
        stage.showAndWait();
        users.clearData();
        users.loadUsers();
        currentUser = users.getUser(currentID);
    }

    @FXML
    public void showMovieManager() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/boundary/ManagerMovie/ManagerMovieBoundary.fxml"));
        Parent parent = loader.load();
        Stage stage = new Stage(StageStyle.DECORATED);
        stage.setTitle("DANH SÁCH PHIM");
        stage.setScene(new Scene(parent));
        ManagerMovieBoundary boundary = loader.getController();
        boundary.initData(currentUser);
        stage.initOwner((Stage) buttonMovie.getScene().getWindow());
        stage.initModality(Modality.WINDOW_MODAL);
        stage.showAndWait();
        users.clearData();
        users.loadUsers();
    }

    @FXML
    public void doShowTicketManager(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/boundary/ManagerTicket/ManagerTicketBoundary.fxml"));
        Parent parent = loader.load();
        Stage stage = new Stage(StageStyle.DECORATED);
        stage.setTitle("DANH SÁCH VÉ");
        stage.setScene(new Scene(parent));
        ManagerTicketBoundary boundary = loader.getController();
        boundary.initData(currentUser);
        stage.initOwner((Stage) buttonTicket.getScene().getWindow());
        stage.initModality(Modality.WINDOW_MODAL);
        stage.showAndWait();
        users.clearData();
        users.loadUsers();
    }

    @FXML
    public void doShowStatisticManager(ActionEvent event) {

    }

    @FXML
    public void doShowUserManager(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/boundary/ManagerUser/ManagerUserBoundary.fxml"));
        Parent parent = loader.load();
        Stage stage = new Stage(StageStyle.DECORATED);
        stage.setTitle("DANH SÁCH NGƯỜI DÙNG");
        stage.setScene(new Scene(parent));
        ManagerUserBoundary boundary = loader.getController();
        boundary.initData(currentUser);
        stage.initOwner((Stage) buttonCinema.getScene().getWindow());
        stage.initModality(Modality.WINDOW_MODAL);
        stage.showAndWait();
        users.clearData();
        users.loadUsers();
    }

    @FXML
    public void doShowCinemaManager(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/boundary/ManagerCinema/ManagerCinemaBoundary.fxml"));
        Parent parent = loader.load();
        Stage stage = new Stage(StageStyle.DECORATED);
        stage.setTitle("DANH SÁCH PHÒNG CHIẾU");
        stage.setScene(new Scene(parent));
        ManagerCinemaBoundary boundary = loader.getController();
        boundary.initData(currentUser);
        stage.initOwner((Stage) buttonCinema.getScene().getWindow());
        stage.initModality(Modality.WINDOW_MODAL);
        stage.showAndWait();
        users.clearData();
        users.loadUsers();
    }
}
