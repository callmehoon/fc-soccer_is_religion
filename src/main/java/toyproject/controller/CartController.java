package toyproject.controller;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import toyproject.controller.dto.*;
import toyproject.controller.viewmodel.CartListViewModel;
import toyproject.service.CartService;

import java.util.List;

@Controller
@Slf4j
@RequestMapping("/cart")
@RequiredArgsConstructor
public class CartController {

    private final CartService cartService;

    @GetMapping("")
    public String cart(@ModelAttribute CartRequestDto cartRequestDto, @ModelAttribute PageRequestDto pageRequestDto, Model model) {

        log.info("Cart Controller _ 기본 페이지 진입");

        // 우선 하드코딩
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

        CartListViewModel cartListViewModel = CartListViewModel.builder().cartList(cartInfo.getCartItems())
                .pageInfo(cartPageInfo)
                .build();

        model.addAttribute("cartListViewModel", cartListViewModel);

        return "cart";
    }


    @GetMapping(value = "/option/size" , produces = "application/json")
    @ResponseBody
    public SizeResponseDto getAvailableSizes(@ModelAttribute SizeRequestDto sizeRequestDto){

        return cartService.getSizesByProductId(sizeRequestDto);

    }




}
