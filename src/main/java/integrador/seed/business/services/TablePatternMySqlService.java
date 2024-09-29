package integrador.seed.business.services;

import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import integrador.seed.business.repositories.DbTablePatternMySqlRepository;
import integrador.seed.models.entities.DbTablePatternMySQL;
import integrador.seed.models.entities.QDbTablePatternMySQL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class TablePatternMySqlService {

    @Autowired
    private DbTablePatternMySqlRepository repository;

    @Autowired
    private EntityManager entityManager;

    private static final QDbTablePatternMySQL dbTablePatternMySQL = QDbTablePatternMySQL.dbTablePatternMySQL;

    public List<DbTablePatternMySQL> getAll() {
        return repository.findAll();
    }

    public DbTablePatternMySQL getById(Long id) {
        return new JPAQueryFactory(entityManager)
                .selectFrom(dbTablePatternMySQL)
                .where(dbTablePatternMySQL.id.eq(id))
                .fetchOne();
    }

    @Transactional
    public void updateTablePattern(Long id, LocalDateTime dateTime) {
        new JPAQueryFactory(entityManager)
                .update(dbTablePatternMySQL)
                .where(dbTablePatternMySQL.id.eq(id))
                .set(dbTablePatternMySQL.dhColumnm, dateTime)
                .execute();
    }
}