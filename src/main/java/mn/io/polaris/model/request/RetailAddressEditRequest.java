package mn.io.polaris.model.request;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class RetailAddressEditRequest {

    @NotEmpty
    private String custCode;

    private Integer addrId;

    @NotEmpty
    private String cityTown;

    @NotEmpty
    private String street;

    @NotEmpty
    private String blockDoor;

    @NotEmpty
    private String addrDetail;

}
