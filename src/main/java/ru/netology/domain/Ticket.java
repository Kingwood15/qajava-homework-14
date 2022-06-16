package ru.netology.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Ticket implements Comparable<Ticket> {
    protected int id;
    protected int cost;
    protected String From;
    protected String To;
    protected int routeTime;

    //метод сортировки
    @Override
    public int compareTo(Ticket otherTicket) {
        if (this.cost < otherTicket.cost) {
            return -1;
        }
        if (this.cost > otherTicket.cost) {
            return 1;
        }
        return 0;
    }

    //подходит ли билет пункту отправления
    public boolean matchesFrom(String search) {
        if (this.getFrom().contains(search)) {
            return true;
        } else {
            return false;
        }
    }

    //подходит ли билет пункту назначения
    public boolean matchesTo(String search) {
        if (this.getTo().contains(search)) {
            return true;
        } else {
            return false;
        }
    }
}
