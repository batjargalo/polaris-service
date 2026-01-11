package mn.io.polaris.model.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class DepositTdAccountRequestDto {

    @NotNull
    private BigDecimal txnAmount;
    @NotEmpty
    private String contAcntCode;
    @NotEmpty
    private String txnDesc;

}
