package pl.zenit.infobazademo.data.cache;

import pl.zenit.infobazademo.data.domain.User;

import java.util.Collection;
import java.util.Optional;

public interface DataCache {

    void update(String userListJson);

    public Collection<User> getUsers();

    public Optional<User> getUser(int id);

}
