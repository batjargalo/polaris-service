package mn.io.polaris.model.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class LoanExtensionResponse {

    private String txnPreview;
    private Integer isSupervisor;
    private Integer isPreviewFee;
    private String feesPreview;
    private Integer txnJrno;
}