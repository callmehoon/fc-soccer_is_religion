package toyproject.controller.viewmodel;

import lombok.Builder;
import lombok.Data;
import toyproject.dto.OrderResponseDto;

import java.util.List;

@Data
@Builder
public class OrderListViewModel {
    List<OrderResponseDto> orderList;
}
