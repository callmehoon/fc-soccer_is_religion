package toyproject.dto;

import lombok.Data;

import java.util.List;

@Data
public class OrderRequestDto {
    private List<Integer> productId;
}
