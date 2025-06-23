package toyproject.mapper.queryparam;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserCartIemQuantityQueryParam {

    private String userId;
    private int productId;
    private int size;
}
