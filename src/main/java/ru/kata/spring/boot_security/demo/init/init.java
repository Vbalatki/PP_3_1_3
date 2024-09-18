package ru.kata.spring.boot_security.demo.init;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.RoleService;
import ru.kata.spring.boot_security.demo.service.UserService;

import javax.annotation.PostConstruct;
import java.util.HashSet;
import java.util.Set;

@Component
public class init {
    private final UserService userService;
    private final RoleService roleService;

    @Autowired
    public init(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @PostConstruct
    public void util() {
        Role roleUser = new Role(1, "ROLE_USER");
        Role roleAdmin = new Role(2, "ROLE_ADMIN");
        roleService.addRole(roleUser);
        roleService.addRole(roleAdmin);

        userService.saveUser(new User("admin", "admin", "adminovich", 20, "admin@mail.ru"),
                new HashSet<Role>(Set.of(roleAdmin, roleUser)));
        userService.saveUser(new User("user", "user", "userovich", 12, "user@mail.ru"));
    }
}
