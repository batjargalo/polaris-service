package mn.io.polaris.model.polaris;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import lombok.Data;
import mn.io.polaris.helper.Utils;

import java.util.Date;

@Data
public class RetailNew {

    private String custSegCode;
    private String sexCode;
    private String taxExemption;
    private String status;
    private Integer isCompanyCustomer;
    private Integer industryId;
    private Integer birthPlaceId;
    private String familyName;
    private String familyName2;
    private String lastName;
    private String lastName2;
    private String firstName;
    private String firstName2;
    private String shortName;
    private String shortName2;
    private String registerMaskCode;
    private String registerCode;
    private String birthDate;
    private String mobile;
    private String countryCode;
    private String email;
    private String industryName;
    private String catId;
    private String langCode;
    private String maritalStatus;
    private String birthPlaceName;
    private String birthPlaceDetail;
    private String phone;
    private String fax;
    private Integer employmentId;
    private Integer titleId;
    private Integer nationalityId;
    private Integer educationId;
    private Integer ethnicGroupId;
    private Integer familyCnt;
    private Integer workerCnt;

    public String toJsonString() {
        Gson gson = new GsonBuilder()
                .registerTypeAdapter(Date.class, new Utils.DateSerializer())
                .create();
        return gson.toJson(this);
    }

}
