package toyproject.viewmodel;

import lombok.Builder;
import lombok.Data;
import toyproject.dto.CartResponseDto;
import toyproject.dto.OrderResponseDto;

import java.util.List;

@Data
@Builder
public class OrderListViewModel {
    List<OrderResponseDto> orderList;
}
