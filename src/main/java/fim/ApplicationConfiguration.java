package fim;


import com.giffing.wicket.spring.boot.context.extensions.ApplicationInitExtension;
import com.giffing.wicket.spring.boot.context.extensions.WicketApplicationInitConfiguration;
import fim.controller.TicketController;
import fim.pages.AddTicket;
import fim.pages.EditTicket;
import fim.pages.Error;
import fim.pages.Tickets;

import org.apache.wicket.Page;
import org.apache.wicket.protocol.http.WebApplication;
import org.apache.wicket.request.resource.IResource;
import org.apache.wicket.request.resource.ResourceReference;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.wicketstuff.annotation.scan.AnnotatedMountScanner;
import org.wicketstuff.rest.utils.mounting.PackageScanner;

@ApplicationInitExtension
@Configuration
@ComponentScan("fim.db")
public class ApplicationConfiguration extends WebApplication implements WicketApplicationInitConfiguration {

    @Override
    public void init(WebApplication webApplication) {

        // your custom configuration

        new AnnotatedMountScanner().scanPackage("fim.pages").mount(this);

        PackageScanner.scanPackage("fim.controller");

    }

    @Override
    public Class<? extends Page> getHomePage() {
        return null;
    }
}
