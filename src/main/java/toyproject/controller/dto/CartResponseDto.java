package toyproject.controller.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class CartResponseDto {
    private CartTotalPrice priceInfo;
    private List<CartInfoDto> cartItems;
    private PageResponseDto pageResponseDto;
}
