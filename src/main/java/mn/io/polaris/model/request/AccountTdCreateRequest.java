package mn.io.polaris.model.request;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import lombok.Data;
import mn.io.polaris.helper.Utils;

import java.util.Date;

@Data
public class AccountTdCreateRequest {

    private String prodCode;
    private String slevel;
    private String capMethod;
    private String capAcntCode;
    private String capAcntSysNo;
    private String startDate;
    private String maturityOption;
    private String rcvAcntCode;
    private String brchCode;
    private String curCode;
    private String name;
    private String name2;
    private String termLen;
    private String maturityDate;
    private String custCode;
    private String segCode;
    private String jointOrSingle;
    private String statusCustom;
    private String statusDate;
    private String casaAcntCode;
    private String closedBy;
    private String closedDate;
    private String lastCtDate;
    private String lastDtDate;

    public String toJsonString() {
        Gson gson = new GsonBuilder()
                .registerTypeAdapter(Date.class, new Utils.DateSerializer())
                .create();
        return gson.toJson(this);
    }

}
