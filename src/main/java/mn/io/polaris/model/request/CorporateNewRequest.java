package mn.io.polaris.model.request;

import lombok.Data;

@Data
public class CorporateNewRequest {

    private String status;
    private Integer isCompanyCustomer;
    private String foundedDate;
    private Integer industryId;
    private String name;
    private String registerMaskCode;
    private String shortName;
    private String industryName;
    private String name2;
    private String custSegCode;
    private String registerCode;
    private String shortName2;
    private String countryCode;
    private String phone;
    private String email;

}
