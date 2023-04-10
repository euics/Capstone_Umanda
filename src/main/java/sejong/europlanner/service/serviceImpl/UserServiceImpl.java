package sejong.europlanner.service.serviceImpl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sejong.europlanner.dto.UserDto;
import sejong.europlanner.entity.UserEntity;
import sejong.europlanner.exception.UserNotFoundException;
import sejong.europlanner.repository.UserRepository;
import sejong.europlanner.service.serviceinterface.UserService;
import sejong.europlanner.vo.response.ResponseLogin;
import sejong.europlanner.vo.response.ResponseUser;

@Service
@Transactional
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    private final BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public UserEntity createUser(UserDto userDto) {
        UserEntity user = new UserEntity();
        user.setId(userDto.getId());
        user.setUsername(userDto.getUsername());
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        user.setName(userDto.getName());
        user.setBirthdate(userDto.getBirthdate());
        user.setGender(userDto.getGender());

        return user;
    }

    @Override
    public ResponseUser checkValidation(UserDto userDto) {
        if (!userDto.getPassword().equals(userDto.getPassword1()))
            throw new IllegalStateException("비밀번호가 일치하지 않습니다.");

        UserEntity savedUser = userRepository.findByUsername(userDto.getUsername());
        if (savedUser != null)
            throw new IllegalStateException("이미 존재하는 회원입니다.");

        UserEntity newUser = userRepository.save(createUser(userDto));
        return new ModelMapper().map(newUser, ResponseUser.class);
    }

    @Override
    public ResponseLogin loginValidation(UserDto userDto) {
        UserEntity savedUser = userRepository.findByUsername(userDto.getUsername());

        if(savedUser == null)
            throw new UserNotFoundException("존재하지 않는 회원입니다.");

        if(!passwordEncoder.matches(userDto.getPassword(), savedUser.getPassword()))
            throw new IllegalStateException("비밀번호가 일치하지 않습니다.");

        return new ModelMapper().map(savedUser, ResponseLogin.class);
    }
}
