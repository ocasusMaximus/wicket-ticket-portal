package fim.controller;
import fim.db.TicketService;
import fim.model.Ticket;
import org.apache.wicket.WicketRuntimeException;
import org.apache.wicket.authroles.authorization.strategies.role.IRoleCheckingStrategy;
import org.apache.wicket.request.http.WebRequest;
import org.apache.wicket.request.http.WebResponse;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.springframework.ui.Model;

import org.wicketstuff.rest.annotations.MethodMapping;
import org.wicketstuff.rest.annotations.ResourcePath;
import org.wicketstuff.rest.contenthandling.IWebSerialDeserial;
import org.wicketstuff.rest.resource.AbstractRestResource;

import java.util.List;
import java.util.Locale;
import java.util.function.Supplier;

@ResourcePath("/ticketController")
public class TicketController extends AbstractRestResource<IWebSerialDeserial>{


//    private List<Ticket> tickets;
    @SpringBean
    private TicketService ticketService;

    public TicketController() {
        super(new IWebSerialDeserial() {
            @Override
            public void objectToResponse(Object targetObject, WebResponse response, String mimeType) throws WicketRuntimeException {

            }

            @Override
            public <T> T requestToObject(WebRequest request, Class<T> argClass, String mimeType) throws WicketRuntimeException {
                return null;
            }

            @Override
            public boolean isMimeTypeSupported(String mimeType) {
                return true;
            }
        });



    }

    @MethodMapping(value = "/test")
    public String index() {
        System.out.println("Jsem tady");
       ticketService.loadAllTickets();
       return "tickets";

    }

//
//    @MethodMapping("/persons")
//    public List<Ticket> getAllPersons() {
//        return persons;
//    }


}
