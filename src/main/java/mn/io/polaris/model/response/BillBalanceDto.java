package mn.io.polaris.model.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class BillBalanceDto {

    @Schema(description = "Үндсэн хүүгийн өрийн авлагын дүн")
    private BigDecimal billBaseintBal;

    @Schema(description = "Үндсэн хүүнээс бодсон торгуулийн хүүгийн дүн")
    private BigDecimal billFinebBal;

    @Schema(description = "Үндсэн үлдэгдлээс бодсон торгуулийн хүүгийн дүн")
    private BigDecimal billFinepBal;

    @Schema(description = "Коммитмэнт  хүүгийн өрийн авлагын дүн")
    private BigDecimal billComintBal;

    @Schema(description = "Үндсэн үлдэгдлийн өр")
    private BigDecimal billPrincBal;

    @Schema(description = "Нийт")
    private BigDecimal totalBill;

}
