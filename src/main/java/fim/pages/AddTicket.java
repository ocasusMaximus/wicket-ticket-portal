package fim.pages;

import com.giffing.wicket.spring.boot.context.scan.WicketHomePage;
import fim.config.HallDataProvider;
import fim.db.HallService;
import fim.db.TicketService;
import fim.model.Hall;
import fim.model.Ticket;
import org.apache.wicket.Session;
import org.apache.wicket.extensions.markup.html.form.select.SelectOption;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.NumberTextField;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.markup.repeater.data.DataView;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.apache.wicket.util.crypt.StringUtils;
import org.omg.CORBA.Any;
import org.wicketstuff.annotation.mount.MountPath;
import org.apache.wicket.extensions.markup.html.form.select.Select;

import java.io.UnsupportedEncodingException;
import java.nio.charset.CharsetEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Locale;

@MountPath("/addTicket")
public class AddTicket extends WebPage  {

    @SpringBean
    private HallService hallService;
    @SpringBean
    private TicketService ticketService;

    private String movieSelect = "Star Wars: Pomsta Sithů";
    private String languageSelect = "Anglictina";
    private String hallSelect = "J2";
    private int numberOfSeatsSelect = 0;

    // jeste prekreslovani pomoci webmarkup containeru jako v test viewer
    public AddTicket() {



        Form<Ticket> addTicketForm = new Form<Ticket>("addTicketForm") {

            Ticket ticket = new Ticket();

            @Override
            protected void onSubmit() {
                ticket.setMovie(movieSelect);
                ticket.setLanguage(languageSelect);
                ticket.setHall(hallSelect);
                ticket.setNumberOfSeats(numberOfSeatsSelect);


                int idOfHall = hallService.getIdOfHall(ticket);
                Hall updatedHall = hallService.loadHallById(idOfHall);
                int capacity = hallService.loadHallById(idOfHall).getCapacity();

                if (capacity >= ticket.getNumberOfSeats()) {
                    ticketService.createTicket(ticket);
                    updatedHall.setCapacity(capacity - ticket.getNumberOfSeats());
                    hallService.createHall(updatedHall);
                    setResponsePage(Tickets.class);
                } else {
                    setResponsePage(Error.class);
                }
            }
        };


//         movieSelect = new String(String.valueOf((StandardCharsets.UTF_8.encode(movieSelect))));
//        String test = "Čo je to";
//        System.out.println(test);
////        StandardCharsets.UTF_8.encode(movieSelect).
//        System.out.println(String.valueOf(StandardCharsets.UTF_8.encode(movieSelect)));



        Select movie = new Select("movie",  new PropertyModel<String>(this, "movieSelect"));
        movie.add(new SelectOption<String>("SW",  new Model("Star Wars: Pomsta Sithů")));
        movie.add(new SelectOption<String>("AE", new Model<String>("Avengers: Endgame")));
        movie.add(new SelectOption<String>("PP", new Model<String>("Pulp Fiction")));
        movie.add(new SelectOption<String>("JW", new Model<String>("John Wick")));

        addTicketForm.add(movie);
        add(addTicketForm);


        Select language = new Select("language",  new PropertyModel<String>(this, "languageSelect"));

        addTicketForm.add(language);

        language.add(new SelectOption<String>("AJ",  new Model<String>("Angličtina")));
        language.add(new SelectOption<String>("CJ",  new Model<String>("Čeština")));

        Select hall = new Select("hall",  new PropertyModel<String>(this, "hallSelect"));

        addTicketForm.add(hall);

        hall.add(new SelectOption<String>("J2",  new Model<String>("J2")));
        hall.add(new SelectOption<String>("J3",  new Model<String>("J3")));

        NumberTextField<Integer> numberOfSeats = new NumberTextField<Integer>("numberOfSeats",  new PropertyModel<Integer>(this, "numberOfSeatsSelect") );
        addTicketForm.add(numberOfSeats);




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


