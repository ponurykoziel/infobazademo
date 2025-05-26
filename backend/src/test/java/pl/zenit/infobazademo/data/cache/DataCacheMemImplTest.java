package pl.zenit.infobazademo.data.cache;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import pl.zenit.infobazademo.data.domain.User;
import pl.zenit.infobazademo.data.external.DataDeserializer;

import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

class DataCacheMemImplTest {


    private static final User user1 = new User(1, "", "", "",  null, "", "", null);
    private static final User user2 = new User(2, "", "", "",  null, "", "", null);
    private static final User user3 = new User(3, "", "", "",  null, "", "", null);
    private static final List<User> users = List.of(user1, user2, user3);

    private DataCacheMemImpl dataCache;

    @BeforeEach
    void setUp() {
        DataDeserializer deserializer = Mockito.mock(DataDeserializer.class);
        dataCache = new DataCacheMemImpl(deserializer);
    }

    @Test
    void testDataset_idShouldBeUnique() {
        assertEquals(users.size(), users.parallelStream().map(User::id).collect(Collectors.toSet()).size());
    }

    @Test
    void testUpdateCache_beforeUpdate_shouldBeEmpty() {
        assertTrue(dataCache.getUsers().isEmpty());
    }

    @Test
    void testUpdateCache_whenUpdated_shouldStoreUsers() {
        dataCache.updateCache(users);
        assertEquals(users.size(), dataCache.getUsers().size());
    }

    @Test
    void testGetUser_onValidUser_shouldReturnUser() {
        dataCache.updateCache(users);
        var user = dataCache.getUser(1);
        assertTrue(user.isPresent());
        assertEquals(user.get().id(), 1);
    }

    @Test
    void testGetUser_onValidUser_shouldReturnDifferentUsers() {
        dataCache.updateCache(users);
        var user = dataCache.getUser(1);
        var other = dataCache.getUser(2);
        assertNotEquals(user.get().id(), other.get().id());
    }

    @Test
    void testGetUser_onInvalidUser_shouldReturnNone() {
        dataCache.updateCache(users);
        var user = dataCache.getUser(-1023);
        assertTrue(user.isEmpty());
        assertFalse(dataCache.getUsers().isEmpty());
    }

}