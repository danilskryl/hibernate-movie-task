package org.danilskryl.javarush;

import org.danilskryl.javarush.dao.*;
import org.danilskryl.javarush.entity.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.Year;
import java.util.Properties;

public class Main {
    private final SessionFactory sessionFactory;
    private final ActorDAO actorDAO;
    private final AddressDAO addressDAO;
    private final CategoryDAO categoryDAO;
    private final CityDAO cityDAO;
    private final CountryDAO countryDAO;
    private final CustomerDAO customerDAO;
    private final FilmDAO filmDAO;
    private final InventoryDAO inventoryDAO;
    private final LanguageDAO languageDAO;
    private final PaymentDAO paymentDAO;
    private final RentalDAO rentalDAO;
    private final StaffDAO staffDAO;
    private final StoreDAO storeDAO;
    private final FilmTextDAO filmTextDAO;

    public Main() {
        Properties properties = new Properties();
        properties.put(Environment.USER, "root");
        properties.put(Environment.PASS, "root");
        properties.put(Environment.URL, "jdbc:p6spy:mysql://localhost:3306/movie");
        properties.put(Environment.DRIVER, "com.p6spy.engine.spy.P6SpyDriver");
        properties.put(Environment.DIALECT, "org.hibernate.dialect.MySQL8Dialect");
        properties.put(Environment.SHOW_SQL, "true");
        properties.put(Environment.FORMAT_SQL, "true");
        properties.put(Environment.HBM2DDL_AUTO, "validate");
        properties.put(Environment.CURRENT_SESSION_CONTEXT_CLASS, "thread");

        sessionFactory = new Configuration()
                .setProperties(properties)
                .addAnnotatedClass(Actor.class)
                .addAnnotatedClass(Address.class)
                .addAnnotatedClass(Category.class)
                .addAnnotatedClass(City.class)
                .addAnnotatedClass(Country.class)
                .addAnnotatedClass(Customer.class)
                .addAnnotatedClass(Film.class)
                .addAnnotatedClass(FilmText.class)
                .addAnnotatedClass(Inventory.class)
                .addAnnotatedClass(Language.class)
                .addAnnotatedClass(Payment.class)
                .addAnnotatedClass(Rental.class)
                .addAnnotatedClass(Staff.class)
                .addAnnotatedClass(Store.class)
                .buildSessionFactory();

        actorDAO = new ActorDAO(sessionFactory);
        addressDAO = new AddressDAO(sessionFactory);
        categoryDAO = new CategoryDAO(sessionFactory);
        cityDAO = new CityDAO(sessionFactory);
        countryDAO = new CountryDAO(sessionFactory);
        customerDAO = new CustomerDAO(sessionFactory);
        filmDAO = new FilmDAO(sessionFactory);
        inventoryDAO = new InventoryDAO(sessionFactory);
        languageDAO = new LanguageDAO(sessionFactory);
        paymentDAO = new PaymentDAO(sessionFactory);
        rentalDAO = new RentalDAO(sessionFactory);
        staffDAO = new StaffDAO(sessionFactory);
        storeDAO = new StoreDAO(sessionFactory);
        filmTextDAO = new FilmTextDAO(sessionFactory);
    }

    public Customer createNewCustomer() {
        try (Session session = sessionFactory.getCurrentSession()) {
            session.beginTransaction();

            Store store = storeDAO.getAll().get(0);
            City city = cityDAO.getCityByName("Bern");

            Address address = new Address();
            address.setAddress("Lake Street, 23");
            address.setAddress2("Bundestag, 83");
            address.setCity(city);
            address.setDistrict("Central");
            address.setPhone("3-100-400-312");
            address.setPostalCode("11948");
            addressDAO.save(address);

            Customer customer = new Customer();
            customer.setFirstName("Danil");
            customer.setLastName("Skryl");
            customer.setEmail("skryl@gmail.com");
            customer.setStore(store);
            customer.setAddress(address);
            customer.setActive(true);
            customerDAO.save(customer);

            session.getTransaction().commit();

            return customer;
        }
    }

    public void rentInventory(Customer customer) {
        try (Session session = sessionFactory.getCurrentSession()) {
            Transaction transaction = session.beginTransaction();

            Film film = filmDAO.getAvailableFilm();
            Store store = storeDAO.getAnyStore();

            Inventory inventory = new Inventory();
            inventory.setFilm(film);
            inventory.setStore(store);
            inventoryDAO.save(inventory);

            Rental rental = new Rental();
            rental.setInventory(inventory);
            rental.setStaff(store.getManagerId());
            rental.setCustomer(customer);
            rental.setRentalDate(LocalDateTime.now());
            rentalDAO.save(rental);

            Payment payment = new Payment();
            payment.setStaff(store.getManagerId());
            payment.setCustomer(customer);
            payment.setRental(rental);
            payment.setPaymentDate(LocalDateTime.now());
            payment.setAmount(BigDecimal.valueOf(200));
            paymentDAO.save(payment);

            transaction.commit();
        }
    }

    public void returnRental() {
        try (Session session = sessionFactory.getCurrentSession()) {
            session.beginTransaction();

            Rental rental = rentalDAO.getUnreternedRental();
            rental.setReturnDate(LocalDateTime.now());
            rentalDAO.save(rental);

            session.getTransaction().commit();
        }
    }

    public void makeNewFilmAndAddInventory() {
        try (Session session = sessionFactory.getCurrentSession()) {
            session.beginTransaction();

            Film film = new Film();
            film.setDescription("Test film description");
            film.setLength((short) 14000);
            film.setTitle("Test film title");
            film.setLanguage(languageDAO.getAnyLanguage());
            film.setRating(Rating.R);
            film.setRentalDuration((byte) 4);
            film.setRentalRate(BigDecimal.valueOf(4.55));
            film.setYear(Year.now());
            film.setReplacementCost(BigDecimal.valueOf(24.99));
            filmDAO.save(film);

            FilmText filmText = new FilmText();
            filmText.setFilm(film);
            filmText.setTitle("Test film title 2");
            filmText.setDescription("Test film description");
            filmTextDAO.save(filmText);

            Inventory inventory = new Inventory();
            inventory.setFilm(film);
            inventory.setStore(storeDAO.getAnyStore());
            inventoryDAO.save(inventory);

            session.getTransaction().commit();
        }
    }

    public static void main(String[] args) {
        Main filmDao = new Main();

        filmDao.makeNewFilmAndAddInventory();
    }
}
