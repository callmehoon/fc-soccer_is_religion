package toyproject.mapper;

import toyproject.dto.CartResponseDto;

import java.util.List;

public interface CartMapper {
    public List<CartResponseDto> searchUserCart(String userId);

}
