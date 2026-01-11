package mn.io.polaris.model.polaris;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class DueLoan {

    @Schema(description = "Төлбөр хийх өдөр")
    private Date schdDate;
    private String companyCode;
    private String acntManagerName;
    private BigDecimal amount;
    private BigDecimal theorBal;
    private BigDecimal intAmount;
    private String acntName;
    private String acntName2;
    private Integer acntManager;
    private String acntCode;
    private String custName;
    private String custName2;
    private String custCode;
    private String acntManagerName2;
    private String chartCode;
    private BigDecimal balance;
    private String chartName;
    private Integer nrsVersion;
    private String curCode;
    private String mobile;

}
