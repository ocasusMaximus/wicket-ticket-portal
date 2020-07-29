package fim;


import com.giffing.wicket.spring.boot.context.extensions.ApplicationInitExtension;
import com.giffing.wicket.spring.boot.context.extensions.WicketApplicationInitConfiguration;

import fim.controller.TicketController;
import org.apache.wicket.Page;
import org.apache.wicket.protocol.http.WebApplication;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.wicketstuff.annotation.scan.AnnotatedMountScanner;
import org.wicketstuff.rest.utils.mounting.PackageScanner;

@ApplicationInitExtension
@Configuration
@ComponentScan("fim")
public class ApplicationConfiguration extends WebApplication implements WicketApplicationInitConfiguration {

    @Override
    public void init(WebApplication webApplication) {

        // your custom configuration
        //tady nemusi nic z toho byt protoze je to vsechno build in v tom wicketspringboot


    }

    @Override
    public Class<? extends Page> getHomePage() {
        return null;
    }
}
