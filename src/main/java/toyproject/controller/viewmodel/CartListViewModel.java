package toyproject.controller.viewmodel;

import lombok.Builder;
import lombok.Data;
import toyproject.controller.dto.CartInfoDto;
import toyproject.controller.dto.CartTotalPrice;
import toyproject.controller.dto.PageResponseDto;

import java.util.List;

@Data
@Builder
public class CartListViewModel {
    CartTotalPrice cartPriceInfo;
    List<CartInfoDto> cartList;
    PageResponseDto pageInfo;
}
