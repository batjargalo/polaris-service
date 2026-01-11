package mn.io.polaris.model.request;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class CustomerInfoRequest {

    @NotEmpty
    private String registrationId;

}
