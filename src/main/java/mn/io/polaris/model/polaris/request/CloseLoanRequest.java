package mn.io.polaris.model.polaris.request;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import mn.io.polaris.helper.Utils;

import java.util.Date;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
public class CloseLoanRequest extends DepositTdAccountRequest {

    private List<CloseLoanAddParam> addParams;
    private String curCode;
    private String contCurCode;

    public String toJsonStringSelf() {
        Gson gson = new GsonBuilder()
                .registerTypeAdapter(Date.class, new Utils.DateSerializer())
                .create();
        return gson.toJson(this);
    }

}
