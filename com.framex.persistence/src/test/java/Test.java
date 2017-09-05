import com.framex.persistence.DefaultPersistence;
import com.framex.persistence.TestService;
import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

public class Test {

    public static void main(String... args){
        DefaultPersistence persistence = new DefaultPersistence();
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://localhost:3306/framex");
        dataSource.setUsername("root");
        dataSource.setPassword("11111");
        persistence.registerDataSource(dataSource, "dataSource");
        DataSourceTransactionManager tx = new DataSourceTransactionManager();
        tx.setDataSource(dataSource);
        persistence.registerTransactionManager(tx, "dataSource", "tx");

        persistence.getContext().getBean("testservice", TestService.class).main();
    }
}
