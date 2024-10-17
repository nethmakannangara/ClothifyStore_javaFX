package model;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Supplier {

    private String supplierId;
    private String name;
    private String address;
    private String email;
    private String company;
    private String contactNO;
}
