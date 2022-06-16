package ru.netology.domain;

import java.util.Comparator;

public class TicketByRouteTimeAscComparator implements Comparator<Ticket> {
    public int compare(Ticket o1, Ticket o2) {
        return o1.getRouteTime() - o2.getRouteTime();
    }
}
