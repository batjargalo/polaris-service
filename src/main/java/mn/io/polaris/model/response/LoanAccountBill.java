package mn.io.polaris.model.response;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class LoanAccountBill {

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
