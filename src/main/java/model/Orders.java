package model;

import lombok.*;

import java.sql.ClientInfoStatus;
import java.util.Date;
import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Orders {
    private String orderId;
    private Date orderDate;
    private String employeeId;
    private String paymentType;
    List<OrderDetails> orderDetailsList;
}
