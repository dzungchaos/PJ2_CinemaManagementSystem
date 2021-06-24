package controller;

import entity.Movie;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import util.HibernateUtil;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Iterator;
import java.util.List;

public class MovieController {
    private static final SessionFactory factory = HibernateUtil.getSessionFactory();

    ObservableList<Movie> movies;
    ObservableList<Movie> unlockMovies;
    ObservableList<Movie> foundMovies;
    ObservableList<Movie> foundAndUnlockMovies;
    ObservableList<Movie> newMovies;

    public MovieController() {
        movies = FXCollections.observableArrayList();
        unlockMovies = FXCollections.observableArrayList();
        foundMovies = FXCollections.observableArrayList();
        foundAndUnlockMovies = FXCollections.observableArrayList();
        newMovies = FXCollections.observableArrayList();
        loadMovies();
        loadUnlockMovies();
    }

    public ObservableList<Movie> getNewMovies() {
        return newMovies;
    }

    public ObservableList<Movie> getMovies() {
        return movies;
    }

    public ObservableList<Movie> getUnlockMovies() {
        return unlockMovies;
    }

    public ObservableList<Movie> getListMovie(String movieNamePart) {
        foundMovies.clear();

        for (Movie movie : movies) {
            if (movie.getMovies_name().contains(movieNamePart)) {
                foundMovies.add(movie);
            }
        }

        return foundMovies;
    }

    public ObservableList<Movie> getListUnlockMovie(String movieNamePart) {
        foundMovies.clear();

        for (Movie movie : movies) {
            if (movie.getMovies_name().contains(movieNamePart) && movie.getMovies_isActive()) {
                foundMovies.add(movie);
            }
        }

        return foundMovies;
    }

    public Integer addMovie(String movies_avatarUrl,
                            String movies_name,
                            String movies_description,
                            String movies_director,
                            String movies_actors,
                            String movies_genres,
                            Integer movies_duration,
                            String movies_airDate,
                            String movies_showtimes) {

        Session session = factory.openSession();
        Transaction transaction = null;
        Integer movies_id = null;

        try {
            transaction = session.beginTransaction();
            Movie movie = new Movie(movies_avatarUrl,
                                    movies_name,
                                    movies_description,
                                    movies_director,
                                    movies_actors,
                                    movies_genres,
                                    movies_duration,
                                    movies_airDate,
                                    false,
                                    movies_showtimes,
                                    true);
            movies_id = (Integer) session.save(movie);
            transaction.commit();
            movies.add(movie);
        } catch (HibernateException e) {
            if (transaction != null)
                transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }

        return movies_id;
    }

    public void updateMovie(Movie selectedMovie,
                            String movies_avatarUrl,
                            String movies_name,
                            String movies_description,
                            String movies_director,
                            String movies_actors,
                            String movies_genres,
                            Integer movies_duration,
                            String movies_airDate,
                            String movies_showtimes) {

        selectedMovie.setMovies_avatarUrl(movies_avatarUrl);
        selectedMovie.setMovies_name(movies_name);
        selectedMovie.setMovies_description(movies_description);
        selectedMovie.setMovies_director(movies_director);
        selectedMovie.setMovies_actors(movies_actors);
        selectedMovie.setMovies_genres(movies_genres);
        selectedMovie.setMovies_duration(movies_duration);
        selectedMovie.setMovies_airDate(movies_airDate);
        selectedMovie.setMovies_showtimes(movies_showtimes);
        Session session = factory.openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.update(selectedMovie);
            transaction.commit();
        } catch (HibernateException e) {
            if (transaction != null)
                transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }

        movies.clear();
        loadMovies();
    }

    public Movie getMovie(Integer movies_id) {
        Movie gotMovie = null;

        for (Movie movie : movies)
            if (movies_id.equals(movie.getMovies_id()))
                gotMovie = movie;

        return gotMovie;
    }

    public void loadMovies() {
        Session session = factory.openSession();
        try {
            List list = session.createQuery("FROM Movie").list();
            for (Iterator iterator = list.iterator(); iterator.hasNext();) {
                Movie movie = (Movie) iterator.next();
                movies.add(movie);
            }
        } catch (HibernateException e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    public void listMovies() {
        int count = 0;
        Session session = factory.openSession();
        try {
            List list = session.createQuery("FROM Movie").list();
            for (Iterator iterator = list.iterator(); iterator.hasNext();) {
                count++;
                System.out.println(count);
                Movie movie = (Movie) iterator.next();
                System.out.println("ID: " + movie.getMovies_id());
                System.out.println("Avatar: " + movie.getMovies_avatarUrl());
                System.out.println("Name: " + movie.getMovies_name());
                System.out.println("Description: " + movie.getMovies_description());
                System.out.println("Director: " + movie.getMovies_director());
                System.out.println("Actors: " + movie.getMovies_actors());
                System.out.println("Genres: " + movie.getMovies_genres());
                System.out.println("Duration: " + movie.getMovies_duration() + " mins");
                System.out.println("AirDate: " + movie.getMovies_airDate());
                System.out.println("Adult Rated: " + movie.getMovies_adultRated());
                System.out.println("Showtimes: " + movie.getMovies_showtimes());
                System.out.println("IsActive: " + movie.getMovies_isActive());
                System.out.println();
            }
        } catch (HibernateException e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    public void lockMovie(Movie selectedMovie) {
        selectedMovie.setMovies_isActive(false);
        Session session = factory.openSession();
        Transaction transaction = null;

        try {
            transaction = session.beginTransaction();
            session.update(selectedMovie);
            transaction.commit();
        } catch (HibernateException e) {
            if (transaction != null)
                transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }

        movies.clear();
        loadMovies();
    }

    public void unlockMovie(Movie selectedMovie) {
        selectedMovie.setMovies_isActive(true);
        Session session = factory.openSession();
        Transaction transaction = null;

        try {
            transaction = session.beginTransaction();
            session.update(selectedMovie);
            transaction.commit();
        } catch (HibernateException e) {
            if (transaction != null)
                transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }

        movies.clear();
        loadMovies();
    }

    public void loadUnlockMovies() {
        for (Movie movie : movies) {
            if (movie.getMovies_isActive()) {
                unlockMovies.add(movie);
            }
        }
    }

    public void addNewMoviesToMoviesList(ObservableList<Movie> listNewMovies) {
        for (Movie movie : listNewMovies) {
            addMovie("",
                    movie.getMovies_name(),
                    movie.getMovies_description(),
                    movie.getMovies_director(),
                    movie.getMovies_actors(),
                    movie.getMovies_genres(),
                    movie.getMovies_duration(),
                    movie.getMovies_airDate(),
                    movie.getMovies_showtimes());
        }
    }

    public void loadMoviesFromExcelFile(File fileMovies) throws IOException {
        newMovies.clear();

        FileInputStream inputStream = new FileInputStream(fileMovies);

        XSSFWorkbook workbook = new XSSFWorkbook(inputStream);

        XSSFSheet sheet = workbook.getSheetAt(0);

        Iterator<Row> rowIterator = sheet.iterator();

        while (rowIterator.hasNext()) {
            Row row = rowIterator.next();

            if (row.getRowNum() == 0)
                continue;

            Iterator<Cell> cellIterator = row.cellIterator();

            Movie movie = new Movie();
            while (cellIterator.hasNext()) {

                Cell cell = cellIterator.next();
                Object cellValue = getCellValue(cell);

                if (cellValue == null || cellValue.toString().isEmpty())
                    continue;

                int columnIndex = cell.getColumnIndex();
                switch (columnIndex) {
                    case 0:
                        movie.setMovies_name((String) getCellValue(cell));
                        break;
                    case 1:
                        movie.setMovies_description((String) getCellValue(cell));
                        break;
                    case 2:
                        movie.setMovies_director((String) getCellValue(cell));
                        break;
                    case 3:
                        movie.setMovies_actors((String) getCellValue(cell));
                        break;
                    case 4:
                        movie.setMovies_genres((String) getCellValue(cell));
                        break;
                    case 5:
                        movie.setMovies_duration(BigDecimal.valueOf((double) cellValue).intValue());
                        break;
                    case 6:
                        movie.setMovies_airDate((String) getCellValue(cell));
                        break;
                    case 7:
                        movie.setMovies_showtimes((String) getCellValue(cell));
                        break;
                    default:
                        break;
                }
            }
            newMovies.add(movie);
        }

        workbook.close();
        inputStream.close();
    }

    private Object getCellValue(Cell cell) {
        CellType cellType = cell.getCellType();
        Object cellValue = null;

        switch (cellType) {
            case BOOLEAN:
                cellValue = cell.getBooleanCellValue();
                break;
            case FORMULA:
                Workbook workbook = cell.getSheet().getWorkbook();
                FormulaEvaluator evaluator = workbook.getCreationHelper().createFormulaEvaluator();
                cellValue = evaluator.evaluate(cell).getNumberValue();
                break;
            case NUMERIC:
                cellValue = cell.getNumericCellValue();
                break;
            case STRING:
                cellValue = cell.getStringCellValue();
                break;
            default:
                break;
        }

        return cellValue;
    }

    public void listNewMovies(ObservableList<Movie> listNewMovies) {
        for (Movie movie : listNewMovies) {
            System.out.println(movie.toString());
        }
    }

    public void clearData() {
        movies.clear();
    }
}
