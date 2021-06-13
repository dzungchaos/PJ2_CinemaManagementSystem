package controller;

import entity.BankAccount;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import util.HibernateUtil;

import java.util.Iterator;
import java.util.List;

public class BankAccountController {
    private static final SessionFactory factory = HibernateUtil.getSessionFactory();

    private ObservableList<BankAccount> bankAccounts;

    public BankAccountController() {
        bankAccounts = FXCollections.observableArrayList();
        loadBankAccounts();
    }

    public Integer addBankAccount(String bankAccounts_userName,
                                  String bankAccounts_password,
                                  String bankAccounts_ownerName,
                                  String bankAccounts_cardNumber,
                                  Integer bankAccounts_balance) {
        Session session = factory.openSession();
        Transaction tx = null;
        Integer bankAccounts_id = null;
        try {
            tx = session.beginTransaction();
            BankAccount bankAccount = new BankAccount(bankAccounts_userName,
                    bankAccounts_password,
                    bankAccounts_ownerName,
                    bankAccounts_cardNumber,
                    bankAccounts_balance);
            bankAccounts_id = (Integer) session.save(bankAccount);
            tx.commit();
            bankAccounts.add(bankAccount);
        } catch (HibernateException e) {
            if (tx != null)
                tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }

        return bankAccounts_id;
    }

    // Lấy dũ liệu trong CSDL rồi nhét vào ctdl bankAccounts
    public void loadBankAccounts () {
        Session session = factory.openSession();
        try {
            List list = session.createQuery(" FROM BankAccount").list();
            for (Iterator iterator = list.iterator(); iterator.hasNext();) {
                BankAccount bankAccount = (BankAccount) iterator.next();
                bankAccounts.add(bankAccount);
            }
        } catch (HibernateException e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    // Liệt kê dữ liệu trong CSDL
    public void listBankAccounts () {
        Session session = factory.openSession();
        try {
            List list = session.createQuery(" FROM BankAccount").list();
            for (Iterator iterator = list.iterator(); iterator.hasNext();) {
                BankAccount bankAccount = (BankAccount) iterator.next();
                System.out.println("bankAccounts_userName: " + bankAccount.getBankAccounts_userName());
                System.out.println("bankAccounts_password: " + bankAccount.getBankAccounts_password());
                System.out.println("bankAccounts_ownerName: " + bankAccount.getBankAccounts_ownerName());
                System.out.println("bankAccounts_cardNumber: " + bankAccount.getBankAccounts_cardNumber());
                System.out.println("bankAccounts_balance: " + bankAccount.getBankAccounts_balance());
            }
        } catch (HibernateException e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    // Kiểm tra xem dữ liệu người dùng nhập vào có tồn tại không?
    public boolean checkBankAccountExist(String ownerName, String cardNumber) {

        for (BankAccount bankAccount : bankAccounts) {
            if (ownerName.equals(bankAccount.getBankAccounts_ownerName())
                    && cardNumber.equals(bankAccount.getBankAccounts_cardNumber())) {
                return true;
            }
        }

        return false;
    }

    public BankAccount getBankAccount(String ownerName, String cardNumber) {
        BankAccount account = null;

        for (BankAccount bankAccount : bankAccounts) {
            if (ownerName.equals(bankAccount.getBankAccounts_ownerName())
                    && cardNumber.equals(bankAccount.getBankAccounts_cardNumber())) {
                account = bankAccount;
            }
        }

        return account;
    }

    // Kiểm tra xem tài khoản còn đủ tiền không
    public boolean checkBankAccountBalance(BankAccount bankAccount) {
        return (bankAccount.getBankAccounts_balance() >= (Integer) 40000);
    }

    // Trừ thẳng 40000 vào tài khoản ngân hàng
    public void subtractBankAccountBalance (BankAccount bankAccount) {
        bankAccount.setBankAccounts_balance(bankAccount.getBankAccounts_balance() - (Integer) 40000);
        Session session = factory.openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.update(bankAccount);
            transaction.commit();
        } catch (HibernateException e) {
            if (transaction != null)
                transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }

        bankAccounts.clear();
        loadBankAccounts();
    }

    public void clearData() {
        bankAccounts.clear();
    }

    public ObservableList<BankAccount> getBankAccounts() {
        return bankAccounts;
    }

}
