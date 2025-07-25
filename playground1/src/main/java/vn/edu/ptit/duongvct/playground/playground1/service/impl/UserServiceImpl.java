package vn.edu.ptit.duongvct.playground.playground1.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import vn.edu.ptit.duongvct.playground.playground1.domain.User;
import vn.edu.ptit.duongvct.playground.playground1.dto.request.RequestUserDTO;
import vn.edu.ptit.duongvct.playground.playground1.dto.response.ResponseUserDTO;
import vn.edu.ptit.duongvct.playground.playground1.repository.UserRepository;
import vn.edu.ptit.duongvct.playground.playground1.service.UserService;
@Service
@Slf4j
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    public UserServiceImpl(UserRepository userRepository, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public ResponseUserDTO createUser(RequestUserDTO dto) {
        log.info("Create user: {}", dto);
        User user = modelMapper.map(dto, User.class);
        User newUser = this.userRepository.save(user);
        log.info("Create user successfully: {}", newUser);
        return this.modelMapper.map(newUser, ResponseUserDTO.class);

    }
}
