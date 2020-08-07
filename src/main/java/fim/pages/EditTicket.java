package fim.pages;

import fim.config.HallDataProvider;
import fim.db.HallService;
import fim.db.TicketService;
import fim.model.Hall;
import fim.model.Ticket;
import org.apache.wicket.extensions.markup.html.form.select.Select;
import org.apache.wicket.extensions.markup.html.form.select.SelectOption;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.NumberTextField;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.markup.repeater.data.DataView;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.spring.injection.annot.SpringBean;


public class EditTicket extends WebPage {

    @SpringBean
    private HallService hallService;
    @SpringBean
    private TicketService ticketService;

    private String movieSelect = "Star Wars: Pomsta Sithů";
    private String languageSelect = "Anglictina";
    private String hallSelect = "J2";
    private int numberOfSeatsSelect = 0;


    public EditTicket(int id) {

        Form<Ticket> editTicketForm = new Form<Ticket>("editTicketForm") {

            Ticket ticket = new Ticket();

            @Override
            protected void onSubmit() {

                ticket.setMovie(movieSelect);
                ticket.setLanguage(languageSelect);
                ticket.setHall(hallSelect);
                ticket.setNumberOfSeats(numberOfSeatsSelect);

                Ticket dbTicket = ticketService.loadTicketById(id);
                int oldTicketNoS = dbTicket.getNumberOfSeats();

                int oldIdOfHall = hallService.getIdOfHall(dbTicket);
                int idOfHall = hallService.getIdOfHall(ticket);

                Hall oldHall = hallService.loadHallById(oldIdOfHall);
                Hall updatedHall = hallService.loadHallById(idOfHall);

                int capacity;
                if (oldHall.getId() != updatedHall.getId()) {
                    capacity = updatedHall.getCapacity();
                } else {
                    capacity = oldHall.getCapacity() + oldTicketNoS;
                }

                if (capacity >= ticket.getNumberOfSeats()) {

                    ticket.setId(id);
                    ticketService.updateTicket(ticket);

                    if (oldHall.getId() != updatedHall.getId()) {
                        int oldCapacity = oldHall.getCapacity() + oldTicketNoS;
                        oldHall.setCapacity(oldCapacity);
                        hallService.createHall(oldHall);
                    }

                    updatedHall.setCapacity(capacity - ticket.getNumberOfSeats());
                    hallService.createHall(updatedHall);
                    setResponsePage(Tickets.class);
                } else {
                    setResponsePage(Error.class);
                }

            }
        };


        Select movie = new Select("movie", new PropertyModel<String>(this, "movieSelect"));
        movie.add(new SelectOption<String>("SW", new Model("Star Wars: Pomsta Sithů")));
        movie.add(new SelectOption<String>("AE", new Model<String>("Avengers: Endgame")));
        movie.add(new SelectOption<String>("PP", new Model<String>("Pulp Fiction")));
        movie.add(new SelectOption<String>("JW", new Model<String>("John Wick")));

        editTicketForm.add(movie);
        add(editTicketForm);


        Select language = new Select("language", new PropertyModel<String>(this, "languageSelect"));

        editTicketForm.add(language);

        language.add(new SelectOption<String>("AJ", new Model<String>("Angličtina")));
        language.add(new SelectOption<String>("CJ", new Model<String>("Čeština")));

        Select hall = new Select("hall", new PropertyModel<String>(this, "hallSelect"));

        editTicketForm.add(hall);

        hall.add(new SelectOption<String>("J2", new Model<String>("J2")));
        hall.add(new SelectOption<String>("J3", new Model<String>("J3")));

        NumberTextField<Integer> numberOfSeats = new NumberTextField<Integer>("numberOfSeats", new PropertyModel<Integer>(this, "numberOfSeatsSelect"));
        editTicketForm.add(numberOfSeats);


        DataView<Hall> dataView = new DataView<Hall>("listhalls", new HallDataProvider(hallService.loadAllHalls())) {
            @Override
            protected void populateItem(Item<Hall> item) {
                item.add(new Label("hallName", item.getModelObject().getName()));
                item.add(new Label("hallCapacity", item.getModelObject().getCapacity()));
            }
        };
        add(dataView);
    }
}


