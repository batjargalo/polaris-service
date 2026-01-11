package mn.io.polaris.model.polaris.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoanAcnt {

    private String custCode;
    private String name;
    private String name2;
    private String prodCode;
    private String curCode;
    private BigDecimal approvAmount;
    private Date approvDate;
    private Date startDate;
    private Integer termLen;
    private String termBasis;
    private Date endDate;
    private String purpose;
    private String subPurpose;
    private Integer isNotAutoClass;
    private Integer comRevolving;
    private String dailyBasisCode;
    private Integer acntManager;
    private String brchCode;
    private Integer isGetBrchFromOutside;
    private String segCode;
    private String status;
    private Integer slevel;
    private Integer classNoTrm;
    private Integer classNoQlt;
    private Integer classNo;
    private String repayAcntCode;
    private Integer isBrowseAcntOtherCom;
    private Integer repayPriority;
    private Integer losMultiAcnt;
    private Integer impairmentPer;
    private Integer validLosAcnt;
    private String prodType;
    private Integer secType;

}
