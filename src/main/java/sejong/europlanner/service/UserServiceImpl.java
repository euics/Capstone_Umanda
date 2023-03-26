package sejong.europlanner.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sejong.europlanner.dto.UserDto;
import sejong.europlanner.entity.UserEntity;
import sejong.europlanner.repository.UserRepository;
import sejong.europlanner.vo.response.ResponseUser;

@Service
@Transactional
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
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

        userRepository.save(createUser(userDto));

        return new ModelMapper().map(userDto, ResponseUser.class);
    }
}
