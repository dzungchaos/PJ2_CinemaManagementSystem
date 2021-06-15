package boundary.Homepage;

import boundary.ManagerMovie.ManagerMovieMemberBoundary;
import boundary.ManagerTicket.ManagerTicketBoundary;
import boundary.ManagerTicket.ManagerTicketMemberBoundary;
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

public class HomepageMemberBoundary {
    @FXML
    public Button buttonTicket;

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
        loader.setLocation(getClass().getResource("/boundary/ManagerMovie/ManagerMovieMemberBoundary.fxml"));
        Parent parent = loader.load();
        Stage stage = new Stage(StageStyle.DECORATED);
        stage.setTitle("DANH SÁCH PHIM RẠP ĐANG CHIẾU");
        stage.setScene(new Scene(parent));
        stage.setResizable(false);
        ManagerMovieMemberBoundary boundary = loader.getController();
        boundary.initData(currentUser);
        stage.initOwner((Stage) buttonModify.getScene().getWindow());
        stage.initModality(Modality.WINDOW_MODAL);
        stage.showAndWait();
        users.clearData();
        users.loadUsers();
    }

    @FXML
    public void doShowTicketManager(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/boundary/ManagerTicket/ManagerTicketMemberBoundary.fxml"));
        Parent parent = loader.load();
        Stage stage = new Stage(StageStyle.DECORATED);
        stage.setTitle("DANH SÁCH VÉ ĐÃ MUA");
        stage.setScene(new Scene(parent));
        ManagerTicketMemberBoundary boundary = loader.getController();
        boundary.initData(currentUser);
        stage.initOwner((Stage) buttonTicket.getScene().getWindow());
        stage.initModality(Modality.WINDOW_MODAL);
        stage.showAndWait();
    }
}



















