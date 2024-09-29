package integrador.seed.models.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ExemploDTO implements Serializable {

    private Integer intExemplo;
    private String strExemplo;
}
