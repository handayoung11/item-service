package hello.itemservice.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ItemUpdateDTO {
    private String itemName;
    private Integer price;
    private Integer quantity;
}
