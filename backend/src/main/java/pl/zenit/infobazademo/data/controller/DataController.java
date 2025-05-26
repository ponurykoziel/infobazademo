package pl.zenit.infobazademo.data.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import pl.zenit.infobazademo.data.cache.DataCache;

import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping("")
@CrossOrigin(originPatterns = "http://localhost:[*]")
public class DataController {

    private final DataCache dataCache;

    public DataController(DataCache dataCache) {
        this.dataCache = dataCache;
    }

    @GetMapping("/roster")
    public List<RosterUser> getRosterUsers() {
        var users = dataCache.getUsers();
        return new RosterUserDomainAdapter().translate(users);
    }

    @GetMapping("/user/{id}")
    public User getUser(@PathVariable final int id) {
        var user = dataCache.getUser(id)
                .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, ""));
        return new UserDomainAdapter().translate(user);
    }

}
