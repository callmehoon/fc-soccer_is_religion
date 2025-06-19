package toyproject.mapper;

import toyproject.dto.OrderResponseDto;

import java.util.List;

public interface OrderMapper {
    public List<OrderResponseDto> searchProducts(List<Integer> productIds);

}
