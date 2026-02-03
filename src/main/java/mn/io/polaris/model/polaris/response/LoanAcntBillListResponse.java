package mn.io.polaris.model.polaris.response;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
// @AllArgsConstructor
// @NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class LoanAcntBillListResponse {

    private String companyCode;
    private Integer paidAmountReal;
    private String billTypeCode;
    private Integer sysNo;
    private String acntName;
    private BigDecimal totalBalance;
    private String acntCode;
    private Integer finePaidAmount;
    private BigDecimal billAmountReal;
    private String billDate;
    private String custCode;
    private BigDecimal billAmount;
    private String sysName;
    private BigDecimal fineAmount;
    private BigDecimal fineRoundAmount;
    private String curCode;
    private Integer fineGraceDay;
    private String billTypeName;
    private String rn;
    private Integer billNo;
    private BigDecimal paidAmount;
    private String fineLastAccrualDate;
    private Integer isCompleted;

}
