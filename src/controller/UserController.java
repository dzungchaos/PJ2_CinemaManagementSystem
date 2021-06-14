package controller;

import entity.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import util.HibernateUtil;

import java.util.Iterator;
import java.util.List;

public class UserController {
    private static final SessionFactory factory = HibernateUtil.getSessionFactory();

    private ObservableList<User> users;

    public UserController() {
        users = FXCollections.observableArrayList();
        loadUsers();
    }

    public ObservableList<User> getUsers() {
        return users;
    }

    public Integer addUser(String users_userName,
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

        Session session = factory.openSession();
        Transaction transaction = null;
        Integer users_id = null;
        try {
            transaction = session.beginTransaction();
            User user = new User(users_userName,
                                users_password,
                                users_avatarUrl,
                                users_name,
                                users_phone,
                                users_gender,
                                users_birthday,
                                users_permission,
                                users_address,
                                users_isActive,
                                users_isOnline);
            users_id = (Integer) session.save(user);
            transaction.commit();
            users.add(user);
        } catch (HibernateException e) {
            if (transaction != null)
                transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }

        return users_id;
    }

    public void changePassword(User selectedUser, String password) {
        selectedUser.setUsers_password(password);
        Session session = factory.openSession();
        Transaction transaction = null;

        try {
            transaction = session.beginTransaction();
            session.update(selectedUser);
            transaction.commit();
        } catch (HibernateException e) {
            if (transaction != null)
                transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }

        users.clear();
        loadUsers();
    }

    public void modifyInfo(User selectedUser) {
        Session session = factory.openSession();
        Transaction transaction = null;

        try {
            transaction = session.beginTransaction();
            session.update(selectedUser);
            transaction.commit();
        } catch (HibernateException e) {
            if (transaction != null)
                transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }

        users.clear();
        loadUsers();
    }

    public boolean checkUserExist(String userName) {
        for (User user : users)
            if (userName.equals(user.getUsers_userName()))
                return true;
        return false;
    }

    public boolean checkLoginInfo(String userName, String password) {
        for (User user : users) {
            if (userName.equals(user.getUsers_userName())
                    && password.equals(user.getUsers_password())){
                return true;
            }
        }
        return false;
    }

    public boolean checkUserUnlock(String userName, String password) {
        for (User user : users) {
            if (userName.equals(user.getUsers_userName())
                    && password.equals(user.getUsers_password())){
                if(user.getUsers_isActive()) {
                    return true;
                }
            }
        }
        return false;
    }

    public User getUser(String userName, String password) {
        User gotUser = null;

        for (User user : users) {
            if (userName.equals(user.getUsers_userName())
                    && password.equals(user.getUsers_password())){
                gotUser = user;
            }
        }

        return gotUser;
    }

    public User getUser (String userName) {
        User gotUser = null;

        for (User user: users) {
            if (userName.equals(user.getUsers_userName())){
                gotUser = user;
            }
        }

        return gotUser;
    }

    public User getUser (Integer id) {
        User gotUser = null;

        for (User user: users) {
            if (id.equals(user.getUsers_id())){
                gotUser = user;
            }
        }

        return gotUser;
    }

    public ObservableList<User> getListUserByName(String userNamePart) {
        ObservableList<User> listUser = null;

        for (User user : users) {
            if (user.getUsers_name().contains(userNamePart))
                listUser.add(user);
        }

        return listUser;
    }

    public void lockUser(User selectedUser) {
        selectedUser.setUsers_isActive(false);
        Session session = factory.openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.update(selectedUser);
            transaction.commit();
        } catch (HibernateException e) {
            if (transaction != null)
                transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }

        users.clear();
        loadUsers();
    }

    public void unlockUser(User selectedUser) {
        selectedUser.setUsers_isActive(true);
        Session session = factory.openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.update(selectedUser);
            transaction.commit();
        } catch (HibernateException e) {
            if (transaction != null)
                transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }

        users.clear();
        loadUsers();
    }

    public void changePermissionUser(User selectedUser, String permission) {
        selectedUser.setUsers_permission(permission);
        Session session = factory.openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.update(selectedUser);
            transaction.commit();
        } catch (HibernateException e) {
            if (transaction != null)
                transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }

        users.clear();
        loadUsers();
    }

    public void loadUsers() {
        Session session = factory.openSession();
        try {
            List list = session.createQuery("FROM User").list();
            for (Iterator iterator = list.iterator(); iterator.hasNext();) {
                User user = (User) iterator.next();
                users.add(user);
            }
        } catch (HibernateException e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    public void listUsers() {
        Session session = factory.openSession();
        int count = 0;
        try {
            List list = session.createQuery("FROM User").list();
            for (Iterator iterator = list.iterator(); iterator.hasNext();) {
                count ++;
                System.out.println(count + "\n");
                User user = (User) iterator.next();
                System.out.println("username: " + user.getUsers_userName());
                System.out.println("password: " + user.getUsers_password());
                System.out.println("Name: " + user.getUsers_name());
                System.out.println("Permission: " + user.getUsers_permission());
                System.out.println("Address: " + user.getUsers_address());
            }
        } catch (HibernateException e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    public void clearData() {
        users.clear();
    }

}
