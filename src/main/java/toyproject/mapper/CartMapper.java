package toyproject.mapper;

import toyproject.mapper.queryparam.UserCartDeleteQueryParam;
import toyproject.mapper.queryparam.UserCartByIDQueryParam;
import toyproject.mapper.queryparam.UserCartItemQuantityQueryParam;
import toyproject.mapper.queryparam.UserCartUpdateQueryParam;
import toyproject.mapper.result.SizeStockResult;
import toyproject.mapper.result.UserCartPriceResult;
import toyproject.mapper.result.UserCartResult;

import java.util.List;

public interface CartMapper {
    List<SizeStockResult> findAvailableSizesByProductId(int productId);

    int findCartItemsCountByUserId(UserCartByIDQueryParam userCartByIDQueryParam);

    List<UserCartResult> findCartItemsByUserId(UserCartByIDQueryParam userCartByIDQueryParam);

    UserCartPriceResult findCartItemsPriceByUserId(UserCartByIDQueryParam userCartByIDQueryParam);
    Integer findCartItemBySize(UserCartItemQuantityQueryParam userCartItemQuantityQueryParam);

    void updateCartItemQuantity(UserCartUpdateQueryParam userCartUpdateQueryParam);
    void deleteCartItem(UserCartDeleteQueryParam cartDeleteQueryParam);
    void insertCartItem(UserCartUpdateQueryParam cartUpdateQueryParam);

}
