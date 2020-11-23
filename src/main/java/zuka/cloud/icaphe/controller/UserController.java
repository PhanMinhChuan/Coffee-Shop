package zuka.cloud.icaphe.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import zuka.cloud.icaphe.CommonUtil.Const;
import zuka.cloud.icaphe.entity.CustomUserDetails;
import zuka.cloud.icaphe.entity.User;
import zuka.cloud.icaphe.jwt.JwtTokenProvider;
import zuka.cloud.icaphe.service.UserService;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

/**
 * @author User
 */
@RestController
@RequestMapping("users")
public class UserController {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenProvider tokenProvider;

    @Autowired
    private UserService userService;

    @GetMapping
    @ResponseBody
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<User>> findAllUser(@RequestParam(value="page", defaultValue = Const.NUMBER_PAGE_START_DEFAULT) Integer page,
                                                  @RequestParam(value="size", defaultValue = Const.NUMBER_SIZE_PAGE_DEFAULT) Integer size) {
        return new ResponseEntity<>(userService.findAllUser(page, size).getContent(), HttpStatus.OK);
    }

    @GetMapping("{idShop}")
    @PreAuthorize("hasRole('ADMIN') and hasRole('SHOP')")
    public ResponseEntity<User> getUser(@PathVariable Integer idShop) {
        System.out.println("Working");
        return new ResponseEntity<>(userService.findUserById(idShop), HttpStatus.OK);
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN') and hasRole('SHOP')")
    public ResponseEntity<Void> createUser(@RequestBody User userCreate) {
        userService.create(userCreate);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/login")
    public String authenticateUser(@Valid @RequestBody User user) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        user.getUsername(),
                        user.getPassword()
                )
        );

        String jwt = tokenProvider.generateToken((CustomUserDetails) authentication.getPrincipal());
        return jwt;
    }

    @PutMapping("{id}")
    @PreAuthorize("hasRole('SHOP') and hasRole('USER')")
    public ResponseEntity<Void> updateUser(@PathVariable Integer id, @RequestBody User userUpdate) {
        userService.update(id, userUpdate);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    @PreAuthorize("hasRole('ADMIN') and hasRole('SHOP')")
    public ResponseEntity<Void> removeUser(@PathVariable Integer id) {
        userService.remove(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
