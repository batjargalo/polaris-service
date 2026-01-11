package mn.io.polaris.model.request;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class AccountCreateAllInOneRequest {

    @NotEmpty
    private String custCode;

    @NotEmpty
    private String name;

    @NotEmpty
    private String name2;

    @NotEmpty
    private String termLen;

    @NotEmpty
    private String prodCode;

}
