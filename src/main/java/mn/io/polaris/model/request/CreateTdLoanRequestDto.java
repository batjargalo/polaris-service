package mn.io.polaris.model.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class CreateTdLoanRequestDto {

    @NotEmpty
    private String tdAcntCode;

    @NotNull
    private BigDecimal txnAmount;

    private BigDecimal intRate;

}
