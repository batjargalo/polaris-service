package mn.io.polaris.model.request;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import mn.io.polaris.helper.Utils;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerCodeRequest {

    @NotEmpty
    private String custCode;

    public String toJsonString() {
        Gson gson = new GsonBuilder()
                .registerTypeAdapter(Date.class, new Utils.DateSerializer())
                .create();
        return gson.toJson(this);
    }

}
