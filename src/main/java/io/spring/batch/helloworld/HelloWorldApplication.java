package io.spring.batch.helloworld;


import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

/**
 * @EnableBatchProcessing
 * 배치 인프라스트럭처를 부트스트랩하는 데 사용된다.
 * 배치 인프라스트럭처를 위한 대부분의 스프링 빈 정의를 제공하므로 다음과 같은 컴포넌트를 직접 포함시킬 필요가 없다.
 * - JobRepository: 실행 중인 잡의 상태를 기록하는 데 사용됨
 * - JobLauncher: 잡을 구동하는 데 사용됨
 * - JobExplorer: JobRepository를 사용해 읽기 전용 작업을 수행하는 데 사용됨
 * - JobRegistry: 특정한 런처 구현체를 사용할 때 잡을 찾는 용도로 사용됨
 * - PlatformTransactionManager: 잡 진행 과정에서 트랜잭션을 다루는 데 사용됨
 * - JobBuilderFactory: 잡을 생성하는 빌더
 * - StepBuilderFactory: 스텝을 생성하는 빌더
 */
@EnableBatchProcessing
@SpringBootApplication
public class HelloWorldApplication {

    @Autowired
    private JobBuilderFactory jobBuilderFactory;

    @Autowired
    private StepBuilderFactory stepBuilderFactory;

    @Bean
    public Step step() {
        return this.stepBuilderFactory.get("step1")
                .tasklet(new Tasklet() {
                            @Override
                            public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
                                System.out.println("Hello World!");
                                return RepeatStatus.FINISHED;
                    }
                }).build();
    }

    @Bean
    public Job job() {
        return this.jobBuilderFactory.get("job")
                .start(step())
                .build();
    }

    public static void main(String[] args) {
        SpringApplication.run(HelloWorldApplication.class, args);
    }

}
