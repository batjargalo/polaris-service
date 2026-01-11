package mn.io.polaris.model.polaris;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class LoanRepaymentInfo {

    public String modifiedUserName;
    public BigDecimal intRate;
    public Date endDate;
    public Integer holidayOption;
    public Integer shiftPartialPay;
    public String description;
    public Date modifiedDatetime;
    public String payFreq;
    public BigDecimal payConstAmt;
    public Integer payType;
    public String createdUserName;
    public Integer payDay1;
    public Integer isFixedPayment;
    public Integer modifiedBy;
    public BigDecimal calcAmt;
    public Integer termFreeTimes;
    public Integer shiftType;
    public Integer addAccrIntAmt;
    public String acntCode;
    public Integer version;
    public BigDecimal payAmt;
    public String intRateOption;
    public Integer createdBy;
    public String intTypeCode;
    public Date createdDatetime;
    public Integer isCalc;
    public Date startDate;

}
