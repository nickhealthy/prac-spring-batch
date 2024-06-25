package io.spring.batch.helloworld.ch3.jobRepositoy;

import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.repository.support.JobRepositoryFactoryBean;
import org.springframework.boot.autoconfigure.batch.BasicBatchConfigurer;
import org.springframework.boot.autoconfigure.batch.BatchProperties;
import org.springframework.boot.autoconfigure.transaction.TransactionManagerCustomizers;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

/*
* JobRepository Bean이 초기화 될 때 Spring Batch가 설정하게 되는데
* 그 설정을 우리가 일부분 커스터마이징할 수 있는 실습
*  - BatchConfigure 인터페이스 또는 그 인터페이스를 구현한 구현체를 상속 받아 구현
* */
@Configuration
public class CustomBatchConfigurer extends BasicBatchConfigurer {

    private final DataSource dataSource;

    protected CustomBatchConfigurer(BatchProperties properties, DataSource dataSource, TransactionManagerCustomizers transactionManagerCustomizers) {
        super(properties, dataSource, transactionManagerCustomizers);
        this.dataSource = dataSource;
    }

    /*
    * 원래는 BasicBatchConfigurer 클래스를 통해 기본 설정된 JobRepository를 만들겠지만
    * 아래와 같이 커스터마이징을 통해 우리가 설정한대로 만들 수 있다.
    * */
    @Override
    public JobRepository createJobRepository() throws Exception {

        JobRepositoryFactoryBean factory = new JobRepositoryFactoryBean();
        factory.setDataSource(dataSource);
        factory.setTransactionManager(getTransactionManager()); // BasicBatchConfigurer - getTransactionManager()
        factory.setIsolationLevelForCreate("ISOLATION_READ_COMMITTED"); // 격리 수준 재설정
        factory.setTablePrefix("SYSTEM_"); // 메타 데이터 테이블명 변경

        return factory.getObject();
    }
}
