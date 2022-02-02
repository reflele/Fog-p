package model;


import business.entities.User;
import business.exceptions.UserException;
import business.persistence.Database;
import business.persistence.UserMapper;
import business.services.RequestFacade;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.sql.SQLException;
import java.sql.Statement;

import static org.junit.jupiter.api.Assertions.*;

public class CalculateTest {

    private final static String DATABASE = "mydb";  // Change this to your own database
    private final static String TESTDATABASE = "test_" + DATABASE;
    private final static String USER = "root";
    private final static String PASSWORD = "root";
    private final static String URL = "jdbc:mysql://localhost:3306/" + TESTDATABASE + "?serverTimezone=CET&useSSL=false";

    private static Database database;
    private static UserMapper userMapper;
    private Calculate calculate = new Calculate(database);
    RequestFacade requestFacade = new RequestFacade(database);
    CalculateBom calculateBom = new CalculateBom(database);

    @BeforeAll
    public static void setUpClass() {
        try {
            database = new Database(USER, PASSWORD, URL);
            userMapper = new UserMapper(database);
        } catch (ClassNotFoundException e) {   // kan ikke finde driveren i database klassen
            fail("Database connection failed. Missing jdbc driver");
        }
    }

    @BeforeEach
    public void setUp() {

        // reset test database
        try (Statement stmt = database.connect().createStatement()) {
            stmt.execute("drop table if exists users");
            stmt.execute("create table " + TESTDATABASE + ".users LIKE " + DATABASE + ".users;");
            stmt.execute(
                    "insert into users values " +
                            "(1,'jens@somewhere.com','jensen','customer','Blomstervejen 22','2860','22568442','Hans','Andreawsen'), " +
                            "(2,'ken@somewhere.com','kensen','customer','Blomstervejen 223','2850','22566563','Hands','Andreassen'), " +
                            "(3,'robin@somewhere.com','batman','employee','Blomstervejen 23','2860','22566566','Hansa','Andrexasen')");
            stmt.execute("DROP TABLE IF EXISTS `test_mydb`.`carport_request`;");
            stmt.execute("CREATE TABLE `carport_request` (\n" +
                    "  `request_id` int NOT NULL AUTO_INCREMENT,\n" +
                    "  `user_id` int NOT NULL,\n" +
                    "  `length` int DEFAULT NULL,\n" +
                    "  `height` int DEFAULT '210',\n" +
                    "  `width` int DEFAULT NULL,\n" +
                    "  `rooftype` varchar(45) DEFAULT 'lige',\n" +
                    "  `treetype` int DEFAULT '0',\n" +
                    "  `cladding` int DEFAULT '0',\n" +
                    "  `number_sides` int DEFAULT '0',\n" +
                    "  `lacquering` int DEFAULT '0',\n" +
                    "  `date_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,\n" +
                    "  `order_status` int DEFAULT '0',\n" +
                    "  `price` double DEFAULT '0',\n" +
                    "  `description` tinytext,\n" +
                    "  PRIMARY KEY (`request_id`),\n" +
                    "  UNIQUE KEY `forespørgsel_id_UNIQUE` (`request_id`)\n" +
                    ") ENGINE=InnoDB AUTO_INCREMENT=01 DEFAULT CHARSET=utf8mb3;");
        } catch (SQLException ex) {
            System.out.println("Could not open connection to database: " + ex.getMessage());
        }
    }

    @Test
    @DisplayName("Checks if correct items are extracted from db and in the correct amount")
    public void raftersTest01() throws UserException {
        double carportLength = 400;
        double carportWidth = 500;
        Assumptions.assumeTrue(carportLength >= 200 && carportLength <= 600);
        Assumptions.assumeTrue(carportWidth >= 220 && carportWidth <= 780);
        calculate.rafters(carportLength, carportWidth);

        assertAll(() -> {
            assertEquals(114, calculate.getBomMaterials().get(0).materialId); //tests if correct rafter is used for length/width
            assertEquals(997, calculate.getBomMaterials().get(1).materialId); //tests if correct screw is used for length/width
            assertEquals(998, calculate.getBomMaterials().get(2).materialId); //tests if correct screw is used for length/width
            assertEquals(10, calculate.getBomMaterials().get(0).count);
            assertEquals(10, calculate.getBomMaterials().get(1).count);
            assertEquals(10, calculate.getBomMaterials().get(2).count);
        });
    }

    @Test
    @DisplayName("Checks if correct items are extracted from db and in the correct amount for smallest available carport")
    public void raftersTest02() throws UserException {
        double carportLength = 200;
        double carportWidth = 220;
        Assumptions.assumeTrue(carportLength >= 200 && carportLength <= 600);
        Assumptions.assumeTrue(carportWidth >= 220 && carportWidth <= 780);
        calculate.rafters(carportLength, carportWidth);

        assertAll(() -> {
            assertEquals(112, calculate.getBomMaterials().get(0).materialId); //tests if correct rafter is used for length/width
            assertEquals(997, calculate.getBomMaterials().get(1).materialId); //tests if correct screw is used for length/width
            assertEquals(998, calculate.getBomMaterials().get(2).materialId); //tests if correct screw is used for length/width
            assertEquals(5, calculate.getBomMaterials().get(0).count);
            assertEquals(5, calculate.getBomMaterials().get(1).count);
            assertEquals(5, calculate.getBomMaterials().get(2).count);
        });

    }

    @Test
    @DisplayName("Checks if correct items are extracted from db and in the correct amount for largest available carport")
    public void raftersTest03() throws UserException {
        double carportLength = 600;
        double carportWidth = 780;
        Assumptions.assumeTrue(carportLength >= 200 && carportLength <= 600);
        Assumptions.assumeTrue(carportWidth >= 220 && carportWidth <= 780);
        calculate.rafters(carportLength, carportWidth);

        assertAll(() -> {
            assertEquals(117, calculate.getBomMaterials().get(0).materialId); //tests if correct rafter is used for length/width
            assertEquals(997, calculate.getBomMaterials().get(1).materialId); //tests if correct screw is used for length/width
            assertEquals(998, calculate.getBomMaterials().get(2).materialId); //tests if correct screw is used for length/width
            assertEquals(16, calculate.getBomMaterials().get(0).count);
            assertEquals(16, calculate.getBomMaterials().get(1).count);
            assertEquals(16, calculate.getBomMaterials().get(2).count);
        });

    }


    @Test
    @DisplayName("tests if purchasePrice scales with carport size")
    public void allSizes01() throws UserException {
        int carportLength = 200;
        int carportWidth;

        for (int i = 0; i < 11; i++) {
            carportWidth = 220;
            for (int j = 0; j < 15; j++) {
                requestFacade.createRequest(1, carportLength, carportWidth, "lige", "carportdescription");
                carportWidth = carportWidth + 40;
            }
            carportLength = carportLength + 40;
        }
//        assertTrue(calculateBom.bomPrice(10) > calculateBom.bomPrice(1));

        System.out.println(calculateBom.bomPrice(10));
//        System.out.println(calculateBom.bomPrice(10));

//        for (int i = 1; i < 16; i++) {
//            }
    }
}


