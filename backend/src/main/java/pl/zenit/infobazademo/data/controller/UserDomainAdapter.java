package pl.zenit.infobazademo.data.controller;

import pl.zenit.infobazademo.data.domain.FromDomainAdapter;
import java.util.Collection;

class UserDomainAdapter implements FromDomainAdapter<User> {

    public User translate(pl.zenit.infobazademo.data.domain.User input) {
        return new User(
                input.id(), input.name(), input.username(), input.email(),
                new Address(
                        input.address().street(),
                        input.address().suite(),
                        input.address().city(),
                        input.address().zipcode()
                ),
                input.phone(), input.website(),
                new Company(
                        input.company().name(),
                        input.company().catchPhrase(),
                        input.company().bs())
        );
    }

}
