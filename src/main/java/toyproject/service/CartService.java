package toyproject.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import toyproject.controller.dto.*;
import toyproject.mapper.CartMapper;
import toyproject.mapper.queryparam.UserCartByIDQueryParam;
import toyproject.mapper.queryparam.UserCartDeleteQueryParam;
import toyproject.mapper.queryparam.UserCartItemQuantityQueryParam;
import toyproject.mapper.queryparam.UserCartUpdateQueryParam;
import toyproject.mapper.result.SizeStockResult;
import toyproject.mapper.result.UserCartPriceResult;
import toyproject.mapper.result.UserCartResult;

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

        UserCartByIDQueryParam userCartByIDQueryParam = UserCartByIDQueryParam.builder()
                .userId(requestDto.getUserId())
                .offset((currentpage - 1) * size)
                .size(size)
                .build();

        int total = cartMapper.findCartItemsCountByUserId(userCartByIDQueryParam);

        int totalPage = total / size + 1;

        List<UserCartResult> userUserCartResultList = cartMapper.findCartItemsByUserId(userCartByIDQueryParam);

        List<CartInfoDto> userCartInfoList = userUserCartResultList.stream().map(userCartResult -> CartInfoDto
                .builder().productId(userCartResult.getProductId())
                .productImg(userCartResult.getProductImg())
                .productName(userCartResult.getProductName())
                .size(userCartResult.getSize())
                .cartProductQuantity(userCartResult.getCartProductQuantity())
                .stockQuantity(userCartResult.getStockQuantity())
                .productPrice(userCartResult.getProductPrice()).build()
        ).toList();

        UserCartPriceResult userCartPriceResult = cartMapper.findCartItemsPriceByUserId(userCartByIDQueryParam);

        CartTotalPrice cartTotalPrice = CartTotalPrice.builder().
                totalProductCount(userCartPriceResult.getTotalProductCount()).
                totalPrice(userCartPriceResult.getTotalPrice()).
                totalDiscount(userCartPriceResult.getTotalDiscount())
                .build();


        PageResponseDto pageInfo = PageResponseDto.builder()
                .page(currentpage)
                .size(size)
                .totalElements(total)
                .totalPage(totalPage)
                .first(currentpage == 1)
                .last(currentpage == totalPage).build();

        return CartResponseDto.builder()
                .priceInfo(cartTotalPrice)
                .cartItems(userCartInfoList)
                .pageResponseDto(pageInfo)
                .build();


    }

    public List<SizeResponseDto> getSizesByProductId(SizeRequestDto sizeRequestDto) {

        log.info("CartService_getSizesByProductId 진입");

        List<SizeStockResult> availableSizes = cartMapper.findAvailableSizesByProductId(sizeRequestDto.getProductId());

        log.info("availableSizes" + availableSizes);

        List<SizeResponseDto> sizeResponseDtoList = availableSizes.stream().map(result -> SizeResponseDto.builder().size(result.getSize()).stockQuantity(result.getStockQuantity()).build()).toList();

        return sizeResponseDtoList;


    }

    @Transactional
    public void updateCartOption(String userId, CartUpdateRequestDto dto) {

        if (dto.getPrevSize() != dto.getNewSize()) {

            UserCartDeleteQueryParam userCartDeleteQueryParam = UserCartDeleteQueryParam
                    .builder()
                    .userId(userId)
                    .productId(dto.getProductId())
                    .size(dto.getPrevSize())
                    .build();

            cartMapper.deleteCartItem(userCartDeleteQueryParam);

            UserCartItemQuantityQueryParam userCartItemQuantityQueryParam = UserCartItemQuantityQueryParam.builder()
                    .userId(userId)
                    .productId(dto.getProductId())
                    .size(dto.getNewSize())
                    .build();

            // 존재하면 cart 옵션 수량 업데이트 , 존재하지 않으면 cart 옵션 수량 insert

            Integer sizeItemQuantity = cartMapper.findCartItemBySize(userCartItemQuantityQueryParam);

            UserCartUpdateQueryParam userCartUpdateQueryParam = UserCartUpdateQueryParam
                    .builder()
                    .userId(userId)
                    .productId(dto.getProductId())
                    .size(dto.getNewSize())
                    .productQuantity(dto.getNewQuantity())
                    .build();

            if (sizeItemQuantity == null) {
                cartMapper.insertCartItem(userCartUpdateQueryParam);
            } else {
                userCartUpdateQueryParam.setQuantityToCartItem(sizeItemQuantity + dto.getNewQuantity());
                cartMapper.updateCartItemQuantity(userCartUpdateQueryParam);
            }


        } else {

            UserCartUpdateQueryParam userCartUpdateQueryParam = UserCartUpdateQueryParam
                    .builder()
                    .userId(userId)
                    .productId(dto.getProductId())
                    .size(dto.getNewSize())
                    .productQuantity(dto.getNewQuantity())
                    .build();

            cartMapper.updateCartItemQuantity(userCartUpdateQueryParam);

        }

    }


//    public void deleteCartItems(CartDeleteRequestDto cartDeleteRequestDto){
//
//        cartMapper.deleteCartItems();
//
//    }

    public void insertCartItem(CartInsertDto dto) {
        cartMapper.insertCartItem(dto);
    }


}
