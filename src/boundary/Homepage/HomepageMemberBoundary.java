package boundary.Homepage;

import entity.User;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;

public class HomepageMemberBoundary {
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
}



















