package entity;

import javax.persistence.*;

@Entity
@Table(name = "PJ2_users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "users_id", unique = true, nullable = false)
    private Integer users_id;

    @Column(name = "users_userName", length = 256)
    private String users_userName;

    @Column(name = "users_password", length = 256)
    private String users_password;

    @Column(name = "users_avatarUrl", length = 256)
    private String users_avatarUrl;

    @Column(name = "users_name", length = 256)
    private String users_name;

    @Column(name = "users_phone", length = 16)
    private String users_phone;

    @Column(name = "users_gender", length = 16)
    private String users_gender;

    @Column(name = "users_birthday", length = 16)
    private String users_birthday;

    @Column(name = "users_permission", length = 256)
    private String users_permission;

    @Column(name = "users_address", length = 256)
    private String users_address;

    @Column(name = "users_isActive")
    private Boolean users_isActive;

    @Column(name = "users_isOnline")
    private Boolean users_isOnline;

    public User() {

    }

    public User(String users_userName,
                String users_password,
                String users_avatarUrl,
                String users_name,
                String users_phone,
                String users_gender,
                String users_birthday,
                String users_permission,
                String users_address,
                Boolean users_isActive,
                Boolean users_isOnline) {
        this.users_userName = users_userName;
        this.users_password = users_password;
        this.users_avatarUrl = users_avatarUrl;
        this.users_name = users_name;
        this.users_phone = users_phone;
        this.users_gender = users_gender;
        this.users_birthday = users_birthday;
        this.users_permission = users_permission;
        this.users_address = users_address;
        this.users_isActive = users_isActive;
        this.users_isOnline = users_isOnline;
    }

    public Integer getUsers_id() {
        return users_id;
    }

    public String getUsers_userName() {
        return users_userName;
    }

    public void setUsers_userName(String users_userName) {
        this.users_userName = users_userName;
    }

    public String getUsers_password() {
        return users_password;
    }

    public void setUsers_password(String users_password) {
        this.users_password = users_password;
    }

    public String getUsers_avatarUrl() {
        return users_avatarUrl;
    }

    public void setUsers_avatarUrl(String users_avatarUrl) {
        this.users_avatarUrl = users_avatarUrl;
    }

    public String getUsers_name() {
        return users_name;
    }

    public void setUsers_name(String users_name) {
        this.users_name = users_name;
    }

    public String getUsers_phone() {
        return users_phone;
    }

    public void setUsers_phone(String users_phone) {
        this.users_phone = users_phone;
    }

    public String getUsers_gender() {
        return users_gender;
    }

    public void setUsers_gender(String users_gender) {
        this.users_gender = users_gender;
    }

    public String getUsers_birthday() {
        return users_birthday;
    }

    public void setUsers_birthday(String users_birthday) {
        this.users_birthday = users_birthday;
    }

    public String getUsers_permission() {
        return users_permission;
    }

    public void setUsers_permission(String users_permission) {
        this.users_permission = users_permission;
    }

    public String getUsers_address() {
        return users_address;
    }

    public void setUsers_address(String users_address) {
        this.users_address = users_address;
    }

    public Boolean getUsers_isActive() {
        return users_isActive;
    }

    public void setUsers_isActive(Boolean users_isActive) {
        this.users_isActive = users_isActive;
    }

    public Boolean getUsers_isOnline() {
        return users_isOnline;
    }

    public void setUsers_isOnline(Boolean users_isOnline) {
        this.users_isOnline = users_isOnline;
    }

    @Override
    public String toString() {
        return "User{" +
                "users_id=" + users_id +
                ", users_userName='" + users_userName + '\'' +
                ", users_password='" + users_password + '\'' +
                ", users_avatarUrl='" + users_avatarUrl + '\'' +
                ", users_name='" + users_name + '\'' +
                ", users_phone='" + users_phone + '\'' +
                ", users_gender='" + users_gender + '\'' +
                ", users_birthday=" + users_birthday +
                ", users_permission='" + users_permission + '\'' +
                ", users_address='" + users_address + '\'' +
                ", users_isActive=" + users_isActive +
                ", users_isOnline=" + users_isOnline +
                '}';
    }
}
