package fim.config;

import fim.model.Hall;
import org.apache.wicket.markup.repeater.data.IDataProvider;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;

import java.util.Iterator;
import java.util.List;

public class HallDataProvider implements IDataProvider<Hall> {

    private List<Hall> halls;

    public HallDataProvider(List<Hall> halls) {
        this.halls = halls;
    }

    @Override
    public Iterator<? extends Hall> iterator(long first, long count) {
        return halls.iterator();
    }

    @Override
    public long size() {
        return halls.size();
    }

    @Override
    public IModel<Hall> model(Hall object) {
        return new Model(object);
    }
}
