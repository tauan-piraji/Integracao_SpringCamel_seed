package integrador.seed.business.oracleRepositories;

import integrador.seed.models.entitiesOracle.DbTablePatternOracle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DbTablePatternOracleRepository extends JpaRepository<DbTablePatternOracle, Long> {
}
