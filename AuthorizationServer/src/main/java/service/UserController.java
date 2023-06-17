SecureServiceA/src/main/java/service/Controller.javapackage service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    @GetMapping("/id")
    public String getUserId(OAuth2Authentication user) {
        return user.getName();
    }

    @GetMapping
    public Map<String, Object> user(OAuth2Authentication user) {
        Map<String, Object> userInfo = new HashMap<>();
        userInfo.put("user", user.getUserAuthentication().getPrincipal());
        userInfo.put("authorities",
                AuthorityUtils.authorityListToSet(user.getUserAuthentication().getAuthorities()));

        return userInfo;
    }

    @PostMapping
    public ResponseEntity<?> createUser(@RequestBody AddUserDto addUserDto) {
        if(inMemoryUserDetailsManager.userExists(addUserDto.getName()))
            return new ResponseEntity<>(HttpStatus.CONFLICT);

        inMemoryUserDetailsManager.createUser(User.withUsername(addUserDto.getName()).password("{noop}" + addUserDto.getPassword()).roles("MANAGER").build());

        return new ResponseEntity<>(addUserDto, HttpStatus.OK);
    }

    @DeleteMapping("/{name}")
    public ResponseEntity<?> deleteUser(@PathVariable String name){
        if(!inMemoryUserDetailsManager.userExists(name))
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        inMemoryUserDetailsManager.deleteUser(name);
        return new ResponseEntity<>("Deleted", HttpStatus.OK);
    }
}
