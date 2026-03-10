package mn.io.polaris.model.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ZmsNotSendRequest {

    @NotEmpty
    private String acntCode;
    @NotNull
    private Integer send_option;

}
