package toyproject.controller;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import toyproject.controller.dto.*;
import toyproject.controller.viewmodel.CartListViewModel;
import toyproject.mapper.queryparam.UserCartByIDQueryParam;
import toyproject.mapper.queryparam.UserCartUpdateQueryParam;
import toyproject.service.CartService;

import java.util.List;
import java.util.Map;

@Controller
@Slf4j
@RequestMapping("/cart")
@RequiredArgsConstructor
public class CartController {

    private final CartService cartService;

    @GetMapping("")
    public String cart(@ModelAttribute CartRequestDto cartRequestDto, @ModelAttribute PageRequestDto pageRequestDto, Model model) {

        log.info("Cart Controller _ 기본 페이지 진입");

        cartRequestDto.setUserId("U01357");

        CartResponseDto cartInfo = cartService.searchCart(cartRequestDto, pageRequestDto);

        PageResponseDto pageInfo = cartInfo.getPageResponseDto();

        PageResponseDto cartPageInfo = PageResponseDto.builder()
                .page(pageInfo.getPage())
                .size(pageInfo.getSize())
                .totalElements(pageInfo.getTotalElements())
                .totalPage(pageInfo.getTotalPage())
                .first(pageInfo.isFirst())
                .last(pageInfo.isLast()).
                build();

        CartListViewModel cartListViewModel = CartListViewModel.builder()
                .cartPriceInfo(cartInfo.getPriceInfo())
                .cartList(cartInfo.getCartItems())
                .pageInfo(cartPageInfo)
                .build();

        model.addAttribute("cartListViewModel", cartListViewModel);

        return "cart";
    }


    @GetMapping(value = "/option/size" , produces = "application/json")
    @ResponseBody
    public List<SizeResponseDto> getAvailableSizes(@ModelAttribute SizeRequestDto sizeRequestDto){

        log.info("Cart Controller _ /option/size");

        return cartService.getSizesByProductId(sizeRequestDto);

    }

@PostMapping("/update")
@ResponseBody
    public ResponseEntity<Void> updateCart(@RequestBody CartUpdateRequestDto request) {

    log.info("Cart Controller _ /update");
    System.out.println(request.toString());
    cartService.updateCartOption("U01357", request); // 내부적으로 삭제 후 insert 로직
    return ResponseEntity.ok().build();
}

@PostMapping("/add")
@ResponseBody
public ResponseEntity<Void> addToCart(@RequestBody List<CartInsertDto> items){
        for(CartInsertDto item : items){
            cartService.insertCartItem(item);
        }
        return ResponseEntity.ok().build();
}


}
