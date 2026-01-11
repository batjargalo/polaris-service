package mn.io.polaris.model.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class WithdrawTdAccountRequestDto {

    @NotEmpty
    private String txnAcntCode;

    @NotNull
    BigDecimal txnAmount;

}
