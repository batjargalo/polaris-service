package mn.io.polaris.model.polaris;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class LoanRepayment {

    @Schema(description = "Төлбөр хийх өдөр")
    private Date schdDate;

    @Schema(description = "Үндсэн зээлийн төлөх дүн")
    private BigDecimal amount;

    @Schema(description = "Хүүний дүн")
    private BigDecimal intAmount;

    @Schema(description = "Нийт төлөх дүн")
    private BigDecimal totalAmount;

    @Schema(description = "Онолын үлдэгдэл")
    private BigDecimal theorBal;

    @Schema(description = "Төлбөрийн хуваарь")
    private Integer nrsVersion;

    @Schema(description = "Төлөгдсөн эсэх")
    private Boolean isPaid;

}
