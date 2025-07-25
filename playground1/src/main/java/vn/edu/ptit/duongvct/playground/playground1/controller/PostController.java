package vn.edu.ptit.duongvct.playground.playground1.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.edu.ptit.duongvct.playground.playground1.dto.request.RequestPostDTO;
import vn.edu.ptit.duongvct.playground.playground1.dto.response.ResponsePostDTO;
import vn.edu.ptit.duongvct.playground.playground1.service.PostService;

@RestController
@RequestMapping("/api/v1/posts")
public class PostController {
    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }
    @PostMapping
    public ResponseEntity<ResponsePostDTO> createPost(@RequestBody RequestPostDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(this.postService.createPost(dto));
    }


}
