package mn.io.polaris.model.polaris;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.Date;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class CustomerInfo {

    private String lastName;
    private String firstName2;
    private String familyName2;
    private String langCode;
    private Date modifiedDatetime;
    private String identityType;
    private String countryCode;
    private String familyName;
    private String lastName2;
    private Integer modifiedBy;
    private String fax;
    private String registerCode;
    private String email;
    private String companyCode;
    private String registerMaskCode;
    private String mobile;
    private Date birthDate;
    private String custCode;
    private String firstName;
    private Integer sexCode;
    private Integer createdBy;
    private String phone;
    private String shortName2;
    private Integer custType;
    private Date createdDatetime;
    private String shortName;
    private Integer status;

}
