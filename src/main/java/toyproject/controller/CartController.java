package toyproject.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import toyproject.controller.dto.*;
import toyproject.controller.viewmodel.CartListViewModel;
import toyproject.service.CartService;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@Slf4j
@RequestMapping("/cart")
@RequiredArgsConstructor
public class CartController {

    private final CartService cartService;

    @GetMapping("")
    public String cart(HttpSession httpSession, @ModelAttribute PageRequestDto pageRequestDto, Model model) {

        log.info("Cart Controller _ 기본 페이지 진입");

        CartRequestDto cartRequestDto = CartRequestDto.builder().userId("U01357").build();

        CartResponseDto cartInfo = cartService.searchCart(cartRequestDto, pageRequestDto);

        CartListViewModel cartListViewModel = CartListViewModel.builder()
                .cartPriceInfo(cartInfo.getPriceInfo())
                .cartList(cartInfo.getCartItems())
                .pageInfo(cartInfo.getPageResponseDto())
                .build();

        model.addAttribute("cartListViewModel", cartListViewModel);

        return "cart";
    }

    @GetMapping("/items")
    @ResponseBody
    public CartResponseDto getCartItems(@ModelAttribute PageRequestDto pageRequestDto) {

        CartRequestDto cartRequestDto = CartRequestDto.builder().userId("U01357").build();

        return cartService.searchCart(cartRequestDto, pageRequestDto);
    }


    @GetMapping(value = "/option/size", produces = "application/json")
    @ResponseBody
    public List<SizeResponseDto> getAvailableSizes(@ModelAttribute SizeRequestDto sizeRequestDto) {

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

    @DeleteMapping("/delete")
    @ResponseBody
    public ResponseEntity<Void> deleteSelectedItems(HttpSession httpSession, @RequestBody CartDeleteRequestDto deleteRequestDto) {

        log.info("Cart Controller _ /delete");

        cartService.deleteCartItems("U01357", deleteRequestDto);

        return ResponseEntity.ok().build();
    }


}
