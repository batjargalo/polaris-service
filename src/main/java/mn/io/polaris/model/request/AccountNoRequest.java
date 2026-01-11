package mn.io.polaris.model.request;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class AccountNoRequest {

    @NotEmpty
    private String acntCode;

}
