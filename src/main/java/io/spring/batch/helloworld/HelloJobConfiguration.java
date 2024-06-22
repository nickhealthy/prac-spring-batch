package io.spring.batch.helloworld;

import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@RequiredArgsConstructor
//@Configuration
public class HelloJobConfiguration {

    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;

    @Bean
    public Job helloJob() {
        return jobBuilderFactory.get("helloJob")        // 잡 생성 및 이름 지정
                .start(helloStep1())                    // 잡을 시작할 스텝 지정
                .next(helloStep2())                     // helloStep1() 종료 후 다음 스텝 지정
                .build();
    }

    @Bean
    public Step helloStep2() {
        return stepBuilderFactory.get("helloStep2")     // 스텝 생성 및 이름 지정
                .tasklet(new Tasklet() {                // Tasklet(실제 비즈니스 로직)를 작성
                    @Override
                    public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext) throws Exception {
                        System.out.println("Hello World2 execute!!");
                        // Tasklet은 기본적으로 무한 반복인데, 다음과 같이 종료 코드를 작성해주어야 한번만 실행하게 된다.
                        // null도 아래의 코드와 마찬가지로 한번만 실행되고 종료하게 된다.
                        return RepeatStatus.FINISHED;
                    }
                }).build();
    }

    @Bean
    public Step helloStep1() {
        return stepBuilderFactory.get("helloStep1")
                .tasklet(new Tasklet() {
                    @Override
                    public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext) throws Exception {
                        System.out.println("Hello World1 execute!!");
                        return RepeatStatus.FINISHED;
                    }
                }).build();
    }

}
