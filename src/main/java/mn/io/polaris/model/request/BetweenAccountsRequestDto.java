package mn.io.polaris.model.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class BetweenAccountsRequestDto {

    @NotNull
    private BigDecimal txnAmount;
    @NotEmpty
    private String txnDesc;
    private TxnType txnType = TxnType.LOAN;

    public enum TxnType {
        LOAN,
        TD
    }
}
