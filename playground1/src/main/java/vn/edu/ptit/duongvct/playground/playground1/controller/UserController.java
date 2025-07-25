package vn.edu.ptit.duongvct.playground.playground1.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.edu.ptit.duongvct.playground.playground1.dto.request.RequestUserDTO;
import vn.edu.ptit.duongvct.playground.playground1.dto.response.ResponsePostDTO;
import vn.edu.ptit.duongvct.playground.playground1.dto.response.ResponseUserDTO;
import vn.edu.ptit.duongvct.playground.playground1.service.PostService;
import vn.edu.ptit.duongvct.playground.playground1.service.UserService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {
    private final UserService userService;
    private final PostService postService;
    public UserController(UserService userService, PostService postService) {
        this.userService = userService;
        this.postService = postService;
    }
    @PostMapping
    public ResponseEntity<ResponseUserDTO> createUser(@RequestBody RequestUserDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(this.userService.createUser(dto));
    }

    @GetMapping("/{userId}/posts")
    public ResponseEntity<List<ResponsePostDTO>> getAllPostsOfUser(@PathVariable Integer userId) throws JsonProcessingException {
        return ResponseEntity.ok(this.postService.findAllPostOfUser(userId));
    }
}
