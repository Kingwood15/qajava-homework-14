package ru.netology.domain;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TicketTest {

    Ticket ticket1 = new Ticket(1, 10000, "MSC", "NYC", 100);
    Ticket ticket2 = new Ticket(2, 11000, "SPB", "NZK", 300);
    Ticket ticket3 = new Ticket(3, 12000, "ABC", "DEF", 200);
    Ticket ticket4 = new Ticket(4, 7000, "SPB", "NZK", 200);
    Ticket ticket5 = new Ticket(5, 8000, "ABC", "DEF", 300);
    Ticket ticket6 = new Ticket(6, 9000, "ABC", "DEF", 100);
    Ticket ticket7 = new Ticket(7, 30000, "MSC", "NYC", 300);
    Ticket ticket8 = new Ticket(8, 20000, "SPB", "NZK", 100);
    Ticket ticket9 = new Ticket(9, 10500, "MSC", "NYC", 200);
    Ticket ticket10 = new Ticket(10, 10000, "ABC", "DEF", 450);

    TicketByRouteTimeAscComparator comparator = new TicketByRouteTimeAscComparator();

    @Test
    void ShouldAdd() {
        TicketManager manager = new TicketManager();
        manager.add(ticket1);
        manager.add(ticket2);
        manager.add(ticket3);
        manager.add(ticket4);
        manager.add(ticket5);
        manager.add(ticket6);
        manager.add(ticket7);
        manager.add(ticket8);
        manager.add(ticket9);
        manager.add(ticket10);

        Ticket ticket12 = new Ticket(12, 10000, "MSC", "NYC", 50);
        Ticket ticket13 = new Ticket(13, 11000, "SPB", "NZK", 350);
        Ticket ticket14 = new Ticket(14, 11000, "SPB", "NZK", 250);
        manager.add(ticket12);
        manager.add(ticket13);
        manager.add(ticket14);

        Ticket[] actual = manager.findAll("", "", comparator);
        Ticket[] expected = {ticket12, ticket1, ticket6, ticket8, ticket3, ticket4, ticket9, ticket14, ticket2, ticket5, ticket7, ticket13, ticket10};

        Assertions.assertArrayEquals(expected, actual);

        Ticket ticket11 = new Ticket(10, 10000, "ABC", "DEF", 450);

        //проверка исключения
        Assertions.assertThrows(AlreadyExistsException.class, () -> {
            manager.add(ticket11);
        });
    }

    @Test
    void ShouldSearchTicket() {
        TicketManager manager = new TicketManager();
        manager.add(ticket1);
        manager.add(ticket2);
        manager.add(ticket3);
        manager.add(ticket4);
        manager.add(ticket5);
        manager.add(ticket6);
        manager.add(ticket7);
        manager.add(ticket8);
        manager.add(ticket9);
        manager.add(ticket10);

        Ticket[] actual1 = manager.findAll("MSC", "NYC", comparator);
        Ticket[] expected1 = {ticket1, ticket9, ticket7};

        Assertions.assertArrayEquals(expected1, actual1);

        Ticket[] actual2 = manager.findAll("MSC", "DEF", comparator);
        Ticket[] expected2 = {};

        Assertions.assertArrayEquals(expected2, actual2);

        Ticket[] actual3 = manager.findAll("SPB", "", comparator);
        Ticket[] expected3 = {ticket8, ticket4, ticket2};

        Assertions.assertArrayEquals(expected3, actual3);
    }

    @Test
    void ShouldSearchBy() {
        TicketManager manager = new TicketManager();
        manager.add(ticket1);
        manager.add(ticket2);
        manager.add(ticket3);
        manager.add(ticket4);
        manager.add(ticket5);
        manager.add(ticket6);
        manager.add(ticket7);
        manager.add(ticket8);
        manager.add(ticket9);
        manager.add(ticket10);

        Ticket[] actual1 = manager.searchBy("ABC");
        Ticket[] expected1 = {ticket3, ticket5, ticket6, ticket10};

        Assertions.assertArrayEquals(expected1, actual1);

        Ticket[] actual2 = manager.searchBy("N");
        Ticket[] expected2 = {ticket1, ticket2, ticket4, ticket7, ticket8, ticket9};

        Assertions.assertArrayEquals(expected2, actual2);
    }

    @Test
    void ShouldRemoveById() {
        TicketManager manager = new TicketManager();

        manager.removeById(1);

        Ticket[] actual0 = manager.findAll("", "", comparator);
        Ticket[] expected0 = {};

        Assertions.assertArrayEquals(expected0, actual0);

        manager.add(ticket1);
        manager.add(ticket2);
        manager.add(ticket3);
        manager.add(ticket4);
        manager.add(ticket5);
        manager.add(ticket6);
        manager.add(ticket7);
        manager.add(ticket8);
        manager.add(ticket9);
        manager.add(ticket10);

        manager.removeById(1);

        Ticket[] actual1 = manager.findAll("", "", comparator);
        Ticket[] expected1 = {ticket6, ticket8, ticket3, ticket4, ticket9, ticket2, ticket5, ticket7, ticket10};

        Assertions.assertArrayEquals(expected1, actual1);

        //проверка исключения
        Assertions.assertThrows(NotFoundException.class, () -> {
            manager.removeById(11);
        });
    }
}
