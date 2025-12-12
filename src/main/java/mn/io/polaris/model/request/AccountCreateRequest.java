package mn.io.polaris.model.request;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import lombok.Data;
import mn.io.polaris.helper.Utils;

import java.util.Date;

@Data
public class AccountCreateRequest {

    private String acntType;
    private String prodCode;
    private String brchCode;
    private String curCode;
    private String custCode;
    private String name;
    private String name2;
    private String slevel;
    private String statusCustom;
    private String jointOrSingle;
    private String dormancyDate;
    private String statusDate;
    private String flagNoCredit;
    private String flagNoDebit;
    private String salaryAcnt;
    private String corporateAcnt;
    private String capAcntCode;
    private String capMethod;
    private String segCode;
    private String paymtDefault;
    private String odType;

    public String toJsonString() {
        Gson gson = new GsonBuilder()
                .registerTypeAdapter(Date.class, new Utils.DateSerializer())
                .create();
        return gson.toJson(this);
    }

}
