package mn.io.polaris.model.polaris.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class TempAccount {

    private Integer flagNoDebit;
    private Date statusDate;
    private String createdByName;
    private BigDecimal capIntegerBal;
    private String statusSysName2;
    private Integer readName;
    private BigDecimal totalAvailBal;
    private Integer lastSeqTxn;
    private BigDecimal odIntegerRcv;
    private BigDecimal odIntegerRcvBill;
    private BigDecimal commLimit;
    private String segName;
    private String corporateAcnt;
    private String prodName;
    private BigDecimal penaltyRcv;
    private Integer flagStoppedPayment;
    private String companyCode;
    private String salaryAcnt;
    private BigDecimal comIntegerRcv;
    private BigDecimal unexIntegerRcv;
    private Integer readTran;
    private Integer flagFrozen;
    private Integer slevel;
    private BigDecimal comIntegerRcvBill;
    private String acntTypeName;
    private Integer custType;
    private Integer flagDormant;
    private Date modifiedDate;
    private String name;
    private Date createdDatetime;
    private Integer unex;
    private String odType;
    private String name2;
    private Integer doTran;
    private BigDecimal odLimit;
    private String capMethodName;
    private String brchCode;
    private BigDecimal intRate;
    private BigDecimal currentBal;
    private BigDecimal availLimit;
    private Date modifiedDatetime;
    private Integer flagStopped;
    private String odClassName2;
    private Date lastCtDate;
    private String modifiedByName;
    private BigDecimal od;
    private BigDecimal acrIntegerBal;
    private String prodName2;
    private String acntType;
    private Integer modifiedBy;
    private String curCode;
    private String statusSys;
    private Date lastDtDate;
    private String acntCode;
    private String brchName;
    private String dailyBasisCode;
    private Integer isSecure;
    private Integer monthlyWdCount;
    private String custName;
    private Integer flagStoppedInt;
    private String custCode;
    private BigDecimal availBal;
    private String prodCode;
    private Integer readBal;
    private Date createdDate;
    private String segCode;
    private String joIntegerOrSingle;
    private Integer createdBy;
    private String statusSysName;
    private String closedByName;
    private BigDecimal unexIntegerRcvBill;
    private BigDecimal passbookFacility;
    private BigDecimal blockedBal;
    private String odClassName;
    private BigDecimal paymtDefault;
    private Date openDate;
    private Integer capMethod;
    private Integer odClassNo;
    private Integer flagNoCredit;

}
