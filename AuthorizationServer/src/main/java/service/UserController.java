package service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController()
@RequestMapping(value = "/user")
public class UserController {
    @Autowired
    private InMemoryUserDetailsManager inMemoryUserDetailsManager;

    @GetMapping
    public Map<String, Object> user(OAuth2Authentication user) {
        Map<String, Object> userInfo = new HashMap<>();
        userInfo.put("user", user.getUserAuthentication().getPrincipal());
        userInfo.put("authorities",
                AuthorityUtils.authorityListToSet(user.getUserAuthentication().getAuthorities()));

//        if(!inMemoryUserDetailsManager.userExists("test"))
//            inMemoryUserDetailsManager.createUser(User.withUsername("test").password("{noop}test").roles("MANAGER").build());

        return userInfo;
    }

//    @DeleteMapping


//    @PostMapping


}
