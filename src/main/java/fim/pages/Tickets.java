package fim.pages;

import com.giffing.wicket.spring.boot.context.scan.WicketHomePage;
import fim.config.TicketDataProvider;
import fim.db.HallService;
import fim.db.TicketService;
import fim.model.Hall;
import fim.model.Ticket;
import org.apache.wicket.Session;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.markup.repeater.data.DataView;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.omg.CORBA.Any;
import org.wicketstuff.annotation.mount.MountPath;

import java.util.Locale;


@WicketHomePage
@MountPath("")
public class Tickets extends WebPage {

    @SpringBean
    private TicketService ticketService;
    @SpringBean
    private HallService hallService;


    public int ticketID;


    public Tickets() {

        Session.get().setLocale(new Locale("cs"));

        DataView<Ticket> dataView = new DataView<Ticket>("listTickets", new TicketDataProvider(ticketService.loadAllTickets())) {
            @Override
            protected void populateItem(Item<Ticket> item) {
                item.add(new Label("movie", item.getModelObject().getMovie()));
                item.add(new Label("language", item.getModelObject().getLanguage()));
                item.add(new Label("numberOfSeats", item.getModelObject().getNumberOfSeats()));
                item.add(new Label("hall", item.getModelObject().getHall()));

                Ticket ticket = ticketService.loadTicketById(item.getModelObject().getId());


                item.add(new Form<Any>("deleteTicket") {
                    @Override
                    protected void onSubmit() {
                        int tickNoS = ticket.getNumberOfSeats();
                        int idOfHall = hallService.getIdOfHall(ticket);
                        Hall updatedHall = hallService.loadHallById(idOfHall);
                        int capacity = updatedHall.getCapacity() + tickNoS;
                        ticketService.removeTicket(ticket);
                        updatedHall.setCapacity(capacity);
                        hallService.createHall(updatedHall);
                        setResponsePage(Tickets.class);
                    }
                });
                item.add(new Form<Any>("editTicket") {

                    @Override
                    protected void onSubmit() {
                        ticketID = item.getModelObject().getId();

                        setResponsePage(new EditTicket(ticketID));
                    }
                });

            }
        };
        add(dataView);

        Form<Any> deleteAllForm = new Form<Any>("deleteAllTickets") {

            @Override
            protected void onSubmit() {
                ticketService.deleteAllTickets();
                setResponsePage(Tickets.class);
            }
        };
        add(deleteAllForm);

    }


}


