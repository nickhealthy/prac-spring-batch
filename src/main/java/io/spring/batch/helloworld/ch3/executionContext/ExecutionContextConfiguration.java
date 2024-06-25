package io.spring.batch.helloworld.ch3.executionContext;

import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class ExecutionContextConfiguration {

    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;

    private final ExecutionContextStep1 executionContextStep1;
    private final ExecutionContextStep2 executionContextStep2;
    private final ExecutionContextStep3 executionContextStep3;
    private final ExecutionContextStep4 executionContextStep4;

    @Bean
    public Job batchJob() {
        return jobBuilderFactory.get("ExecutionContextConfiguration")
                .start(step1())
                .next(step2())
                .next(step3())
                .next(step4())
                .build();

    }

    @Bean
    public Step step1() {
        return stepBuilderFactory.get("step1")
                .tasklet(executionContextStep1)
                .build();
    }

    @Bean
    public Step step2() {
        return stepBuilderFactory.get("step2")
                .tasklet(executionContextStep2)
                .build();
    }

    @Bean
    public Step step3() {
        return stepBuilderFactory.get("step3")
                .tasklet(executionContextStep3)
                .build();
    }

    @Bean
    public Step step4() {
        return stepBuilderFactory.get("step4")
                .tasklet(executionContextStep4)
                .build();
    }

}
