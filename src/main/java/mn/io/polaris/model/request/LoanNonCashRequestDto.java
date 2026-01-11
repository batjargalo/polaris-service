package mn.io.polaris.model.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class LoanNonCashRequestDto {

    @NotEmpty
    private String txnAcntCode;
    @NotNull
    private BigDecimal txnAmount;
    @NotEmpty
    private String contAcntCode;
    @NotEmpty
    private String txnDesc;

}
