package mn.io.polaris.model.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class InfoRequest {

    @NotEmpty
    private String acntCode;
    @NotNull
    private int getWithSecure;

}
