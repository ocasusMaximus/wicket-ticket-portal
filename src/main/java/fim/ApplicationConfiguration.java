package fim;

import com.giffing.wicket.spring.boot.context.extensions.ApplicationInitExtension;
import com.giffing.wicket.spring.boot.context.extensions.WicketApplicationInitConfiguration;
import fim.pages.AddTicket;
import fim.pages.Error;
import fim.pages.Tickets;
import org.apache.wicket.protocol.http.WebApplication;

@ApplicationInitExtension
public class ApplicationConfiguration implements WicketApplicationInitConfiguration {

    @Override
    public void init(WebApplication webApplication) {
        // your custom configuration
        webApplication.mountPage("/", Tickets.class);
        webApplication.mountPage("addTicket", AddTicket.class);
        webApplication.mountPage("error", Error.class);
    }
}
