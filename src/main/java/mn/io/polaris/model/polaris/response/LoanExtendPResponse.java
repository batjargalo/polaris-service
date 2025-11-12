package mn.io.polaris.model.polaris.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
// @AllArgsConstructor
// @NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class LoanExtendPResponse {

    private String txnPreview;
    private Integer isSupervisor;
    private Integer isPreviewFee;
    private String feesPreview;
    private Integer txnJrno;

}
