package mn.io.polaris.model.polaris;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.math.BigDecimal;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class LoanClose {

    private BigDecimal acrIntBal;
    private BigDecimal baseIntBal;
    private BigDecimal princBal;
    private Integer flagWroffPrinc;
    private Integer flagWroffInt;
    private BigDecimal fineIntBal;
    private BigDecimal acrCommIntBal;
    private String prodType;
    private BigDecimal baseCommIntBal;
    private BigDecimal totalBal;
    private BigDecimal prepaidBaseintBal;

}
