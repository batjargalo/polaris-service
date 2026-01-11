package mn.io.polaris.model.dao;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigInteger;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "polaris")
public class PolarisDao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, columnDefinition = "BIGINT")
    private BigInteger id;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String request;

    @Column(nullable = false)
    private Date requestDate;

    @Column(columnDefinition = "TEXT")
    private String response;

    private Date responseDate;

}
