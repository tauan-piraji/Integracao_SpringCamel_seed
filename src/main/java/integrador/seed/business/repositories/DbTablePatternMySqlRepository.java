package integrador.seed.business.repositories;

import integrador.seed.models.entities.DbTablePatternMySQL;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface DbTablePatternMySqlRepository extends JpaRepository<DbTablePatternMySQL, Long> {
}
