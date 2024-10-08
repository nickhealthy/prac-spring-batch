package io.spring.batch.helloworld.ch6.taskletStep;

import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * STEP - tasklet 설정
 * startLimit: 정해진 횟수만큼만 STEP을 실행(STEP 마다 개별적으로 설정 가능)
 * allowStartIfComplete: 이미 성공한 STEP이라도 재실행이 가능하도록 설정
 */
@Configuration
@RequiredArgsConstructor
public class Limit_AllowConfiguration {

    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;

    @Bean
    public Job job() {
        return jobBuilderFactory.get("Limit_AllowConfiguration")
                .start(step1())
                .next(step2())
                .build();
    }

    @Bean
    public Step step1() {
        return stepBuilderFactory.get("step1")
                .tasklet((stepContribution, chunkContext) -> {
                    System.out.println("step1 EXECUTE!!");
                    return RepeatStatus.FINISHED;
                })
                .allowStartIfComplete(true)
                .build();
    }

    @Bean
    public Step step2() {
        return stepBuilderFactory.get("step2")
                .tasklet((stepContribution, chunkContext) -> {
                    throw new RuntimeException("STEP2 FAILED");
                })
                .startLimit(3)
                .build();
    }

}
