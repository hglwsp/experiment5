package com.example.experiment5.controller;

import com.example.experiment5.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Slf4j
@RestController
@RequestMapping("/api")
public class UserController {

    @GetMapping("/index")
    public Map getIndex() {
        return Map.of("index", "main");
    }

    @GetMapping("/users")
    public Map getUsers() {
        return Map.of("users", users);
    }

    @GetMapping("/users/{uid}")
    public Map getUser(@PathVariable int uid) {
        log.debug("{}", uid);
        User user = users.stream()
                .filter(u -> u.getId() == uid)
                .findFirst()
                .orElse(null);

        return Optional.ofNullable(user)
                .map(u -> Map.of("user", u))
                .orElse(Map.of());
    }

    @PostMapping("/users")
    public Map postUser(@RequestBody User user) {
        users.add(user);
        return Map.of("users", user);
    }

    private static List<User> users = create();

    private static List<User> create() {
        users = new ArrayList<>();
        User user = new User(1, "kun", "123456", "753");
        User user1 = new User(2, "hgl", "1122", "626");
        User user2 = new User(3, "wsp", "5544", "0928");
        users.add(user);
        users.add(user1);
        users.add(user2);
        return users;
    }
}