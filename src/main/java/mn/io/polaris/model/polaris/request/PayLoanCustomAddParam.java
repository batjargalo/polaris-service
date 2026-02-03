package mn.io.polaris.model.polaris.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PayLoanCustomAddParam {

    @SerializedName("PAYCUSTCODE")
    @JsonProperty("PAYCUSTCODE")
    private String payCustCode;

    private String contAcntType;

    private BigDecimal princPayAmt;
    private BigDecimal billIntPayAmt;
    private BigDecimal intPayAmt;
    private BigDecimal finePayAmt;
    private BigDecimal billFinepBal;
    private BigDecimal billFinebBal;
    private BigDecimal billCommIntPayAmt;
    private BigDecimal commIntPayAmt;

}
