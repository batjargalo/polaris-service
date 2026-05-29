package mn.io.polaris.model.request;

import java.math.BigDecimal;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ArcvNonCashPayRequest {

    @NotEmpty
    private String acntCode;
    @NotEmpty
    private String txnDesc;
    @NotNull
    private BigDecimal txnAmount;

}
