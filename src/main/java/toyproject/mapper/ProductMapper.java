package toyproject.mapper;

import org.w3c.dom.ls.LSException;
import toyproject.dto.CartResponseDto;
import toyproject.dto.ProductResponseDto;

import java.util.List;
import java.util.Map;

public interface ProductMapper {
    public ProductResponseDto selectProductDetail(int productID);
    public List<Map<String,Object>> productSize(int productID);


}
