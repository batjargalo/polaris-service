package mn.io.polaris.model.polaris.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.math.BigDecimal;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class NRSListDto {

    private BigDecimal totalAmount;
    private BigDecimal amount;
    private BigDecimal intAmount;
    private String schdDate;
    private BigDecimal theorBal;
    private String acntCode;
    private Integer rn;

}
