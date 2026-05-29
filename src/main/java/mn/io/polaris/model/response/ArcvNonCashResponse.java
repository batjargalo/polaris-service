package mn.io.polaris.model.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class ArcvNonCashResponse {
    @Schema(description = "Гүйлгээний журнал дугаар")
    private String txnJrno;
}
