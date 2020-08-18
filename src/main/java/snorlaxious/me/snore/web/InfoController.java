package snorlaxious.me.snore.web;

import java.time.LocalDateTime;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.NoArgsConstructor;

@NoArgsConstructor
@RequestMapping("/api/v1/info")
@RestController
public class InfoController {
    @GetMapping("/hello")
    public String hello() {
        return "Hello World!";
    }

    @GetMapping("/time")
    public String serverTime() {
        return "Server time: " + LocalDateTime.now();
    }
}
