package toyproject.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class SizeResponseDto {
        private List<Integer> sizes;

}
