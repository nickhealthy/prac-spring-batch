package io.spring.batch.helloworld.ch3.jobParameter;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * 테스트를 위한 클래스, 자동으로 실행하면 아래와 같은 테스트를 실행하는데 어려움이 있어 수동으로 실행하게 된다.
 * [테스트 내용]
 * - 애플리케이션 실행 시 파라미터를 주입하는 방법
 * - 코드로 직접 JobParameter를 주입하는 방법
 */
//@Component
public class JobParameterRunnerTest implements ApplicationRunner {

    @Autowired
    private JobLauncher jobLauncher;

    @Autowired
    private Job job;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        JobParameters jobParameters = new JobParametersBuilder()
                .addString("name", "user1")
                .addLong("seq", 1L)
                .addDate("date", new Date())
                .addDouble("age", 16.5)
                .toJobParameters();

        jobLauncher.run(job, jobParameters);

    }
}
