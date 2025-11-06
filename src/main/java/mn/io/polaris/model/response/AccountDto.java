package mn.io.polaris.model.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class AccountDto {

    @Schema(description = "Дансны дугаар")
    private String acntCode;

}
