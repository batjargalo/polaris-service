package mn.io.polaris.model.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class LoanCloseDto {

    @Schema(description = "Нийт үлдэгдэл")
    private BigDecimal totalBal;

}
