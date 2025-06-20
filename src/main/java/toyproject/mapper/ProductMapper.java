package toyproject.mapper;

import toyproject.dto.CartResponseDto;
import toyproject.dto.ProductResponseDto;

import java.util.List;

public interface ProductMapper {
    public ProductResponseDto selectProductDetail(int productID);

}
