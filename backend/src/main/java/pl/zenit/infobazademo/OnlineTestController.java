package pl.zenit.infobazademo;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("")
@CrossOrigin(originPatterns = "http://localhost:[*]")
public class OnlineTestController {

    private static final String HELLO_MESSAGE = "Backend is running";

    @GetMapping
    public String hello() {
        return HELLO_MESSAGE;
    }

}
