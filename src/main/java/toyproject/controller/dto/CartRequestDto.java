package toyproject.controller.dto;

import lombok.Builder;
import lombok.Data;
import org.springframework.web.bind.annotation.ResponseBody;

@Data
@Builder
public class CartRequestDto {
    private String userId;

}
