package mn.io.polaris.model.polaris.request;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import lombok.Data;
import mn.io.polaris.helper.Utils;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class LoanRequest {

    private String custCode;
    private String name;
    private String name2;
    private String prodCode;
    private String purpose;
    private String subPurpose;
    private Integer isNotAutoClass;
    private Integer comRevolving;
    private String dailyBasisCode;
    private String curCode;
    private BigDecimal approvAmount;
    private String approvDate;
    private Integer acntManager;
    private String brchCode;
    private Integer isGetBrchFromOutside;
    private String segCode;
    private String status;
    private String slevel;
    private String classNoTrm;
    private String classNoQlt;
    private String classNo;
    private String startDate;
    private String endDate;
    private Integer termLen;
    private String termBasis;
    private String repayAcntCode;
    private Integer repayPriority;
    private Integer secType;

    public String toJsonString() {
        Gson gson = new GsonBuilder()
                .registerTypeAdapter(Date.class, new Utils.DateSerializer())
                .create();
        return gson.toJson(this);
    }

}
