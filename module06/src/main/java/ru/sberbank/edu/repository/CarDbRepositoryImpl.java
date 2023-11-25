package ru.sberbank.edu.repository;


import ru.sberbank.edu.model.Car;

import java.sql.*;
import java.util.Collection;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.jar.JarEntry;

public class CarDbRepositoryImpl implements CarRepository {
    private final Connection connection;
    private static final String CREATE_CAR_SQL = "INSERT INTO car (id, model) VALUES (?,?)";
    private static final String UPDATE_CAR_SQL = "UPDATE car SET model = ? WHERE id = ?";
    private static final String SELECT_CAR_BY_ID = "SELECT * FROM car WHERE id = ?";
    private static final String SELECT_CAR_BY_MODEL = "SELECT * FROM car WHERE model = ?";
    private static final String DELETE_CAR_BY_ID = "DELETE FROM car WHERE id = ?";
    private static final String SELECT_ALL_CARS = "SELECT * FROM car";
    private static final String DELETE_ALL_CAR = "DELETE FROM car";


    private final PreparedStatement createPreStmt;
    private final PreparedStatement updatePreStmt;
    private final PreparedStatement findByIdPreStmt;
    private final PreparedStatement findByModelPreStmt;
    private final PreparedStatement deleteByIdPreStmt;
    private final Statement selectAllPreStmt;
    private final Statement deleteAllStmt;

    public CarDbRepositoryImpl(Connection connection) throws SQLException {
        this.connection = connection;
        this.createPreStmt = connection.prepareStatement(CREATE_CAR_SQL);
        this.updatePreStmt = connection.prepareStatement(UPDATE_CAR_SQL);
        this.findByIdPreStmt = connection.prepareStatement(SELECT_CAR_BY_ID);
        this.findByModelPreStmt = connection.prepareStatement(SELECT_CAR_BY_MODEL);
        this.deleteByIdPreStmt = connection.prepareStatement(DELETE_CAR_BY_ID);
        this.selectAllPreStmt = connection.createStatement();
        this.deleteAllStmt = connection.createStatement();
    }

    @Override
    public Car createOrUpdate(Car car) throws SQLException {
        Optional<Car> optCar = findById(car.getId());
        if (optCar.isEmpty()) {
            createPreStmt.setString(1, car.getId());
            createPreStmt.setString(2, car.getModel());
            createPreStmt.executeUpdate();
        } else {
            updatePreStmt.setString(1, car.getModel());
            updatePreStmt.setString(2, car.getId());
            updatePreStmt.executeUpdate();
        }
        return car;
    }

    @Override
    public Set<Car> createAll(Collection<Car> cars) {
        Set<Car> createdCars = new HashSet<>();

        cars.forEach(car -> {
            try {
                this.createPreStmt.setString(1, car.getId());
                this.createPreStmt.setString(2, car.getModel());
                int res = this.createPreStmt.executeUpdate();
                if (res == 1){
                    createdCars.add(car);
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        });

        return createdCars;
    }

    @Override
    public Set<Car> findAll() {
        Set<Car> cars = new HashSet<>();

        try {
            ResultSet allCars = selectAllPreStmt.executeQuery(SELECT_ALL_CARS);
            while (allCars.next()){
                String id = allCars.getString("id");
                String model = allCars.getString("model");
                cars.add(new Car(id, model)) ;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return cars;
    }

    @Override
    public Optional<Car> findById(String id) throws SQLException {
        // validation
        int rowsCount = countRowsById(id);
        if (rowsCount > 1) {
            throw new RuntimeException("Car with id = " + id + " was found many times (" + rowsCount + ").");
        } else if (rowsCount == 0) {
            return Optional.empty();
        }

        findByIdPreStmt.setString(1, id);
        ResultSet resultSet = findByIdPreStmt.executeQuery();

        resultSet.next();
        Car car = new Car(resultSet.getString(1), resultSet.getString(2));
        return Optional.of(car);
    }

    @Override
    public Boolean deleteById(String id) {
        boolean isDeleted;

        try {
            deleteByIdPreStmt.setString(1, id);
            isDeleted = deleteByIdPreStmt.executeUpdate() == 1;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return isDeleted;
    }

    @Override
    public Boolean deleteAll() {
        boolean isDeleteAll;
        try {
             isDeleteAll = deleteAllStmt.executeUpdate(DELETE_ALL_CAR) > 1;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return isDeleteAll;
    }

    private int countRowsById(String id) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement("SELECT COUNT(*) FROM car where id = ?");
        preparedStatement.setString(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();
        int rowCount = 0;
        while (resultSet.next()) {
            rowCount = resultSet.getInt(1);
        }
        return rowCount;
    }

    @Override
    public Set<Car> findByModel(String model) {
        Set<Car> cars = new HashSet<>();

        try {
            findByModelPreStmt.setString(1, model);
            ResultSet rs = findByModelPreStmt.executeQuery();
            while (rs.next()){
                String id = rs.getString("id");
                String model1 = rs.getString("model");
                cars.add(new Car(id, model1));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return cars;
    }
}
