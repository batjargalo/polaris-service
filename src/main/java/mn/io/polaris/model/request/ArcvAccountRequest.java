package mn.io.polaris.model.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ArcvAccountRequest {

    @NotEmpty
    private String acntCode;
    @NotEmpty
    private String custCode;
    @NotNull
    private int pageNumber;
    @NotNull
    private int pageSize;

}
