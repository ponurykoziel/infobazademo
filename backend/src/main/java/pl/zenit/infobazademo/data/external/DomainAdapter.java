package pl.zenit.infobazademo.data.external;

import org.springframework.stereotype.Component;
import pl.zenit.infobazademo.data.domain.Geo;
import pl.zenit.infobazademo.data.domain.ToDomainAdapter;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

class DomainAdapter implements ToDomainAdapter<User> {

    public pl.zenit.infobazademo.data.domain.User translate(User input) {
        return new pl.zenit.infobazademo.data.domain.User(
                input.id(), input.name(), input.username(), input.email(),
                new pl.zenit.infobazademo.data.domain.Address(
                        input.address().street(),
                        input.address().suite(),
                        input.address().city(),
                        input.address().zipcode(),
                        new pl.zenit.infobazademo.data.domain.Geo(
                                input.address().geo().lat(),
                                input.address().geo().lng()
                        )
                ),
                input.phone(), input.website(),
                new pl.zenit.infobazademo.data.domain.Company(
                        input.company().name(),
                        input.company().catchPhrase(),
                        input.company().bs())
        );
    }

}
