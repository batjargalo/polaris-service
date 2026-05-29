package mn.io.polaris.model.polaris;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.math.BigDecimal;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class AccountArcv {

    @Size(max = 20)
    @Schema(description = "Дансны дугаар")
    private String acntCode;

    @Size(max = 20)
    @Schema(description = "Дансны дугаар")
    private String relAcntCode;

    @Schema(description = "Дансны үлдэгдэл")
    private BigDecimal principal;

}
