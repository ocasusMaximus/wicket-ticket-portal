package fim;


import com.giffing.wicket.spring.boot.context.extensions.ApplicationInitExtension;
import com.giffing.wicket.spring.boot.context.extensions.WicketApplicationInitConfiguration;


import fim.db.HallService;
import fim.model.Hall;
import org.apache.wicket.Page;
import org.apache.wicket.injection.Injector;
import org.apache.wicket.protocol.http.WebApplication;

import org.apache.wicket.spring.injection.annot.SpringBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@ApplicationInitExtension
@Configuration
@ComponentScan("fim")
public class ApplicationConfiguration extends WebApplication implements WicketApplicationInitConfiguration {

    @SpringBean
    private HallService hallService;
    @Override
    public void init(WebApplication webApplication) {

        // your custom configuration

        webApplication.getMarkupSettings().setDefaultMarkupEncoding("UTF-8");
        webApplication.getRequestCycleSettings().setResponseRequestEncoding("UTF-8");

        Injector.get().inject(this);

        initHalls();
    }

    private void initHalls() {
        if(hallService.loadAllHalls().size() == 0) {
            Hall j2 = new Hall();
            j2.setId(1);
            j2.setCapacity(100);
            j2.setName("J2");
            Hall j3 = new Hall();
            j3.setId(2);
            j3.setCapacity(100);
            j3.setName("J3");

            hallService.createHall(j2);
            hallService.createHall(j3);
        }
    }

    @Override
    public Class<? extends Page> getHomePage() {
        return null;
    }
}
