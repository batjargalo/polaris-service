package mn.io.polaris.model.dao;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigInteger;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "code")
public class CodeDao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, columnDefinition = "BIGINT")
    private BigInteger id;

    @Column(nullable = false)
    private String codeName;

    @Column(nullable = false)
    private String codeValue;

    @Column(nullable = false)
    private Integer codeOrder;

    @Column(nullable = false)
    private Integer codeStatus;

    @Column(nullable = false)
    private String tableName;

}
