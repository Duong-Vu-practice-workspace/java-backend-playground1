package vn.edu.ptit.duongvct.playground.playground1.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import vn.edu.ptit.duongvct.playground.playground1.domain.Post;
import vn.edu.ptit.duongvct.playground.playground1.dto.request.RequestPostDTO;

@Configuration
public class ModelMapperConfig {
    @Bean
    public ModelMapper modelMapper(){
        ModelMapper mapper =  new ModelMapper();
        mapper.getConfiguration().setSkipNullEnabled(true);
        mapper.typeMap(RequestPostDTO.class, Post.class)
                .addMappings(m -> m.skip(Post::setId));
        return mapper;
    }
}
