package mn.io.polaris.model.polaris;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import lombok.Data;
import mn.io.polaris.helper.Utils;

import java.util.Date;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class RetailAddress {

    private Integer isMain;
    private String statusName;
    private Integer addrId;
    private String isMainName;
    private Integer custAddrId;
    private String addrName;
    private String custCode;
    private Integer status;
    private String addrName2;
    private String blockDoor;
    private String street;
    private String addrDetail;
    private String cityTown;

    public String toJsonString() {
        Gson gson = new GsonBuilder()
                .registerTypeAdapter(Date.class, new Utils.DateSerializer())
                .create();
        return gson.toJson(this);
    }

}
