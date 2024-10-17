package model;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Item {
    private String itemCode;
    private String description;
    private Double UnitPrice;
    private String size;
    private Integer qtyOnHand;
}
