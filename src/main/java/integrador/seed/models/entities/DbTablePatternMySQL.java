package integrador.seed.models.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "db_table_pattern", schema = "dbo", catalog = "TI_Varejo")
public class DbTablePatternMySQL implements Serializable {

    @Id
    @Column(name = "ID")
    private Long id;

    @Column(name = "text_column")
    private String textColumn;

    @Column(name = "integer_column")
    private Integer integerColumn;

    @Column(name = "decimal_column")
    private BigDecimal decimalColumn;

    @Column(name = "dt_column")
    private LocalDate dtColumn;

    @Column(name = "dh_column")
    private LocalDateTime dhColumnm;
}