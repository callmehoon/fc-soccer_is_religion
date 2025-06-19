package toyproject.service;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import toyproject.dto.OrderRequestDto;
import toyproject.mapper.OrderMapper;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderMapper orderMapper;

    public searchProducts(OrderRequestDto requestDto);
}
