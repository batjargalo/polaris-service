package mn.io.polaris.model.polaris.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Map;
import java.math.BigDecimal;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class LoanExtendPResponse {

    // private String txnPreview;
    private List<Object> txnPreview;
    private Integer svCount;
    private Integer isPreview;
    private Integer tdBreakPenalty;
    private Integer isPreviewFee;
    private List<Object> subJrnos;
    private Integer jrItemNoAndIncr;
    private Integer deductedFeeAmtFromTxn;
    private Integer deductedFeeAmtFromAcnt;
    private Integer deductedFeeAmtFromTxnCont;
    private Integer isSupervisor;
    private Integer feeException;
    private BigDecimal txnJrno;
    // private String feesPreview;
    private List<Object> feesPreview;

    // // Getters and setters
    // public Map<String, List<Object>> getData() {
    // return data;
    // }

    // public void setFeesPreview(Map<String, List<Object>> feesPreview) {
    // this.feesPreview = feesPreview;
    // }

}
