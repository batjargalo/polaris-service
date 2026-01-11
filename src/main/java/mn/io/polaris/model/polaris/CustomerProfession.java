package mn.io.polaris.model.polaris;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import mn.io.polaris.helper.Utils;

import java.util.Date;

@Data
public class CustomerProfession {

    @NotEmpty
    private String custCode;
    @NotEmpty
    private String eduDegreeId;
    @NotEmpty
    private String schoolName;
    @NotEmpty
    private String professionId;
    private String certificateNo;
    @NotNull
    private Integer beginYear;
    private Integer endYear;
    @NotNull
    private Integer isPresent;
    private String description;

    public String toJsonString() {
        Gson gson = new GsonBuilder()
                .registerTypeAdapter(Date.class, new Utils.DateSerializer())
                .create();
        return gson.toJson(this);
    }

}
