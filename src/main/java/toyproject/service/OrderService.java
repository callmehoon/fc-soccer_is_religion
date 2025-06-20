package toyproject.service;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import toyproject.dto.OrderRequestDto;
import toyproject.dto.OrderResponseDto;
import toyproject.mapper.OrderMapper;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderMapper orderMapper;

    public List<OrderResponseDto> searchProducts(OrderRequestDto requestDto) {
        return orderMapper.searchProducts(requestDto.getProductId());
    }
}
