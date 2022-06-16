package ru.netology.domain;

import java.lang.reflect.Array;
import java.util.Arrays;

public class TicketManager {
    private TicketRepository repository = new TicketRepository();

    public void add(Ticket newTicket) {
        repository.save(newTicket);
    }

    //метод поиска по месту вылета или прилёта
    public Ticket[] searchBy(String text) {
        Ticket[] result = new Ticket[0];
        for (Ticket product : repository.findAll()) {
            if (matches(product, text, "") || matches(product, "", text)) {

                int length = result.length + 1;
                Ticket[] tmp = new Ticket[length];

                for (int i = 0; i < result.length; i++) {
                    tmp[i] = result[i];
                }

                int lastIndex = tmp.length - 1;
                tmp[lastIndex] = product;

                result = tmp;
            }
        }
        return result;
    }

    // метод определения соответствия билета направлению
    public boolean matches(Ticket ticket, String searchFrom, String searchTo) {
        if (ticket.matchesFrom(searchFrom) && ticket.matchesTo(searchTo)) {
            return true;
        } else {
            return false;
        }
    }

    //метод вывода билетов по направлениям
    public Ticket[] findAll(String from, String to, TicketByRouteTimeAscComparator comparator) {
        Ticket[] result = new Ticket[0];
        for (Ticket ticket : repository.findAll()) {
            if (matches(ticket, from, to)) {
                int length = result.length + 1;
                Ticket[] tmp = new Ticket[length];
                for (int i = 0; i < result.length; i++) {
                    tmp[i] = result[i];
                }
                int lastIndex = tmp.length - 1;
                tmp[lastIndex] = ticket;
                result = tmp;
            }
        }
        Arrays.sort(result,comparator);  //вызов сортировки
        return result;
    }

    public void removeById(int removeId) {
        repository.removeById(removeId);
    }
}
