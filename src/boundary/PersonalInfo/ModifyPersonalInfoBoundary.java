package boundary.PersonalInfo;

import controller.UserController;
import entity.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class ModifyPersonalInfoBoundary {
    private UserController users = new UserController();
    @FXML
    public TextField fieldName;
    @FXML
    public TextField fieldPhone;
    @FXML
    public TextField fieldGender;
    @FXML
    public TextField fieldBirthday;
    @FXML
    public TextField fieldAddress;

    @FXML
    public Button buttonExit;

    @FXML
    public Button buttonUpdate;

    private User currentUser;

    public void initData(User user) {
        currentUser = user;
        fieldName.setText(currentUser.getUsers_name());
        fieldPhone.setText(currentUser.getUsers_phone());
        fieldGender.setText(currentUser.getUsers_gender());
        fieldBirthday.setText(currentUser.getUsers_birthday());
        fieldAddress.setText(currentUser.getUsers_address());
    }

    @FXML
    public void closeWindow() {
        Stage stage = (Stage) buttonExit.getScene().getWindow();
        stage.close();
    }

    @FXML
    public void doModify(ActionEvent event) {
        String users_name = fieldName.getText();
        String users_phone = fieldPhone.getText();
        String users_gender = fieldGender.getText();
        String users_birthday = fieldBirthday.getText();
        String users_address = fieldAddress.getText();

        currentUser.setUsers_name(users_name);
        currentUser.setUsers_phone(users_phone);
        currentUser.setUsers_gender(users_gender);
        currentUser.setUsers_birthday(users_birthday);
        currentUser.setUsers_address(users_address);
        users.modifyInfo(currentUser);

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Đổi thông tin cá nhân thành công");
        alert.setHeaderText(null);
        alert.setContentText("Thông tin cá nhân của đã được thay đổi. Bạn có thể đặt vé xem phim với các thông tin mới.");
        alert.showAndWait();
        users.clearData();
        Stage stage = (Stage) buttonUpdate.getScene().getWindow();
        stage.close();
    }
}
