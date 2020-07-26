package fim.config;

import fim.model.Hall;
import fim.model.Ticket;
import org.apache.wicket.markup.repeater.data.IDataProvider;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;

import java.util.Iterator;
import java.util.List;

public class TicketDataProvider implements IDataProvider<Ticket> {

    private List<Ticket> tickets;

    public TicketDataProvider(List<Ticket> tickets) {
    this.tickets = tickets;
    }

    @Override
    public Iterator<? extends Ticket> iterator(long first, long count) {
        return tickets.iterator();
    }

    @Override
    public long size() {
        return tickets.size();
    }

    @Override
    public IModel<Ticket> model(Ticket object) {
        return new Model(object);
    }

    @Override
    public void detach() {

    }
}
