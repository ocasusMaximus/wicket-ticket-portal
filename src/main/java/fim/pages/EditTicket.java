package fim.pages;

import fim.config.HallDataProvider;
import fim.db.HallService;
import fim.model.Hall;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.markup.repeater.data.DataView;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.wicketstuff.annotation.mount.MountPath;

@MountPath("/editTicket")
public class EditTicket extends WebPage {

    @SpringBean
    private HallService hallService;
    public EditTicket() {
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


