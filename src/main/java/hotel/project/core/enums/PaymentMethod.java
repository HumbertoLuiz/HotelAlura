package hotel.project.core.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum PaymentMethod {
    CREDIT (1, "CREDIT"),
    DEBIT (2, "DEBIT"),
    CASH (3, "CASH");

    private Integer id;
    
    private String name;
}
