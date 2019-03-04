package testdata;

import org.flywaydb.core.api.migration.BaseJavaMigration;
import org.flywaydb.core.api.migration.Context;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.SingleConnectionDataSource;

import javax.sql.DataSource;
import java.time.LocalDate;

public class R__ImportMoreBook extends BaseJavaMigration {

    @Override
    public void migrate(Context context) throws Exception {

        DataSource ds = new SingleConnectionDataSource(context.getConnection(), true);
        JdbcTemplate tmpl = new JdbcTemplate(ds);

        final LocalDate baseDate = LocalDate.of(1990, 1, 1);

        for (int i = 1; i <= 100; i++) {
            String isbn = "test-isbn-" + i;
            String title = "test book " + i;
            final LocalDate publishDate = baseDate.plusDays(i);
            final StringBuilder sb = new StringBuilder();
            sb.append("insert into book (isbn, title, publish_date) values (");
            sb.append("'" + isbn + "',");
            sb.append("'" + title + "',");
            sb.append("'" + publishDate.toString() + "'");
            sb.append(")");
            tmpl.execute(sb.toString());
        }
    }
}
