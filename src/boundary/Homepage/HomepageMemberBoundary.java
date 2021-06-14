package boundary.Homepage;

import boundary.PersonalInfo.ChangePasswordBoundary;
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

    public void initialize() {

    }

    @FXML
    public void doLogOut(ActionEvent event) {
        currentUser.setUsers_isOnline(false);
        Stage stage = (Stage) buttonLogout.getScene().getWindow();
        stage.close();
    }

    @FXML
    public void doModify() {

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
    public void showMovieManager() {

    }
}



















