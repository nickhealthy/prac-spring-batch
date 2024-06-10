package io.spring.batch.helloworld.ch3.jobParameter;

import java.util.Date;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.context.annotation.Bean;

import lombok.RequiredArgsConstructor;

// @Configuration
@RequiredArgsConstructor
public class JobParameterConfiguration {

    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;

    @Bean
    public Job job() {
        return jobBuilderFactory.get("JobParameterConfiguration")
                .start(step1())
                .next(step2())
                .build();
    }

    public Step step1() {
        return stepBuilderFactory.get("step1")
                .tasklet(new Tasklet() {
                    @Override
                    public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext)
                            throws Exception {
                        System.out.println("JobParameterConfiguration step1 executed!!");
                        JobParameters jobParameters = stepContribution.getStepExecution().getJobExecution()
                                .getJobParameters();

                        String name = jobParameters.getString("name");
                        Long seq = jobParameters.getLong("seq");
                        Date date = jobParameters.getDate("date");
                        Double age = jobParameters.getDouble("age");

                        System.out.println("name = " + name);
                        System.out.println("seq = " + seq);
                        System.out.println("date = " + date);
                        System.out.println("age = " + age);

                        return RepeatStatus.FINISHED;
                    }
                }).build();

    }

    public Step step2() {
        return stepBuilderFactory.get("step2")
                .tasklet(new Tasklet() {
                    @Override
                    public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext)
                            throws Exception {
                        System.out.println("JobParameterConfiguration step2 executed!!");
                        return RepeatStatus.FINISHED;
                    }
                }).build();

    }

}