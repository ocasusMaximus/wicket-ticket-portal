package fim.controller;
import fim.db.HallService;
import fim.db.TicketService;
import fim.model.Hall;
import fim.model.Ticket;
import fim.pages.Tickets;
import org.apache.wicket.Page;
import org.apache.wicket.WicketRuntimeException;
import org.apache.wicket.request.http.WebRequest;
import org.apache.wicket.request.http.WebResponse;
import org.apache.wicket.spring.injection.annot.SpringBean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.wicketstuff.rest.annotations.MethodMapping;
import org.wicketstuff.rest.annotations.ResourcePath;
import org.wicketstuff.rest.contenthandling.IWebSerialDeserial;
import org.wicketstuff.rest.resource.AbstractRestResource;
import org.wicketstuff.rest.utils.http.HttpMethod;

@ResourcePath("/")
public class TicketController extends AbstractRestResource<IWebSerialDeserial>{



    //problem nejspis je ze on si tady neotvira session a tudiz vraci null
    @SpringBean
    private TicketService ticketService;
    @SpringBean
    private HallService hallService;






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

        org.apache.wicket.injection.Injector.get().inject(this);

    }

    @MethodMapping(value = "/test", httpMethod = HttpMethod.GET)
    public String index() {
        for(Ticket ticket : ticketService.loadAllTickets()){
            System.out.println("Jméno filmu: "+ticket.getMovie());
        }
       ticketService.loadAllTickets();
       return  "test";

    }
//
//    @MethodMapping(value = "/addTicket", httpMethod = HttpMethod.GET)
//    public String addTicket() {
//        for(Hall hall : hallService.loadAllHalls()){
//            System.out.println("Jméno sálu: "+hall.getName());
//        }
//        hallService.loadAllHalls();
//        return  "test";
//
//    }



}
