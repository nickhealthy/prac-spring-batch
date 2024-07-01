package io.spring.batch.helloworld.ch3.jobParameter;

import lombok.RequiredArgsConstructor;
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
import org.springframework.context.annotation.Configuration;

import java.util.Map;

//@Configuration
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

    @Bean
    public Step step1() {
        return stepBuilderFactory.get("step1")
                .tasklet(new Tasklet() {
                    @Override
                    public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext)
                            throws Exception {
                        System.out.println("JobParameterConfiguration step1 executed!!");
                        JobParameters jobParameters1 = stepContribution.getStepExecution().getJobExecution()
                                .getJobParameters();
                        System.out.println("jobParameters1 = " + jobParameters1);

                        jobParameters1.getString("name");
                        jobParameters1.getLong("seq");
                        jobParameters1.getDate("date");
                        jobParameters1.getDouble("age");

                        Map<String, Object> jobParameters2 = chunkContext.getStepContext().getJobParameters();
                        System.out.println("jobParameters2 = " + jobParameters2);

                        return RepeatStatus.FINISHED;
                    }
                }).build();

    }

    @Bean
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
