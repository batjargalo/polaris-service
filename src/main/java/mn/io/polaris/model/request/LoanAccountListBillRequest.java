package mn.io.polaris.model.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class LoanAccountListBillRequest {

    @NotEmpty
    private String acntCode;
    // @NotEmpty
    // private String prodCode;
    @NotNull
    private Integer pageNumber;
    @NotNull
    private Integer pageSize;

}
