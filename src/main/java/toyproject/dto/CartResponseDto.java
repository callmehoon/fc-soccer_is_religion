package toyproject.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CartResponseDto {
    private int    productId;
    private String productImg;
    private String productName;
    private int    size;
    private int    productQuantity;
    private int    productPrice;
}
