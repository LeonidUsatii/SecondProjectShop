package de.ait.services.OrdersService;

import de.ait.models.Book;
import de.ait.models.CashWarrant;
import de.ait.models.DeliveryOffGoods;
import de.ait.models.Film;
import de.ait.models.Music;
import de.ait.models.Order;
import de.ait.models.Product;
import de.ait.models.User;
import de.ait.repositories.CashWarrant.CashWarrantRepository;
import de.ait.repositories.DeliveryOffGoods.DeliveryOffGoodsRepository;
import de.ait.repositories.books.BooksRepository;

import de.ait.repositories.films.FilmsRepository;
import de.ait.repositories.musics.MusicsRepository;
import de.ait.repositories.orders.OrderRepository;
import de.ait.repositories.products.ProductsRepository;
import de.ait.repositories.users.UsersRepository;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public class OrdersServiceImpl implements OrdersService {
    private final UsersRepository usersRepository;
    private final ProductsRepository productsRepository;
    private final OrderRepository ordersRepository;
    private final DeliveryOffGoodsRepository deliveryOffGoodsRepository;
    private final CashWarrantRepository cashWarrantRepository;
    private final BooksRepository booksRepository;
    private final MusicsRepository musicsRepository;
    private final FilmsRepository filmsRepository;
    public OrdersServiceImpl(UsersRepository usersRepository, ProductsRepository productsRepository,
                             OrderRepository ordersRepository,
                             DeliveryOffGoodsRepository deliveryOffGoodsRepository,
                             CashWarrantRepository cashWarrantRepository, BooksRepository booksRepository,
                             MusicsRepository musicsRepository, FilmsRepository filmsRepository) {
        this.usersRepository = usersRepository;
        this.productsRepository = productsRepository;
        this.ordersRepository = ordersRepository;
        this.deliveryOffGoodsRepository = deliveryOffGoodsRepository;
        this.cashWarrantRepository = cashWarrantRepository;
        this.booksRepository = booksRepository;
        this.musicsRepository = musicsRepository;
        this.filmsRepository = filmsRepository;
    }
    @Override
    public String makeOrder(String email, String productTitle) {
        User user = usersRepository.findByEmail(email);

        if (user == null) {
            throw new IllegalArgumentException("Пользователь не найден");
        }

        Product product = productsRepository.findByTitle(productTitle);
        Book book = booksRepository.findByTitle(productTitle);
        Music music = musicsRepository.findByTitle(productTitle);
        Film film = filmsRepository.findByTitle(productTitle);

        Order order = new Order(
                UUID.randomUUID().toString(),
                LocalDateTime.now(),
                product.getId(),
                user.getId()
        );

        CashWarrant cashWarrant = new CashWarrant(
                UUID.randomUUID().toString(),
                LocalDateTime.now(),
                user.getId(),
                product.getPrice());

        ordersRepository.save(order);
        cashWarrantRepository.save(cashWarrant);

        productsRepository.delete(product);

        if(book != null) {
            booksRepository.delete(book);
        }
        if(music != null) {
            musicsRepository.delete(music);
        }
        if(film != null) {
            filmsRepository.delete(film);
        }

        return "Квитанция № " + order.getId() + ", заказ был сделан на "
                + productTitle + " в " + order.getDateTime();
    }

    @Override
    public String makeOrder(String email, String productTitle, String address) {
        User user = usersRepository.findByEmail(email);

        if (user == null) {
            throw new IllegalArgumentException("Пользователь не найден");
        }

        Product product = productsRepository.findByTitle(productTitle);
        Book book = booksRepository.findByTitle(productTitle);
        Music music = musicsRepository.findByTitle(productTitle);
        Film film = filmsRepository.findByTitle(productTitle);



        Order order = new Order(
                UUID.randomUUID().toString(),
                LocalDateTime.now(),
                product.getId(),
                user.getId()
        );

        CashWarrant cashWarrant = new CashWarrant(
                UUID.randomUUID().toString(),
                LocalDateTime.now(),
                user.getId(),
                product.getPrice());

        DeliveryOffGoods deliveryOffGoods = new DeliveryOffGoods(
                UUID.randomUUID().toString(),
                address, product.getId(),
                user.getId(),
                LocalDateTime.now().plusDays(3).toString()
        );

        ordersRepository.save(order);
        cashWarrantRepository.save(cashWarrant);
        deliveryOffGoodsRepository.save(deliveryOffGoods);

        productsRepository.delete(product);

        if(book != null) {
            booksRepository.delete(book);
        }
        if(music != null) {
            musicsRepository.delete(music);
        }
        if(film != null) {
            filmsRepository.delete(film);
        }

        return "Квитанция № " + order.getId() + ", заказ был сделан на "
                + productTitle + " в " + order.getDateTime()  + " дата доставки "
                + LocalDateTime.now().plusDays(3);
    }
    @Override
    public List<Order> getOrder() {
        return ordersRepository.findAll();
    }

}
