package mn.io.polaris.model.polaris;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import lombok.Data;
import mn.io.polaris.helper.Utils;

import java.util.Date;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Retail {

    private Integer isPolitical;
    private String modifiedUserName;
    private String lastName;
    private String firstName2;
    private String countryName2;
    private Date modifiedDatetime;
    private String createdUserName;
    private String countryCode;
    private Integer taxExemption;
    private String sexName;
    private String statusName;
    private String lastName2;
    private Integer modifiedBy;
    private String statusName2;
    private String custSegCode;
    private String registerCode;
    private String email;
    private String companyCode;
    private String registerMaskCode;
    private Integer isBl;
    private Integer isCompanyCustomer;
    private String mobile;
    private String fullName;
    private String fullName2;
    private String custCode;
    private Date birthDate;
    private Integer isVatPayer;
    private String firstName;
    private Integer sexCode;
    private String phone;
    private Integer createdBy;
    private Date lastRenewDate;
    private String shortName2;
    private Date createdDatetime;
    private String countryName;
    private String sexName2;
    private String shortName;
    private Integer status;
    private String idCardNo;
    private String familyName;
    private String familyName2;
    private String nationalityName;
    private String nationalityName2;
    private String fax;
    private String eduName;
    private String eduName2;
    private String ethnicGrpName;
    private String ethnicGrpName2;
    private String immovableType;
    private String brchCode;
    private String birthPlaceDetail;
    private String birthPlaceName;
    private String birthPlaceName2;
    private String eBarimtNo;
    private String maritalStatusName;
    private String maritalStatusName2;
    private String industryName;
    private String ownerShip;
    private Integer birthPlaceId;
    private Integer titleId;
    private Integer employmentId;
    private Integer familyCnt;
    private Integer industryId;
    private Integer educationId;
    private Integer nationalityId;
    private Integer ethnicGroupId;
    private Integer slevel;
    private Integer maritalStatus;
    private Integer langCode;
    private Integer totalLoan;
    private Integer workerCnt;
    private Integer monthlyIncome;

    public String toJsonString() {
        Gson gson = new GsonBuilder()
                .registerTypeAdapter(Date.class, new Utils.DateSerializer())
                .create();
        return gson.toJson(this);
    }

}
