package toyproject.mapper;



import toyproject.controller.dto.OrderItemRequestDto;
import toyproject.controller.dto.OrderResponseDto;
import toyproject.mapper.result.OrderResult;

import java.util.List;

public interface OrderMapper {
    List<OrderResult> searchProducts(List<Integer> productIds);
}
