package io.spring.batch.helloworld.ch4.incrementer;

import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class IncrementerConfiguration {

    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;

    @Bean
    public Job incrementerJob() {
        return jobBuilderFactory.get("IncrementerConfiguration")
                .start(step1())
                .next(step2())
//                .incrementer(new CustomJobParametersIncrementer())    // 커스터마이징 JobParametersIncrementer
                .incrementer(new RunIdIncrementer())                    // 스프링 배치에서 기본으로 제공해주는 JobParametersIncrementer
                .build();
    }

    @Bean
    public Step step1() {
        return stepBuilderFactory.get("step1")
                .tasklet((stepContribution, chunkContext) -> {
                    System.out.println("STEP1 EXECUTION!!");
                    return RepeatStatus.FINISHED;
                }).build();
    }


    @Bean
    public Step step2() {
        return stepBuilderFactory.get("step2")
                .tasklet((stepContribution, chunkContext) -> {
                    System.out.println("STEP2 EXECUTION!!");
                    return RepeatStatus.FINISHED;
                }).build();
    }
}
