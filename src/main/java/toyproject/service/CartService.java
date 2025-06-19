package toyproject.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import toyproject.dto.CartRequestDto;
import toyproject.dto.CartResponseDto;
import toyproject.mapper.CartMapper;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CartService {
    private final CartMapper cartMapper;

    public List<CartResponseDto> searchCart(CartRequestDto requestDto) {

        List<CartResponseDto> userCartResultList = cartMapper.searchUserCart(requestDto.getUserId());

        return userCartResultList;


    }

}
