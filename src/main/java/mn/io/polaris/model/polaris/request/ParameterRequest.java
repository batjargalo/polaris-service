package mn.io.polaris.model.polaris.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Параметр")
public class ParameterRequest {

    @NotNull
    @Schema(description = "Параметр авах утгууд",
            example = "CIF009 - Ажил эрхлэлт /CIF_EMPLOYMENT/, " +
                    "CIF006 - Хүндэтгэл /CIF_TITLE/, " +
                    "CIF002 - Үндэстэн /CIF_NATIONALITY/, " +
                    "CIF005 - Боловсролийн түвшин /CIF_EDU_LEVEL/, " +
                    "CIF028 - Үйл ажиллагааны чиглэл /CIF_INDUSTRY/, " +
                    "CIF007 - Гэр бүлийн байдал /CIF_CONST/, " +
                    "CIF011 - Эрдмийн зэргийн ID /CIF_EDU_DEGREE/, " +
                    "CIF012 - Мэргэжлийн ID /CIF_PROFESSION/, " +
                    "CIF033 - Холбоо барих төрлүүд /CIF_CONTACT_TYPE/, " +
                    "CIF052 - Хаяг №1, " +
                    "CIF003 - Яс үндэс /CIF_ETHNIC_GRP/"
    )
    private List<String> dictList;

}
