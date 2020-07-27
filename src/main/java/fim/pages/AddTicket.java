package fim.pages;

import com.giffing.wicket.spring.boot.context.scan.WicketHomePage;
import fim.config.HallDataProvider;
import fim.db.HallService;
import fim.model.Hall;
import fim.model.Ticket;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.markup.repeater.data.DataView;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.omg.CORBA.Any;
import org.wicketstuff.annotation.mount.MountPath;

@MountPath("/addTicket")
public class AddTicket extends WebPage {

    @SpringBean
    private HallService hallService;

    // jeste prekreslovani pomoci webmarkup containeru jako v test viewer
    public AddTicket() {
        Form<Ticket> addTicketForm = new Form<Ticket>("addTicketForm") {


            @Override
            protected void onSubmit() {

                setResponsePage(AddTicket.class);
            }
        };

//        addTicketForm.add(new Select<>("numberOfSeats",));
        add(addTicketForm);







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


