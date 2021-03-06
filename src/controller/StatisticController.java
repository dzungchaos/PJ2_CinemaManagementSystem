package controller;

import entity.Statistic;
import entity.Ticket;
import entity.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import util.HibernateUtil;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Iterator;
import java.util.List;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class StatisticController {
    private static final SessionFactory factory = HibernateUtil.getSessionFactory();
    private ObservableList<Statistic> statistics;
    private ObservableList<Statistic> listStatisticByPurchaseDate;

    public StatisticController() {
        statistics = FXCollections.observableArrayList();
        listStatisticByPurchaseDate = FXCollections.observableArrayList();
    }

    public ObservableList<Statistic> getListStatisticByPurchaseDate() {
        return listStatisticByPurchaseDate;
    }

    public void loadStatisticByDate(String purchasedDate) {
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = DriverManager.getConnection("jdbc:sqlserver://localhost;databaseName=PJ2_CinemaDB;user=sa;password=1234");

            String sql = "SELECT\ttickets_movies_name AS movies_name, COUNT(tickets_id) AS viewCount, COUNT(tickets_id) * 40000 AS turnover\n" +
                    "FROM\tPJ2_tickets\n" +
                    "WHERE\ttickets_purchasedDate like ?\n" +
                    "GROUP BY tickets_movies_name";
            // 15-06-2021
            statement = connection.prepareCall(sql);
            statement.setString(1, "%" + purchasedDate);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Statistic statistic = new Statistic(
                        resultSet.getString("movies_name"),
                        resultSet.getInt("viewCount"),
                        resultSet.getInt("turnover"));
                listStatisticByPurchaseDate.add(statistic);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void listStatisticByDate(String purchasedDate) {
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = DriverManager.getConnection("jdbc:sqlserver://localhost;databaseName=PJ2_CinemaDB;user=sa;password=1234");

            String sql = "SELECT\ttickets_movies_name AS movies_name, COUNT(tickets_id) AS viewCount, COUNT(tickets_id) * 40000 AS turnover\n" +
                    "FROM\tPJ2_tickets\n" +
                    "WHERE\ttickets_purchasedDate like ?\n" +
                    "GROUP BY tickets_movies_name";
            // 15-06-2021
            statement = connection.prepareCall(sql);
            statement.setString(1, "%" + purchasedDate);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Statistic statistic = new Statistic(
                        resultSet.getString("movies_name"),
                        resultSet.getInt("viewCount"),
                        resultSet.getInt("turnover"));
                // listStatisticByPurchaseDate.add(statistic);
                System.out.println("Phim: " + statistic.getMovies_name());
                System.out.println("L?????t xem: " + statistic.getViewCount().toString());
                System.out.println("Doanh thu: " + statistic.getTurnover().toString());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public Integer getTotalTurnover(ObservableList<Statistic> statisticsList) {
        Integer totalTurnover = 0;

        for (Statistic statistic : statisticsList) {
            totalTurnover += statistic.getTurnover();
        }

        return totalTurnover;
    }

    public void saveStatisticToExcelFile(ObservableList<Statistic> listStatistics, File fileStatistics, User currentUser, String purchasedDate, Integer totalTurnover) throws IOException {
        UserController userController = new UserController();
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet("Th???ng k??");

        int rownum = 0;
        Cell cell;
        Row row;

        XSSFCellStyle style = createStyleForTitle(workbook);

        row = sheet.createRow(rownum);
        cell = row.createCell(0, CellType.STRING);
        cell.setCellValue("R???P CHI???U PHIM ANH S??U");
        cell.setCellStyle(style);
        rownum += 2;

        row = sheet.createRow(rownum);
        cell = row.createCell(0, CellType.STRING);
        cell.setCellValue("Ch??? r???p: " + userController.getHeadUser().getUsers_name());
        cell.setCellStyle(style);
        rownum += 2;

        row = sheet.createRow(rownum);
        cell = row.createCell(0, CellType.STRING);
        cell.setCellValue("?????a ch???: 235 ???????ng L????ng Ng???c Quy???n, P.Ho??ng V??n Th???, TP.Th??i Nguy??n, T.Th??i Nguy??n");
        cell.setCellStyle(style);
        rownum += 2;

        row = sheet.createRow(rownum);
        cell = row.createCell(0, CellType.STRING);
        cell.setCellValue("Ng?????i l??m th???ng k??: " + currentUser.getUsers_name());
        cell.setCellStyle(style);
        rownum += 2;

        row = sheet.createRow(rownum);
        cell = row.createCell(0, CellType.STRING);
        cell.setCellValue("Ng??y l??m th???ng k??: " + dtf.format(LocalDate.now()));
        cell.setCellStyle(style);
        rownum += 2;

        String statisticType;
        if (purchasedDate.length() == 4)
            statisticType = " N??M ";
        else if (purchasedDate.length() == 7)
            statisticType = " TH??NG ";
        else if (purchasedDate.length() == 10)
            statisticType = " NG??Y ";
        else
            statisticType = "";

        row = sheet.createRow(rownum);
        cell = row.createCell(0, CellType.STRING);
        cell.setCellValue("TH???NG K?? DOANH S??? R???P CHI???U " + statisticType + purchasedDate);
        cell.setCellStyle(style);
        rownum += 2;

        row = sheet.createRow(rownum);
        cell = row.createCell(0, CellType.STRING);
        cell.setCellValue("T???ng doanh thu: " + totalTurnover.toString());
        cell.setCellStyle(style);
        rownum += 2;

        row = sheet.createRow(rownum);
        cell = row.createCell(0, CellType.STRING);
        cell.setCellValue("Phim");
        cell.setCellStyle(style);

        cell = row.createCell(1, CellType.STRING);
        cell.setCellValue("L?????t xem");
        cell.setCellStyle(style);

        cell = row.createCell(2, CellType.STRING);
        cell.setCellValue("Doanh thu");
        cell.setCellStyle(style);

        for (Statistic statistic : listStatistics) {
            rownum++;
            row = sheet.createRow(rownum);

            cell = row.createCell(0, CellType.STRING);
            cell.setCellValue(statistic.getMovies_name());

            cell = row.createCell(1, CellType.NUMERIC);
            cell.setCellValue(statistic.getViewCount());

            cell = row.createCell(2, CellType.NUMERIC);
            cell.setCellValue(statistic.getTurnover());
        }

        FileOutputStream outputStream = new FileOutputStream(fileStatistics);
        workbook.write(outputStream);
    }

    private XSSFCellStyle createStyleForTitle(XSSFWorkbook workbook) {
        XSSFFont font = workbook.createFont();
        font.setBold(true);
        XSSFCellStyle style =workbook.createCellStyle();
        style.setFont(font);
        return style;
    }
}
