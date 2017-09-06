import com.framex.persistence.DefaultPersistence;
import com.framex.persistence.TestService;
import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
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

        //((ConfigurableApplicationContext)persistence.getContext()).refresh();
        ConfigurableApplicationContext child = new ClassPathXmlApplicationContext("spring-persistence.xml");
        child.setParent(persistence.getContext());
        child.refresh();
        child.getBean("testservice", TestService.class);
        //System.out.println(persistence.getContext().getBean("dataSource"));
        /*child.setParent(persistence.getContext());
        child.getBean("testservice", TestService.class);*/
        //persistence.getContext().getBean("testservice", TestService.class).main();
    }
}
