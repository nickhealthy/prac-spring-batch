package io.spring.batch.helloworld.ch4.incrementer;

import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.JobParametersIncrementer;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * JobParametersIncrementer 커스터마이징
 * - 같은 인자로 여러 번의 배치 작업을 수행하고 싶을 때 사용한다.
 * - 스프링 배치에서 기본으로 제공해주는 JobParametersIncrementer도 있지만, 커스터마이징을 하고 싶을 때 JobParametersIncrementer 인터페이스를 구현한다.
 */
public class CustomJobParametersIncrementer implements JobParametersIncrementer {

    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @Override
    public JobParameters getNext(JobParameters jobParameters) {

        String id = format.format(new Date());

        return new JobParametersBuilder().addString("runId", id).toJobParameters();
    }
}
