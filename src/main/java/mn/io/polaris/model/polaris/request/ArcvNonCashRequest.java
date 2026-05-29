package mn.io.polaris.model.polaris.request;

import java.math.BigDecimal;
import java.util.List;

import lombok.Data;

@Data
public class ArcvNonCashRequest {

    private String txnAcntCode;
    private String contAcntType;
    private String contAcntCode;
    private String txnDesc;
    private Integer rate;
    private String rateTypeId;
    private BigDecimal contAmount;
    private BigDecimal txnAmount;
    private String curCode;
    private Integer contSysNo;
    private List<SubTxns> subTxns;
    private Integer txnSysNo;
    private String payCustCode;
    private Integer contRate;
    private String txnDefCode;
    private String changeBanknotes;
    private Integer isTmw;
    private String identityType;
    private String scrCode;
    private Integer tcustType;
    private String tcustCode;
    private String tcustName;
    private String tcustAddr;
    private String tcustRegister;
    private String tcustRegisterMask;
    private String tcustContact;
    private String chartType;
    private String contChartType;
    private String chartCode;
    private String sourceType;
    private Integer isPreview;
    private Integer isPreviewFee;
    private String acntType;
    private String contChartCode;
    private String dtOrCtDominant;
    private String contCurCode;

}
