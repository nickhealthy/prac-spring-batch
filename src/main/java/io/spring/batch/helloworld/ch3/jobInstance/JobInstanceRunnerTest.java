package io.spring.batch.helloworld.ch3.jobInstance;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

/**
 * 테스트를 위해 스프링 배치에 의해 배치 작업이 자동으로 실행되지 않고, jobLauncher를 통해 수동적으로 수행하기 위한 클래스
 * - ApplicationRunner: 스프링 부트가 제공하며, 스프링 부트가 초기화가 완료되면 가장 먼저 호출되는 인터페이스이다.
 */
//@Component
public class JobInstanceRunnerTest implements ApplicationRunner {

    /* @EnableBatchProcessing 어노테이션에 의해 스프링 배치가 초기화 될 때 JobLauncher를 자동으로 빈으로 등록해준다. */
    @Autowired
    private JobLauncher jobLauncher;

    /* JobInstanceConfiguration 클래스에 등록된 Job을 빈으로 의존성 주입을 통해 Job을 주입 받을 수 있다. */
    @Autowired
    private Job job;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        JobParameters jobParameters = new JobParametersBuilder()
                .addString("name", "user2")
                .toJobParameters();

        jobLauncher.run(job, jobParameters);
    }
}
