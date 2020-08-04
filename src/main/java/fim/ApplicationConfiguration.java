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

        webApplication.getMarkupSettings().setDefaultMarkupEncoding("UTF-8");
        webApplication.getRequestCycleSettings().setResponseRequestEncoding("UTF-8");


    }

    @Override
    public Class<? extends Page> getHomePage() {
        return null;
    }
}
