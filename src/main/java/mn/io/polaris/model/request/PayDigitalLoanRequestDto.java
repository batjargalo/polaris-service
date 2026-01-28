package mn.io.polaris.model.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class PayDigitalLoanRequestDto {

    @NotNull
    private BigDecimal txnAmount;
    @NotEmpty
    private String txnAcntCode;
    @NotEmpty
    private String contAcntType;
    @NotNull
    private BigDecimal princPayAmt;
    @NotNull
    private BigDecimal billIntPayAmt;
    @NotNull
    private BigDecimal intPayAmt;
    @NotNull
    private BigDecimal finePayAmt;
    @NotNull
    private BigDecimal billFinepBal;
    @NotNull
    private BigDecimal billFinebBal;
    @NotNull
    private BigDecimal billCommIntPayAmt;
    @NotNull
    private BigDecimal commIntPayAmt;

}
