package toyproject.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import toyproject.dto.ProductResponseDto;
import toyproject.service.ProductService;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/product")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;
    @GetMapping("/detail") // 네이밍 직관적으로 변경하기위해 /read -> /detail로 수정 by 홍성훈
    public String detail(@RequestParam("productID") Integer productId, Model m) {
        System.out.println("controller");
        try{
            ProductResponseDto paramDto= ProductResponseDto.builder()
            .productID(productId)
            .build();

            System.out.println("넘어온 productId = " + productId);

            ProductResponseDto productDto = productService.productDetail(paramDto);
            List<Map<String,Object>> sizeList=productService.productSize(paramDto);
            System.out.println("가져온 productDto = " + productDto);
            m.addAttribute("productDto", productDto);
            m.addAttribute("sizeList", sizeList);
            System.out.println(">> sizeList = " + sizeList);
            System.out.println(">> productDto = " + productDto);


        }catch(Exception e){
            e.printStackTrace();
        }
        return "product_detail";
    }
}



