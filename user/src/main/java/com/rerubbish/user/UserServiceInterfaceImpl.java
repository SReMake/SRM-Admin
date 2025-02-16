package com.rerubbish.user;

import org.babyfish.jimmer.client.meta.Api;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
@Transactional
public class UserServiceInterfaceImpl {
    @Api
    @GetMapping("/helloworld")
    public String helloworld() {
        return "hello world";
    }
}
