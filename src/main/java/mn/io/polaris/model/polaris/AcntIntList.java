package mn.io.polaris.model.polaris;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class AcntIntList {

    private String otherInfo;
    private String payCustName;
    private BigDecimal intRate;
    private String acntCode;
    private String sourceBalType;
    private String dailyBasisCode;
    private String type;
    private String intRateOption;
    private String intTypeName;
    private BigDecimal accrIntAmt;
    private BigDecimal intRuleRate;
    private BigDecimal dailyIntAmt;
    private BigDecimal accrIntAmtOff;
    private BigDecimal accrIntAmtOn;
    private String balTypeCode;
    private String intTypeCode;
    private Integer flagOffbal;
    private String lastAcrInfo;
    private String intRateCode;
    private int spreadRuleId;
    private int spread;
    private int lastAcrTxnSeq;
    private int lastAcrAmt;
    private String intRateName;
    private Date lastAccrualDate;

}
