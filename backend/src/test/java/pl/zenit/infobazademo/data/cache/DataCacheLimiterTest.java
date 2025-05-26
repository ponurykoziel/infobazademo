package pl.zenit.infobazademo.data.cache;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import pl.zenit.infobazademo.data.domain.User;
import pl.zenit.infobazademo.data.external.DataDeserializer;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

class DataCacheLimiterTest {


    private DataCacheLimiter subject;

    private DataDeserializer deserializer;

    @BeforeEach
    void setUp() {
        deserializer = Mockito.mock(DataDeserializer.class);
        Mockito.when(deserializer.deserialize(Mockito.anyString())).thenReturn(List.of());

        subject = new DataCacheLimiter(deserializer) {

            @Override
            void updateCache(List<User> updatedUserList) {
                return;
            }

            @Override
            public Collection<User> getUsers() {
                throw new RuntimeException("");
            }

            @Override
            public Optional<User> getUser(int id) {
                throw new RuntimeException("");
            }

        };
    }

    @Test
    void testUpdate_onNewInput_shouldUpdate() {
        subject.update("ABC");
        subject.update("DEF");
        Mockito.verify(deserializer,  Mockito.times(1)).deserialize("ABC");
        Mockito.verify(deserializer,  Mockito.times(1)).deserialize("DEF");
    }

    @Test
    void testUpdate_onSameInput_shouldNotUpdate() {
        subject.update("ABC");
        subject.update("ABC");

        Mockito.verify(deserializer,  Mockito.times(1)).deserialize("ABC");
    }
}