package mn.io.polaris.model.request;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class AccountCasaCreateRequest {

    @NotEmpty
    private String custCode;

    @NotEmpty
    private String name;

    @NotEmpty
    private String name2;

}
