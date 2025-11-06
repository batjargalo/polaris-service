package mn.io.polaris.model.polaris;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import lombok.Data;
import mn.io.polaris.helper.Utils;

import java.util.Date;

@Data
public class CorporateNew {

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

    public String toJsonString() {
        Gson gson = new GsonBuilder()
                .registerTypeAdapter(Date.class, new Utils.DateSerializer())
                .create();
        return gson.toJson(this);
    }

}
