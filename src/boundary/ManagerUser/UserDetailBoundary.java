package boundary.ManagerUser;

import controller.UserController;
import entity.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.util.Optional;

public class UserDetailBoundary {
    private UserController users = new UserController();
    private User selectedUser;
    private User currentUser;

    private ObservableList<String> permissionList = FXCollections.observableArrayList();

    @FXML
    public Label labelID;
    @FXML
    public Label labelUserName;
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
    public ComboBox<String> boxPermission;
    @FXML
    public Button buttonOK;
    @FXML
    public Button buttonExit;

    public void initData(User selectedUser, User currentUser) {
        this.selectedUser = selectedUser;
        this.currentUser = currentUser;

        if (currentUser.getUsers_permission().equals("ADMIN")) {
            permissionList.add("STAFF");
            permissionList.add("MEMBER");
        } else if (currentUser.getUsers_permission().equals("HEAD")) {
            permissionList.add("ADMIN");
            permissionList.add("STAFF");
            permissionList.add("MEMBER");
        }

        labelID.setText(selectedUser.getUsers_id().toString());
        labelUserName.setText(selectedUser.getUsers_userName());
        labelName.setText(selectedUser.getUsers_name());
        labelPhone.setText(selectedUser.getUsers_phone());
        labelGender.setText(selectedUser.getUsers_gender());
        labelBirthday.setText(selectedUser.getUsers_birthday());
        labelAddress.setText(selectedUser.getUsers_address());
        boxPermission.setItems(permissionList);
        boxPermission.getSelectionModel().select(selectedUser.getUsers_permission());
    }

    public void doUpdatePermission(ActionEvent event) {
        if (!boxPermission.getSelectionModel().getSelectedItem().equals(selectedUser.getUsers_permission())) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("XÁC NHẬN PHÂN QUYỀN!");
            alert.setTitle(null);
            alert.setContentText("Bạn vừa cấp quyền " + boxPermission.getSelectionModel().getSelectedItem()
                    + " cho " + selectedUser.getUsers_name() + " với tên đăng nhập " + selectedUser.getUsers_userName() + ". Bạn có chắc chắn không?");

            Optional<ButtonType> result = alert.showAndWait();
            if (result.isPresent() && result.get() == ButtonType.OK) {
                users.changePermissionUser(selectedUser, boxPermission.getSelectionModel().getSelectedItem());
                users.clearData();
                users.loadUsers();
            }
        }
        Stage stage = (Stage) buttonOK.getScene().getWindow();
        stage.close();
    }

    public void closeWindow(ActionEvent event) {
        Stage stage = (Stage) buttonExit.getScene().getWindow();
        stage.close();
    }
}
