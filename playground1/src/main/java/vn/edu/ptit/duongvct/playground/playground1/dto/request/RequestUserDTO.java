package vn.edu.ptit.duongvct.playground.playground1.dto.request;

import lombok.*;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class RequestUserDTO {
    private String name;
    private String email;
}
