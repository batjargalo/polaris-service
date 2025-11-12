package mn.io.polaris.model.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class CreateRepaymentRequest {

    @NotEmpty
    private String acntCode;
    @NotNull
    private BigDecimal calcAmt;
    @NotNull
    private String payType;
    @NotEmpty
    private String payFreq;
    @NotNull
    private Integer payDay1;
    @NotEmpty
    private String endDate;
    @NotEmpty
    private String description;

}
