package sejong.europlanner.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sejong.europlanner.dto.UserDto;
import sejong.europlanner.entity.UserEntity;
import sejong.europlanner.repository.UserRepository;
import sejong.europlanner.vo.response.ResponseUser;

@Service
@Transactional
public class UserServiceImpl implements UserService{
    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
            this.userRepository = userRepository;
        }

        @Override
        public ResponseUser checkValidation(UserDto userDto) {
            if(!userDto.getPassword().equals(userDto.getPassword1()))
                throw new IllegalStateException("비밀번호가 일치하지 않습니다.");

            UserEntity savedUser = userRepository.findByUsername(userDto.getUsername());
            if(savedUser != null)
                throw new IllegalStateException("이미 존재하는 회원입니다.");

            userRepository.save(new ModelMapper().map(userDto, UserEntity.class));

        return new ModelMapper().map(userDto, ResponseUser.class);
    }
}
