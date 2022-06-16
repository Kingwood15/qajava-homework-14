package ru.netology.domain;

public class TicketRepository {
    private Ticket[] repository = new Ticket[0];

    // поиск товара по id
    public Ticket findById(int searchId) {
        for (int i = 0; i < repository.length; i++) {
            if (repository[i].getId() == searchId) {
                return repository[i];
            }
        }
        return null;
    }

    //сохранить/добавить объект
    public void save(Ticket newTicket) {
        if (repository.length != 0) {
            if (findById(newTicket.getId()) != null) {
                throw new AlreadyExistsException("Element with id: " + newTicket + " already exist");
            }
        }
        int length = repository.length + 1;
        Ticket[] tmp = new Ticket[length];

        for (int i = 0; i < repository.length; i++) {
            tmp[i] = repository[i];
        }

        int lastIndex = tmp.length - 1;
        tmp[lastIndex] = newTicket;

        repository = tmp;
    }

    //вывод всех объектов
    public Ticket[] findAll() {
        return repository;
    }

    //удалить объект по id
    public void removeById(int removeId) {
        if (repository.length != 0) {
            if (findById(removeId) == null) {
                throw new NotFoundException("Element with id: " + removeId + " not found");
            }
            Ticket[] tmp = new Ticket[repository.length - 1];
            int copyToIndex = 0;

            for (int i = 0; i < repository.length; i++) {
                if (repository[i].getId() != removeId) {
                    tmp[copyToIndex] = repository[i];
                    copyToIndex++;
                }
            }

            repository = tmp;
        } else {
            System.out.println("No products to remove");
        }
    }
}
