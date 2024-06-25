package io.spring.batch.helloworld.ch3.jobRepositoy;

import org.springframework.batch.core.*;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/* JobRepository를 가져와서 각종 정보를 출력해보는 테스트 진행 */
@Component
public class JobRepositoryListener implements JobExecutionListener {
    @Autowired
    private JobRepository jobRepository;


    @Override
    public void beforeJob(JobExecution jobExecution) {

    }

    @Override
    public void afterJob(JobExecution jobExecution) {
        String jobName = jobExecution.getJobInstance().getJobName();

        JobParameters jobParameters = new JobParametersBuilder()
                .addString("requestDate", "20210102")
                .toJobParameters();

        JobExecution lastJobExecution = jobRepository.getLastJobExecution(jobName, jobParameters);
        if (lastJobExecution != null) {
            for (StepExecution stepExecution : lastJobExecution.getStepExecutions()) {
                BatchStatus status = stepExecution.getStatus();
                System.out.println("status = " + status);

                ExitStatus exitStatus = stepExecution.getExitStatus();
                System.out.println("exitStatus = " + exitStatus);

                String stepName = stepExecution.getStepName();
                System.out.println("stepName = " + stepName);
            }
        }
    }
}
