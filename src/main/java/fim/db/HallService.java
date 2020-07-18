package fim.db;



import fim.model.Hall;
import fim.model.Ticket;

import java.util.List;

public interface HallService {

    Hall createHall(Hall hall);

    Hall loadHallById(int id);

    void removeHallById(int id);

    void removeHall(Hall hall);

    void deleteAllHalls();

    List<Hall> loadAllHalls();

    int getIdOfHall(Ticket ticket);
}
