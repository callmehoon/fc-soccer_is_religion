package toyproject.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import toyproject.controller.dto.*;
import toyproject.mapper.CartMapper;
import toyproject.mapper.queryparam.CartQueryParam;
import toyproject.mapper.result.CartResult;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class CartService {
    private final CartMapper cartMapper;

    public CartResponseDto searchCart(CartRequestDto requestDto, PageRequestDto pageRequestDto) {

        log.info("CartService_searchCart 진입");

        int currentpage = pageRequestDto.getPageOrDefault();
        int size = pageRequestDto.getSizeOrDefault();

        CartQueryParam cartQueryParam = CartQueryParam.builder()
                .userId(requestDto.getUserId())
                .offset((currentpage - 1) * size)
                .size(size)
                .build();


        int total = cartMapper.findCartItemsCountByUserId(cartQueryParam);
        int totalPage = total / size + 1;


        List<CartResult> userCartResultList = cartMapper.findCartItemsByUserId(cartQueryParam);

        List<CartInfoDto> userCartInfoList = userCartResultList.stream().map(cartResult -> CartInfoDto
                .builder().productId(cartResult.getProductId())
                .productImg(cartResult.getProductImg())
                .productName(cartResult.getProductName())
                .size(cartResult.getSize())
                .productQuantity(cartResult.getProductQuantity())
                .productPrice(cartResult.getProductPrice()).build()
        ).toList();


        PageResponseDto pageInfo = PageResponseDto.builder()
                .page(currentpage)
                .size(size)
                .totalElements(total)
                .totalPage(totalPage)
                .first(currentpage == 1)
                .last(currentpage == totalPage).build();

        return CartResponseDto.builder()
                .cartItems(userCartInfoList)
                .pageResponseDto(pageInfo)
                .build();


    }

    public SizeResponseDto getSizesByProductId(SizeRequestDto sizeRequestDto) {

        log.info("CartService_getSizesByProductId 진입");

        List<Integer> availableSizes = cartMapper.findAvailableSizesByProductId(sizeRequestDto.getProductId());

        System.out.println(availableSizes);

        SizeResponseDto sizeResponseDto = SizeResponseDto.builder().sizes(availableSizes).build();

        return sizeResponseDto;


    }

}
