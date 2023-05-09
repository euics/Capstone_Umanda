package sejong.europlanner.controller;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import sejong.europlanner.component.JwtTokenProvider;
import sejong.europlanner.dto.UserDto;
import sejong.europlanner.service.serviceinterface.UserService;
import sejong.europlanner.vo.request.user.RequestLogin;
import sejong.europlanner.vo.request.user.RequestUser;
import sejong.europlanner.vo.response.user.ResponseGetUser;
import sejong.europlanner.vo.response.user.ResponseLogin;
import sejong.europlanner.vo.response.user.ResponseUser;

import java.net.URI;

@RestController
@CrossOrigin(origins = "http://localhost:3000", allowedHeaders = "Authorization")
public class UserController {
    private final UserService userService;

    private final JwtTokenProvider jwtTokenProvider;

    @Autowired
    public UserController(UserService userService,
                          JwtTokenProvider jwtTokenProvider) {
        this.userService = userService;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @PostMapping("/users/register")
    public ResponseEntity<ResponseUser> createUser(@RequestBody RequestUser requestUser){
        ModelMapper mapper = new ModelMapper();
        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

        UserDto userDto = mapper.map(requestUser, UserDto.class);

        ResponseUser responseUser = userService.checkValidation(userDto);

        // 사용자 URI 생성
        URI location = ServletUriComponentsBuilder
                .fromCurrentContextPath()
                .path("/users/{id}")
                .buildAndExpand(responseUser.getId())
                .toUri();

        return ResponseEntity.created(location).body(responseUser);
    }

    @PostMapping("/users/login")
    public ResponseEntity<ResponseLogin> loginUser(@RequestBody RequestLogin requestLogin) {
        ModelMapper mapper = new ModelMapper();
        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

        UserDto userDto = mapper.map(requestLogin, UserDto.class);

        ResponseLogin responseLogin = userService.loginValidation(userDto);

        String jwtToken = jwtTokenProvider.generateToken(requestLogin.getUsername());

        responseLogin.setJwtToken(jwtToken);

        return ResponseEntity.ok().body(responseLogin);
    }

    @GetMapping("/users/{userId}")
    public ResponseEntity<ResponseGetUser> getUser(@PathVariable Long userId){
        ResponseGetUser user = userService.getUser(userId);

        return ResponseEntity.ok().body(user);
    }
}
