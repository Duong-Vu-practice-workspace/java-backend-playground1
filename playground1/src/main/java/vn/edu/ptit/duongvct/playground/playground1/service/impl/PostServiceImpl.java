package vn.edu.ptit.duongvct.playground.playground1.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import vn.edu.ptit.duongvct.playground.playground1.domain.Post;
import vn.edu.ptit.duongvct.playground.playground1.domain.User;
import vn.edu.ptit.duongvct.playground.playground1.dto.request.RequestPostDTO;
import vn.edu.ptit.duongvct.playground.playground1.dto.response.ResponsePostDTO;
import vn.edu.ptit.duongvct.playground.playground1.repository.PostRepository;
import vn.edu.ptit.duongvct.playground.playground1.repository.UserRepository;
import vn.edu.ptit.duongvct.playground.playground1.service.PostService;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@Slf4j
public class PostServiceImpl implements PostService {
    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private final RedisTemplate<String, String> redisTemplate;
    private final ObjectMapper objectMapper;
    private final ModelMapper modelMapper;
    public static final int CACHE_TIME_IN_MINUTES = 15;
    public PostServiceImpl(PostRepository postRepository, UserRepository userRepository, RedisTemplate<String, String> redisTemplate, ObjectMapper objectMapper, ModelMapper modelMapper) {
        this.postRepository = postRepository;
        this.userRepository = userRepository;
        this.redisTemplate = redisTemplate;
        this.objectMapper = objectMapper;
        this.modelMapper = modelMapper;
    }


    @Override
    public List<ResponsePostDTO> findAllPostOfUser(Integer userId) throws JsonProcessingException {
        String cacheKey = String.format("user:%d:posts", userId);
        String cachedData = redisTemplate.opsForValue().get(cacheKey);
        ArrayList<Post> list = new ArrayList<>();
        List<ResponsePostDTO> newList;
        if (Objects.isNull(cachedData)) {
            log.info("Cache miss, read from database");
            list = new ArrayList<>(this.postRepository.findAllByUserId(userId));
            newList = list.stream()
                    .map(i -> this.modelMapper.map(i, ResponsePostDTO.class))
                    .peek(i -> i.setUserId(userId))
                    .toList();
            redisTemplate.opsForValue().set(cacheKey,
                    objectMapper.writeValueAsString(newList),
                    Duration.ofMinutes(CACHE_TIME_IN_MINUTES));
        } else {
            log.info("Cache hit");
            newList = objectMapper.readValue(cachedData,
                    List.class);
        }
        return newList;

    }

    @Override
    public ResponsePostDTO createPost(RequestPostDTO dto) {
        log.info("Start create Post: {}", dto);
        Post createdPost = this.postRepository.save(mapFromRequestPostDTO(dto));
        log.info("Finish create post: {}", createdPost);
        return mapFromPost(createdPost);
    }
    private Post mapFromRequestPostDTO(RequestPostDTO dto) {
        log.info("Start map RequestPostDTO {} to post", dto);
        Post post = this.modelMapper.map(dto, Post.class);
        log.info("Post after mapped by model mapper: {}", post);
        Optional<User> opUser = this.userRepository.findById(dto.getUserId());
        if (opUser.isEmpty()) {
            log.error("User with id {} not found", dto.getUserId());
            throw new IllegalArgumentException("User not found");
        }
        post.setUser(opUser.get());
        log.info("Finish map RequestPostDTO to post {} ", post);
        return post;
    }
    private ResponsePostDTO mapFromPost(Post post) {
        log.info("Start map post {} to ResponsePostDTO", post);
        ResponsePostDTO dto = this.modelMapper.map(post, ResponsePostDTO.class);
        dto.setUserId(post.getUser().getId());
        log.info("Finish map post to ResponsePostDTO {}", dto);
        return dto;
    }
}
