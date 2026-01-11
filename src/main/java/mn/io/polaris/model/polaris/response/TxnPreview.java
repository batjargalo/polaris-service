package mn.io.polaris.model.polaris.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.math.BigDecimal;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class TxnPreview {
    private String companyCode;
    private Integer isFee;
    private Integer sysNo;
    private String acntName;
    private String acntCode;
    private String txnType;
    private BigDecimal debitAmount;
    private BigDecimal creditAmount;
    private String txnCode;
    private String balTypeName;
    private String balTypeCode;
    private String sysName;
    private String acntType;
    private String curCode;
    private BigDecimal curRate;
    private String txnDesc;
    private String txnName;
    private Long jritemNo;
    private BigDecimal txnAmount;
}
