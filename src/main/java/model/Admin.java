package model;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Admin {

    private String adminId;
    private String name;
    private String email;
    private String password;
}
