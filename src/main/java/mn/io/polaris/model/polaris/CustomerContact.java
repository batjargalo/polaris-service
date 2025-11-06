package mn.io.polaris.model.polaris;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import mn.io.polaris.helper.Utils;

import java.util.Date;

@Data
public class CustomerContact {

    @NotEmpty
    private String contactTypeId;
    @NotEmpty
    private String custCode;
    @NotEmpty
    private String contactValue;

    public String toJsonString() {
        Gson gson = new GsonBuilder()
                .registerTypeAdapter(Date.class, new Utils.DateSerializer())
                .create();
        return gson.toJson(this);
    }

}
