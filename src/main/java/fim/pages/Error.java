package fim.pages;


import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.form.Form;
import org.omg.CORBA.Any;


//@MountPath("/error")
public class Error extends WebPage {
    public Error(){

        Form<Any> form = new Form<Any>("goback") {

            @Override
            protected void onSubmit() {

                setResponsePage(Tickets.class);
            }
        };
        add(form);
    }
}


