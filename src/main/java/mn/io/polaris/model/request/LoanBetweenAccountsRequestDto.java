package mn.io.polaris.model.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class LoanBetweenAccountsRequestDto {

    @NotNull
    private BigDecimal txnAmount;
    @NotEmpty
    private String txnDesc;
    @NotEmpty
    private String txnAcntCode;

}
