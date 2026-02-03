package mn.io.polaris.model.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class EditBillRequestDto {

    @NotEmpty
    private String acntCode;
    @NotNull
    private BigDecimal txnAmount;
    @NotNull
    private Integer billNo;
}
