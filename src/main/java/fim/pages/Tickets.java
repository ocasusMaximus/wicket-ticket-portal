package fim.pages;

import com.giffing.wicket.spring.boot.context.scan.WicketHomePage;
import fim.config.HallDataProvider;
import fim.config.TicketDataProvider;
import fim.db.HallService;
import fim.db.TicketService;
import fim.model.Hall;
import fim.model.Ticket;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.markup.repeater.data.DataView;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.omg.CORBA.Any;
import org.springframework.beans.factory.annotation.Autowired;


@WicketHomePage
public class Tickets extends WebPage {

    @SpringBean
    private TicketService ticketService;

    public Tickets() {
        DataView<Ticket> dataView = new DataView<Ticket>("listTickets", new TicketDataProvider(ticketService.loadAllTickets())) {
            @Override
            protected void populateItem(Item<Ticket> item) {
                item.add(new Label("movie", item.getModelObject().getMovie()));
                item.add(new Label("language", item.getModelObject().getLanguage()));
                item.add(new Label("numberOfSeats", item.getModelObject().getNumberOfSeats()));
                item.add(new Label("hall", item.getModelObject().getHall()));
                item.add(new Form<Any>("deleteTicket") {

                    @Override
                    protected void onSubmit() {

                        setResponsePage(AddTicket.class);
                    }
                });
                item.add(new Form<Any>("editTicket") {

                    @Override
                    protected void onSubmit() {

                        setResponsePage(EditTicket.class);
                    }
                });

            }
        };
        add(dataView);

        Form<Any> deleteAllForm = new Form<Any>("deleteAllTickets") {

            @Override
            protected void onSubmit() {

                setResponsePage(AddTicket.class);
            }
        };
        add(deleteAllForm);

    }

}


