package toyproject.viewmodel;

import lombok.Builder;
import lombok.Data;
import toyproject.dto.CartResponseDto;

import java.util.List;

@Data
@Builder
public class CartListViewModel {
    List<CartResponseDto> cartList;
}
