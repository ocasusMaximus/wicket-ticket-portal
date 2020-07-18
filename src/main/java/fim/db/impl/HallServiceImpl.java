package fim.db.impl;


import fim.db.HallRepository;
import fim.db.HallService;
import fim.model.Hall;
import fim.model.Ticket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HallServiceImpl implements HallService {


    @Autowired
    private HallRepository hallRepository;

    @Override
    public Hall createHall(Hall hall) {
        return hallRepository.save(hall);
    }

    @Override
    public Hall loadHallById(int id) {
        return hallRepository.findById(id).get();
    }

    @Override
    public void removeHallById(int id) {
        hallRepository.deleteById(id);
    }

    @Override
    public void removeHall(Hall hall) {
        hallRepository.delete(hall);
    }

    @Override
    public void deleteAllHalls() {
        hallRepository.deleteAll();
    }

    @Override
    public List<Hall> loadAllHalls() {
        return hallRepository.findAll();
    }
    @Override
    public int getIdOfHall(Ticket ticket) {
        int idOfHall = 0;
        for(Hall hall :loadAllHalls()){
            if(hall.getName().equals(ticket.getHall())){
                idOfHall = hall.getId();
            }
        }
        return idOfHall;
    }
}
