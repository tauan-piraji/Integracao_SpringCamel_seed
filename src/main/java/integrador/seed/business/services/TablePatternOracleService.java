package integrador.seed.business.services;

import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import integrador.seed.business.oracleRepositories.DbTablePatternOracleRepository;
import integrador.seed.business.repositories.DbTablePatternMySqlRepository;
import integrador.seed.models.entities.DbTablePatternMySQL;
import integrador.seed.models.entities.QDbTablePatternMySQL;
import integrador.seed.models.entitiesOracle.DbTablePatternOracle;
import integrador.seed.models.entitiesOracle.QDbTablePatternOracle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class TablePatternOracleService {

    @Autowired
    private DbTablePatternOracleRepository repository;

    @Autowired
    private EntityManager entityManager;

    private static final QDbTablePatternOracle dbTablePatternOracle = QDbTablePatternOracle.dbTablePatternOracle;

    public List<DbTablePatternOracle> getAll() {
        return repository.findAll();
    }

    public DbTablePatternOracle getById(Long id) {
        return new JPAQueryFactory(entityManager)
                .selectFrom(dbTablePatternOracle)
                .where(dbTablePatternOracle.id.eq(id))
                .fetchOne();
    }

    @Transactional
    public void updateTablePattern(Long id, LocalDateTime dateTime) {
        new JPAQueryFactory(entityManager)
                .update(dbTablePatternOracle)
                .where(dbTablePatternOracle.id.eq(id))
                .set(dbTablePatternOracle.dhColumnm, dateTime)
                .execute();
    }
}