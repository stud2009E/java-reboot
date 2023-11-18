package ru.sberbank.edu;

import org.dbunit.DataSourceBasedDBTestCase;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.h2.jdbcx.JdbcDataSource;
import org.junit.jupiter.api.Assertions;
import ru.sberbank.edu.model.Car;
import ru.sberbank.edu.repository.CarDbRepositoryImpl;
import ru.sberbank.edu.repository.CarRepository;
import ru.sberbank.edu.service.CarService;
import ru.sberbank.edu.service.CarServiceImpl;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.Optional;

public class CarServiceTest extends DataSourceBasedDBTestCase {

    private CarService carService;
    private CarRepository carRepository;

    public void testAddCar() throws SQLException {
        String id = "6";
        String model = "volvo";
        carService.addCar(id, model);

        Optional<Car> volvoOptional = carRepository.findById(id);
        if (volvoOptional.isEmpty()) {
            Assertions.fail();
        }
        Assertions.assertEquals(id, volvoOptional.get().getId());
        Assertions.assertEquals(model, volvoOptional.get().getModel());
        Assertions.assertEquals(carRepository.findAll().size(), 6);
    }


    public void testEditModel() throws SQLException {
        String id = "1";
        String model = "volvo";

        Optional<Car> bmwOptional = carRepository.findById(id);
        if (bmwOptional.isEmpty()) {
            Assertions.fail();
        }
        Assertions.assertNotEquals(bmwOptional.get().getModel(), model);

        carService.editModel(id, model);

        Optional<Car> volvoOptional = carRepository.findById(id);
        if (volvoOptional.isEmpty()) {
            Assertions.fail();
        }
        Assertions.assertEquals(id, volvoOptional.get().getId());
        Assertions.assertEquals(model, volvoOptional.get().getModel());
    }


    public void testDeleteCar() throws SQLException {
        String id = "1";

        Optional<Car> carOptional = carRepository.findById(id);
        Assertions.assertFalse(carOptional.isEmpty());

        Assertions.assertEquals(carRepository.findAll().size(), 5);

        carService.deleteCar(id);

        Assertions.assertEquals(carRepository.findAll().size(), 4);

        carOptional = carRepository.findById(id);
        Assertions.assertTrue(carOptional.isEmpty());
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        carRepository = new CarDbRepositoryImpl(getDataSource().getConnection());
        carService = new CarServiceImpl(carRepository);
    }

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
        carService = null;
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
