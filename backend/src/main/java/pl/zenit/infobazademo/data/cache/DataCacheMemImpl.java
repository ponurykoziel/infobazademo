package pl.zenit.infobazademo.data.cache;

import org.springframework.stereotype.Component;
import pl.zenit.infobazademo.data.domain.User;
import pl.zenit.infobazademo.data.external.DataDeserializer;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

@Component
public class DataCacheMemImpl extends DataCacheLimiter {

    private Map<Integer, User> users = new ConcurrentHashMap<>();

    public DataCacheMemImpl(DataDeserializer dataDeserializer) {
        super(dataDeserializer);
    }

    @Override
    void updateCache(List<User> updatedUserList) {
        Map<Integer, User> buffer = updatedUserList
            .parallelStream()
            .collect(Collectors.toMap(User::id, p-> p));
        var swap = new ConcurrentHashMap<>(buffer);
        users = swap;
    }

    @Override
    public Collection<User> getUsers() {
        return users.values();
    }

    @Override
    public Optional<User> getUser(int id) {
        return Optional.ofNullable(users.get(id));
    }

}
