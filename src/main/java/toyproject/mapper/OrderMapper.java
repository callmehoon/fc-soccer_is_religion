package toyproject.mapper;



import toyproject.controller.dto.OrderItemRequestDto;
import toyproject.controller.dto.OrderResponseDto;

import java.util.List;

public interface OrderMapper {
    List<OrderResponseDto> searchProducts(List<OrderItemRequestDto> productIds);
}
