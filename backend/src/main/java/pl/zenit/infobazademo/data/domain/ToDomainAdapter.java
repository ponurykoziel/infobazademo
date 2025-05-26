package pl.zenit.infobazademo.data.domain;

import java.util.Collection;
import java.util.List;

public interface ToDomainAdapter<DtoType> {

    default List<User> translate(Collection<DtoType> input) {
        return input.parallelStream()
                .map(this::translate)
                .toList();
    }

    User translate(DtoType input);

}
