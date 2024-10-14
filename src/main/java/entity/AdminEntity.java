package entity;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class AdminEntity {

    private String adminId;
    private String name;
    private String email;
    private String password;
}
