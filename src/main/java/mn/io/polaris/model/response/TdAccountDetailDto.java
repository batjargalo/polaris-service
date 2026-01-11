package mn.io.polaris.model.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class TdAccountDetailDto {

    @Schema(description = "Дансны дугаар")
    private String acntCode;

    @Schema(description = "Одоогийн үлдэгдэл")
    private BigDecimal currentBal;

    @Schema(description = "Хуримтлагдсан хүү")
    private BigDecimal acrintBal;

    @Schema(description = "Хүү")
    private BigDecimal intRate;

    @Schema(description = "Хугацаа дуусах огноо")
    private Date maturityDate;

    @Schema(description = "Хадгаламжийн хугацаа хоногоор")
    private Integer tenor;

    @Schema(description = "Хадгаламж эхэлсэн өдөр")
    private Date startDate;

    @Schema(description = "Бүтээгдэхүүний нэр")
    private String prodName;

    @Schema(description = "Бүтээгдэхүүний код")
    private String prodCode;

    @Schema(description = "Валют")
    private String curCode;

    @Schema(description = "Дансны төлөв")
    private String status;

    @Schema(description = "Нийт авах өгөөж")
    private BigDecimal expectedIntBal;

    @Schema(description = "Төлөвийн нэр")
    private String statusName;

    @Schema(description = "Битүүмжилсэн дүн")
    private BigDecimal blockBal;

    @Schema(description = "Боломжит үлдэгдэл")
    private BigDecimal availBal;

}
