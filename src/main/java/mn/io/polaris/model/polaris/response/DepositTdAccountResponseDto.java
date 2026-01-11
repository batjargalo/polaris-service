package mn.io.polaris.model.polaris.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class DepositTdAccountResponseDto {

    private Integer svCount;
    private List<TxnPreview> txnPreview;
    private BigDecimal tdBreakPenalty;
    private Integer isPreviewFee;
    private List<Object> subJrnos;
    private BigInteger jrItemNoAndIncr;
    private BigDecimal deductedFeeAmtFromTxn;
    private BigDecimal deductedFeeAmtFromAcnt;
    private BigDecimal deductedFeeAmtFromTxnCont;
    private Integer isSupervisor;
    private BigDecimal feeException;
    private BigInteger txnJrno;
    private List<Object> feesPreview;
    private BigInteger txnJritemNo;
    private String acntCode;
    private String caAcntCode;

}
