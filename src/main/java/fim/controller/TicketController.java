//package fim.controller;
//import fim.model.Ticket;
//import org.apache.wicket.authroles.authorization.strategies.role.IRoleCheckingStrategy;
//import org.wicketstuff.rest.annotations.MethodMapping;
//import org.wicketstuff.rest.contenthandling.IWebSerialDeserial;
//import org.wicketstuff.rest.resource.AbstractRestResource;
//
//import java.util.List;
//import java.util.Locale;
//import java.util.function.Supplier;
//
//
//public class TicketController extends AbstractRestResource<IWebSerialDeserial>{
//
//    public TicketController(IWebSerialDeserial serialDeserial) {
//        super(serialDeserial);
//    }
//
//    public TicketController(IWebSerialDeserial serialDeserial, IRoleCheckingStrategy roleCheckingStrategy) {
//        super(serialDeserial, roleCheckingStrategy);
//    }
//
//    public TicketController(IWebSerialDeserial serialDeserial, IRoleCheckingStrategy roleCheckingStrategy, Supplier<Locale> localeSupplier) {
//        super(serialDeserial, roleCheckingStrategy, localeSupplier);
//    }
////
//    @MethodMapping("/persons")
//    public List<Ticket> getAllPersons() {
//        return persons;
//    }


//}
