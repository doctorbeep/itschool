package ro.itschool.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ro.itschool.entity.ITSchoolUser;
import ro.itschool.service.ITSchoolUserService;

@RestController
public class ITSchoolUserController {
    @Autowired
    private ITSchoolUserService itSchoolUserService;

    @PreAuthorize("hasAuthority('USER')")  // Use hasRole for role-based access control
    @GetMapping("/auth/user/welcome")
    public String sayHello() {
        return "Hello user";
    }

    @GetMapping("/everyone")
    public String sayHelloEveryone(){
        return "Hello stranger";
    }

    @PreAuthorize("hasRole('ADMIN')")  // Use hasRole for role-based access control
    @GetMapping("/auth/admin/welcome")
    public String sayHelloAdmin() {
        return "Hello admin";
    }

    @PostMapping("/register")
    public void registerUser(@RequestBody ITSchoolUser user){
        itSchoolUserService.registerUser(user);
    }
}