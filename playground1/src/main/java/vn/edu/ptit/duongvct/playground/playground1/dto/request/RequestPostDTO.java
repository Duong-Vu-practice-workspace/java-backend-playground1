package vn.edu.ptit.duongvct.playground.playground1.dto.request;

import lombok.*;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class RequestPostDTO {
    private String name;
    private String content;
    private Integer userId;
}
