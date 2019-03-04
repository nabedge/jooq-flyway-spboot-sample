package testdata;

import com.example.db.jooq.gen.tables.JBook;
import com.example.db.jooq.gen.tables.records.BookRecord;
import org.flywaydb.core.api.migration.BaseJavaMigration;
import org.flywaydb.core.api.migration.Context;
import org.jooq.DSLContext;
import org.jooq.SQLDialect;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.SingleConnectionDataSource;

import javax.sql.DataSource;
import java.time.LocalDate;

import static org.jooq.impl.DSL.using;

public class R__ImportMoreBookWithJooq extends BaseJavaMigration {

    @Override
    public void migrate(Context context) throws Exception {

        DataSource ds = new SingleConnectionDataSource(context.getConnection(), true);

        DSLContext dslContext = using(context.getConnection(), SQLDialect.POSTGRES);

        final LocalDate baseDate = LocalDate.of(2010, 1, 1);

        for (int i = 1; i <= 10; i++) {
            String isbn = "test-isbn-" + i;
            String title = "test book " + i;

            final LocalDate publishDate = baseDate.plusDays(i);
            final JBook jBook = JBook.BOOK;

            final BookRecord rec = dslContext.newRecord(jBook);
            rec.setIsbn("isbn-test-with-jooq-" + i);
            rec.setTitle("test title with jooq " + i);
            rec.setPublishDate(java.sql.Date.valueOf(baseDate.plusDays(i)));
            rec.store();
        }
    }
}
