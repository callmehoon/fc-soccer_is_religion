package toyproject.controller.viewmodel;

import lombok.Builder;
import lombok.Data;
import toyproject.controller.dto.CartInfoDto;
import toyproject.controller.dto.PageResponseDto;

import java.util.List;

@Data
@Builder
public class CartListViewModel {
    List<CartInfoDto> cartList;
    PageResponseDto pageInfo;
}
