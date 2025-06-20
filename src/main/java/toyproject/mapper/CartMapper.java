package toyproject.mapper;

import toyproject.mapper.queryparam.CartQueryParam;
import toyproject.mapper.result.CartResult;

import java.util.List;

public interface CartMapper {
    public List<Integer> findAvailableSizesByProductId(int productId);
    public int findCartItemsCountByUserId(CartQueryParam cartQueryParam);
    public List<CartResult> findCartItemsByUserId(CartQueryParam cartQueryParam);

}
