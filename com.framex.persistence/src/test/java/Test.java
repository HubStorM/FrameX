import com.framex.persistence.DefaultPersistence;
import com.framex.persistence.SpringContextUtil;
import com.framex.persistence.TestService;
import com.framex.persistence.datasource.DataSourceFactory;
import com.framex.persistence.datasource.SupportedDataSourceEnum;
import com.framex.persistence.datasource.dynamic.DynamicDataSource;
import com.framex.persistence.datasource.dynamic.DynamicDataSourceHolder;
import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.Arrays;

public class Test {

    public static void main(String... args){

        DataSourceFactory.buildDataSource("dataSourceA",
                SupportedDataSourceEnum.BASICDATASOURCE,
                Arrays.asList("com.mysql.jdbc.Driver", "jdbc:mysql://localhost:3306/framex", "root", "11111"),
                null);
        DynamicDataSourceHolder.setDataSourceName("dataSourceA");
        SpringContextUtil.getApplicationContext().getBean("testservice", TestService.class).main();

        /*DefaultPersistence persistence = new DefaultPersistence();
        BasicDataSource dataSourceA = new BasicDataSource();
        dataSourceA.setDriverClassName("com.mysql.jdbc.Driver");
        dataSourceA.setUrl("jdbc:mysql://localhost:3306/dynamicDataSourceA");
        dataSourceA.setUsername("root");
        dataSourceA.setPassword("11111");
        persistence.registerDataSource(dataSourceA, "dataSourceA");
        BasicDataSource dataSourceB = new BasicDataSource();
        dataSourceB.setDriverClassName("com.mysql.jdbc.Driver");
        dataSourceB.setUrl("jdbc:mysql://localhost:3306/dynamicDataSourceB");
        dataSourceB.setUsername("root");
        dataSourceB.setPassword("11111");
        persistence.registerDataSource(dataSourceB, "dataSourceB");*/

        /*DataSourceFactory.buildDataSource("dataSourceA",
                SupportedDataSourceEnum.BASICDATASOURCE,
                Arrays.asList("com.mysql.jdbc.Driver", "jdbc:mysql://localhost:3306/framex", "root", "11111"),
                null);
        DynamicDataSource dynamicDataSource = SpringContextUtil.getApplicationContext().getBean("dynamicDataSource", DynamicDataSource.class);
        DynamicDataSourceHolder.setDataSourceName("dataSourceA");
        JdbcTemplate jdbc = new JdbcTemplate(dynamicDataSource);
        jdbc.execute("insert into framex_config VALUES ('4', '4', '4')");*/

        /*DynamicDataSource dynamicDataSource = persistence.getContext().getBean("dynamicDataSource", DynamicDataSource.class);
        Map<Object, Object> dataSources = new HashMap<Object, Object>();
        dataSources.put("dataSourceA", persistence.getContext().getBean("dataSourceA"));
        dataSources.put("dataSourceB", persistence.getContext().getBean("dataSourceB"));
        dynamicDataSource.setTargetDataSources(dataSources);*/

        //DataSourceHolder.setDataSourceName("dataSourceA");
        /*JdbcTemplate jdbc = new JdbcTemplate(dynamicDataSource);
        jdbc.execute("insert into framex_a VALUES ('1')");*/

        /*DataSourceTransactionManager tx = new DataSourceTransactionManager();
        persistence.registerTransactionManager(tx, "dynamicDataSource", "tx");*/


        /*DataSourceTransactionManager tx = new DataSourceTransactionManager();
        tx.setDataSource(dataSourceA);
        persistence.registerTransactionManager(tx, "dynamicDataSource", "tx");
        System.out.println(persistence.getContext().getBean("tx", DataSourceTransactionManager.class).getDataSource());*/

        /*DataSourceTransactionManager tx = new DataSourceTransactionManager();
        tx.setDataSource(dataSourceA);
        persistence.registerTransactionManager(tx, "dataSourceA", "tx");*/

        //((ConfigurableApplicationContext)persistence.getContext()).refresh();
        /*ConfigurableApplicationContext child = new ClassPathXmlApplicationContext("spring-persistence.xml");
        child.setParent(persistence.getContext());
        child.refresh();
        child.getBean("testservice", TestService.class);*/
        //System.out.println(persistence.getContext().getBean("dataSourceA"));
        /*child.setParent(persistence.getContext());
        child.getBean("testservice", TestService.class);*/
        //persistence.getContext().getBean("testservice", TestService.class).main();
    }
}
