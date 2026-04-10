package mn.io.polaris.model.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class LoanCloseRequest {

    @NotEmpty
    private String acntCode;
    @NotEmpty
    private String companyCode;
    @NotEmpty
    @JsonFormat(pattern = "yyyy-MM-dd")
    private String closeDate;

}
