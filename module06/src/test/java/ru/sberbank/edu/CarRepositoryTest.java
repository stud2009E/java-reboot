package ru.sberbank.edu;

import org.dbunit.DataSourceBasedDBTestCase;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.h2.jdbcx.JdbcDataSource;
import org.junit.jupiter.api.Assertions;
import ru.sberbank.edu.model.Car;
import ru.sberbank.edu.repository.CarDbRepositoryImpl;
import ru.sberbank.edu.repository.CarRepository;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

/**
 * Unit test for simple App.
 */
public class CarRepositoryTest extends DataSourceBasedDBTestCase {

    private CarRepository carRepository;

    public void testInitialDb() {
        Assertions.assertEquals(carRepository.findAll().size(), 5);
    }


    public void testUpdateCar() throws SQLException {
        Car volvo = new Car("5", "volvo");
        Optional<Car> carId5Optional = carRepository.findById("5");

        if (carId5Optional.isEmpty()) {
            Assertions.fail();
        }
        Assertions.assertNotEquals(volvo, carId5Optional.get());

        carRepository.createOrUpdate(volvo);
        Optional<Car> carId5OptionalAfterUpdate = carRepository.findById("5");

        if (carId5OptionalAfterUpdate.isEmpty()) {
            Assertions.fail();
        }
        Assertions.assertEquals(volvo, carId5OptionalAfterUpdate.get());
    }

    public void testCreateCar() throws SQLException {
        Car volvo = new Car("6", "volvo");
        Optional<Car> carId5Optional = carRepository.findById("6");

        Assertions.assertTrue(carId5Optional.isEmpty());
        Assertions.assertEquals(carRepository.findAll().size(), 5);

        carRepository.createOrUpdate(volvo);

        Optional<Car> volvoOptional = carRepository.findById("6");
        if (volvoOptional.isEmpty()) {
            Assertions.fail();
        }
        Assertions.assertEquals(volvo, volvoOptional.get());
        Assertions.assertEquals(carRepository.findAll().size(), 6);
    }

    public void testCreateAll() throws SQLException {
        Assertions.assertEquals(carRepository.findAll().size(), 5);

        Car ford = new Car("6", "ford");
        Car nissan = new Car("7", "nissan");
        List<Car> newCars = new ArrayList<>();
        newCars.add(ford);
        newCars.add(nissan);

        carRepository.createAll(newCars);
        Assertions.assertEquals(carRepository.findAll().size(), 7);

        Optional<Car> fordOptional = carRepository.findById(ford.getId());
        if (fordOptional.isEmpty()) {
            Assertions.fail();
        }
        Assertions.assertEquals(ford, fordOptional.get());

        Optional<Car> nissanOptional = carRepository.findById(nissan.getId());
        if (nissanOptional.isEmpty()) {
            Assertions.fail();
        }
        Assertions.assertEquals(nissan, nissanOptional.get());
    }

    public void testDeleteAll(){
        Assertions.assertEquals(carRepository.findAll().size(), 5);
        carRepository.deleteAll();
        Assertions.assertEquals(carRepository.findAll().size(), 0);
    }

    public void testDeleteById() throws SQLException {
        Optional<Car> carOptional = carRepository.findById("1");
        Assertions.assertFalse(carOptional.isEmpty());

        Assertions.assertEquals(carRepository.findAll().size(), 5);

        boolean isDeleted = carRepository.deleteById("1");
        Assertions.assertTrue(isDeleted);

        Assertions.assertEquals(carRepository.findAll().size(), 4);

        carOptional = carRepository.findById("1");
        Assertions.assertTrue(carOptional.isEmpty());
    }


    public void testFindByModel(){
        Set<Car> cars = carRepository.findByModel("audi");
        Assertions.assertEquals(1, cars.size());

        cars = carRepository.findByModel("bmw");
        Assertions.assertEquals(1, cars.size());

        cars = carRepository.findByModel("not existed");
        Assertions.assertEquals(0, cars.size());
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        carRepository = new CarDbRepositoryImpl(getDataSource().getConnection());
    }

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
        carRepository = null;
    }

    @Override
    protected IDataSet getDataSet() throws Exception {
        return new FlatXmlDataSetBuilder().build(getClass().getClassLoader().getResourceAsStream("dataset.xml"));
    }

    @Override
    protected DataSource getDataSource() {
        JdbcDataSource dataSource = new JdbcDataSource();
        dataSource.setURL("jdbc:h2:mem:default;MODE=LEGACY;DB_CLOSE_DELAY=-1;init=runscript from 'classpath:schema.sql'");

        return dataSource;
    }

}
