package toyproject.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class OrderResponseDto {
    private int    productId;
    private String productImg;
    private String productName;
    private int    productPrice;
}
