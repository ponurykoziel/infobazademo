package pl.zenit.infobazademo.data.domain;

import java.util.Collection;
import java.util.List;

public interface FromDomainAdapter<DtoType> {

    default List<DtoType> translate(Collection<User> input) {
        return input.parallelStream()
                .map(this::translate)
                .toList();
    }

    DtoType translate(User input);
}
