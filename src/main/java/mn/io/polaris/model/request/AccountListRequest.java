package mn.io.polaris.model.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class AccountListRequest {

    @NotEmpty
    private String custCode;
    @NotNull
    private int pageNumber;
    @NotNull
    private int pageSize;

}
