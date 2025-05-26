package pl.zenit.infobazademo.data.cache;

import org.springframework.stereotype.Component;
import pl.zenit.infobazademo.data.domain.User;
import pl.zenit.infobazademo.data.external.DataDeserializer;

import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

@Component
abstract class DataCacheLimiter implements DataCache {

    private final DataDeserializer dataDeserializer;

    public DataCacheLimiter(DataDeserializer dataDeserializer) {
        this.dataDeserializer = dataDeserializer;
    }

    private AtomicReference<String> buffer = new AtomicReference<>();

    @Override
    public void update(String userListJson) {
        var old = buffer.getAndSet(userListJson);
        boolean same = old != null && old.equals(userListJson);
        if (!same) {
            var users = dataDeserializer.deserialize(userListJson);
            updateCache(users);
        }
    }

    abstract void updateCache(List<User> updatedUserList);

}
