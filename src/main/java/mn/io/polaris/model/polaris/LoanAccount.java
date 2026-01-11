package mn.io.polaris.model.polaris;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class LoanAccount {

    @Schema(description = "Бүтээгдэхүүний нэр")
    private String prodName;

    @Schema(description = "Олгосон дүн")
    private BigDecimal advAmount;

    @Schema(description = "Үндсэн хүүгийн хувь")
    private BigDecimal baseFixedIntRate;

    @Schema(description = "Хугацааны урт")
    private Integer termLen;

    @Schema(description = "Эхэлсэн өдөр")
    private Date startDate;

    @Schema(description = "Зээл дуусах огноо")
    private Date endDate;

    private String modifiedUserName;
    private Integer readName;
    private BigDecimal billPrincBal;
    private Integer crtBillbintTopay;
    private String autooffOptionInt;
    private Integer autooffClsInt;
    private String createdUserName;
    private BigDecimal availComBal;
    private String segName;
    private Integer billCommintBalOn;
    private Integer princBalOff;
    private Integer fineGrace;
    private String termBasis;
    private Integer impairmentPer;
    private String acntManagerName;
    private BigDecimal acrBaseintBalOn;
    private Integer maxTermUnit;
    private BigDecimal acrBaseintBal;
    private BigDecimal fineMinDuebal;
    private String subPurposeName;
    private String prodType;
    private BigDecimal revolAmt;
    private String flagStoppedName2;
    private String acntManagerName2;
    private Date approvDate;
    private String subPurpose;
    private BigDecimal princBalOn;
    private BigDecimal acrCommintBal;
    private BigDecimal billFinebBal;
    private Integer notSendToCib;
    private Integer custType;
    private Date createdDatetime;
    private String repayAcntCode;
    private String name2;
    private String status;
    private String firstAcntManagerName;
    private Integer losMultiAcnt;
    private Integer autooffInt;
    private Integer flagMoveSa;
    private BigDecimal contAvailable;
    private BigDecimal approvAmount;
    private Integer secType;
    private BigDecimal billBaseintBal;
    private String className;
    private Integer flagStopped;
    private String custName2;
    private Integer allowedCam;
    private Integer repayPriority;
    private Date lastTxnDate;
    private String autochgOptionCls;
    private Integer classNoTrm;
    private BigDecimal intRcv1;
    private Integer autochgCls;
    private String statusName;
    private Integer modifiedBy;
    private String curCode;
    private BigDecimal usedComBal;
    private BigDecimal billCommintBal;
    private BigDecimal billFinepBalOn;
    private Integer classNoQlt;
    private BigDecimal billBaseintBalOff;
    private String classTrmName;
    private BigDecimal theorBal;
    private BigDecimal intAmount;
    private BigDecimal billComintBal;
    private String brchName;
    private Integer isSecure;
    private String dailyBasisCode;
    private String custName;
    private Integer flagStoppedInt;
    private BigDecimal acrBaseintBalOff;
    private Integer firstAcntManager;
    private String segCode;
    private Integer repayAcntSysNo;
    private BigDecimal billFinepBal;
    private Integer comRevolving;
    private BigDecimal totalBal;
    private Date advDate;
    private Date closedDate;
    private String autochgDueCls;
    private BigDecimal acrCommintBalOn;
    private BigDecimal intRcv1On;
    private Integer isNotAutoClass;
    private BigDecimal limit;
    private BigDecimal acrCommintBalOff;
    private String flagStoppedName;
    private BigDecimal intRcv1Off;
    private String companyCode;
    private BigDecimal princBal;
    private Integer lastBillNo;
    private Integer flagWroffPrinc;
    private Integer acntManager;
    private Integer isBrowseAcntOtherCom;
    private String purposeName;
    private Integer readTran;
    private String classQltName2;
    private Integer slevel;
    private String flagStoppedIntName;
    private Integer classNo;
    private String name;
    private Integer paymentMethod;
    private Integer isLinkedSecz;
    private Integer doTran;
    private BigDecimal billBaseintBalOn;
    private String flagStoppedIntName2;
    private String brchCode;
    private BigDecimal billFinebBalOff;
    private Integer flagWroffInt;
    private String purpose;
    private Integer flagSec;
    private Date modifiedDatetime;
    private Integer defTermUnit;
    private BigDecimal billFinepBalOff;
    private String flagMoveSaName;
    private Integer minTermUnit;
    private String repayAcntName;
    private String prodName2;
    private BigDecimal billCommintBalOff;
    private String statusName2;
    private String classQltName;
    private BigDecimal totalBill;
    private String acntCode;
    private Integer activeNrsVersion;
    private String custCode;
    private String prodCode;
    private Integer readBal;
    private BigDecimal billFinebBalOn;
    private Integer createdBy;
    private Date billBaseintDate;
    private String firstAcntManagerName2;
    private String flagMoveSaName2;
    private BigDecimal prepaidBaseintBal;
    private Date billFineDate;
    private Date billPrincDate;
    private Date nextSchdDate;
    private BigDecimal nextSchdAmt;
    private BigDecimal nextSchdInt;

}
