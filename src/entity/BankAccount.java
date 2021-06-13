package entity;

import javax.persistence.*;

@Entity
@Table(name = "PJ2_bankAccounts")
public class BankAccount {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "bankAccounts_id", unique = true, nullable = false)
    private Integer bankAccounts_id;

    @Column(name = "bankAccounts_userName", length = 256)
    private String bankAccounts_userName;

    @Column(name = "bankAccounts_password", length = 256)
    private String bankAccounts_password;

    @Column(name = "bankAccounts_ownerName", length = 256)
    private String bankAccounts_ownerName;

    @Column(name = "bankAccounts_cardNumber", length = 256)
    private String bankAccounts_cardNumber;

    @Column(name = "bankAccounts_balance")
    private Integer bankAccounts_balance;

    public BankAccount() {

    }

    public BankAccount(String bankAccounts_userName,
                       String bankAccounts_password,
                       String bankAccounts_ownerName,
                       String bankAccounts_cardNumber,
                       Integer bankAccounts_balance) {
        this.bankAccounts_userName = bankAccounts_userName;
        this.bankAccounts_password = bankAccounts_password;
        this.bankAccounts_ownerName = bankAccounts_ownerName;
        this.bankAccounts_cardNumber = bankAccounts_cardNumber;
        this.bankAccounts_balance = bankAccounts_balance;
    }


    public Integer getBankAccounts_id() {
        return bankAccounts_id;
    }

    public String getBankAccounts_userName() {
        return bankAccounts_userName;
    }

    public void setBankAccounts_userName(String bankAccounts_userName) {
        this.bankAccounts_userName = bankAccounts_userName;
    }

    public String getBankAccounts_password() {
        return bankAccounts_password;
    }

    public void setBankAccounts_password(String bankAccounts_password) {
        this.bankAccounts_password = bankAccounts_password;
    }

    public String getBankAccounts_ownerName() {
        return bankAccounts_ownerName;
    }

    public void setBankAccounts_ownerName(String bankAccounts_ownerName) {
        this.bankAccounts_ownerName = bankAccounts_ownerName;
    }

    public String getBankAccounts_cardNumber() {
        return bankAccounts_cardNumber;
    }

    public void setBankAccounts_cardNumber(String bankAccounts_cardNumber) {
        this.bankAccounts_cardNumber = bankAccounts_cardNumber;
    }


    public Integer getBankAccounts_balance() {
        return bankAccounts_balance;
    }

    public void setBankAccounts_balance(Integer bankAccounts_balance) {
        this.bankAccounts_balance = bankAccounts_balance;
    }

    public void setBankAccounts_value(BankAccount bankAccount){
        this.bankAccounts_id = bankAccount.bankAccounts_id;
        this.bankAccounts_userName = bankAccount.bankAccounts_userName;
        this.bankAccounts_password = bankAccount.bankAccounts_password;
        this.bankAccounts_ownerName = bankAccount.bankAccounts_ownerName;
        this.bankAccounts_cardNumber = bankAccount.bankAccounts_cardNumber;
        this.bankAccounts_balance = bankAccount.bankAccounts_balance;
    }
}
