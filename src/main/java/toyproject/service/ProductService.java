package toyproject.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import toyproject.dto.CartRequestDto;
import toyproject.dto.CartResponseDto;
import toyproject.dto.ProductResponseDto;
import toyproject.mapper.CartMapper;
import toyproject.mapper.ProductMapper;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductMapper productMapper;

    public ProductResponseDto productDetail(ProductResponseDto productResponseDto) {

        ProductResponseDto productDetail = productMapper.selectProductDetail(productResponseDto.getProductID());

        return productDetail;


    }

}
