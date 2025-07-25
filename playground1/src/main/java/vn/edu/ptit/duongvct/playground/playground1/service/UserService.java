package vn.edu.ptit.duongvct.playground.playground1.service;

import vn.edu.ptit.duongvct.playground.playground1.dto.request.RequestUserDTO;
import vn.edu.ptit.duongvct.playground.playground1.dto.response.ResponseUserDTO;

public interface UserService {
    public ResponseUserDTO createUser(RequestUserDTO dto);
}
