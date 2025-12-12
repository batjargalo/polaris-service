package mn.io.polaris.model.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class PreviewCloseTdAccountResponseDto {

    @Schema(description = "Шилжүүлэх дүн")
    private BigDecimal totalBal;

    @Schema(description = "НӨАТ дүн")
    private BigDecimal taxBal;

    @Schema(description = "Хүүний дүн")
    private BigDecimal intBal;

}
