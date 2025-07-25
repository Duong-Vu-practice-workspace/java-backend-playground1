package vn.edu.ptit.duongvct.playground.playground1.dto.response;

import lombok.*;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ResponsePostDTO {
    private Integer id;
    private String name;
    private String content;
    private Integer userId;
}
