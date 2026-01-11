package mn.io.polaris.model.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class RetailNewAllInOneRequest {

    //Харилцагч
    @NotEmpty
    private String sexCode;
    private Integer industryId;
    @NotEmpty
    private String familyName;
    @NotEmpty
    private String familyName2;
    @NotEmpty
    private String lastName;
    @NotEmpty
    private String lastName2;
    @NotEmpty
    private String firstName;
    @NotEmpty
    private String firstName2;
    @NotEmpty
    private String shortName;
    @NotEmpty
    private String shortName2;
    @NotEmpty
    private String registerCode;
    @NotEmpty
    private String birthDate;
    @NotEmpty
    private String mobile;
    @NotEmpty
    private String email;
    private String maritalStatus;
    private String birthPlaceDetail;
    @NotEmpty
    private String phone;
    @Schema(description = "Харилцагчийн дансны дугаар")
    private String fax;
    private Integer employmentId;
    private Integer titleId;
    private Integer nationalityId;
    private Integer educationId;
    private Integer ethnicGroupId;
    private Integer familyCnt;
    private Integer workerCnt;

    //Хаяг
    private Integer addrId;
    private String cityTown;
    private String street;
    private String blockDoor;
    private String addrDetail;

}
