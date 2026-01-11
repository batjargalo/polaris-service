package mn.io.polaris.model.request;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class LoanRepaymentRequest {

    @NotEmpty
    private String acntCode;

}
