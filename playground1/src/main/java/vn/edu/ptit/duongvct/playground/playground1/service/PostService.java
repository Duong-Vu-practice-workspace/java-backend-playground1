package vn.edu.ptit.duongvct.playground.playground1.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import vn.edu.ptit.duongvct.playground.playground1.dto.request.RequestPostDTO;
import vn.edu.ptit.duongvct.playground.playground1.dto.response.ResponsePostDTO;

import java.util.List;

public interface PostService {
    public List<ResponsePostDTO> findAllPostOfUser(Integer userId) throws JsonProcessingException;
    public ResponsePostDTO createPost(RequestPostDTO dto);
}
