package mn.io.polaris.model.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TxnDto {

    @Schema(description = "Гүйлгээ хийсэн бодит огноо")
    private Date postDate;

    @Schema(description = "Огноо")
    private String txnDate;

    @Schema(description = "Үндсэн")
    private BigDecimal totalPrincAmountGrouped;

    @Schema(description = "Хүү")
    private BigDecimal totalIntAmountGrouped;

    @Schema(description = "Үлдэгдэл")
    private BigDecimal balance;

}
