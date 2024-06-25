package io.spring.batch.helloworld.ch3.executionContext;

import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.stereotype.Component;

@Component
public class ExecutionContextStep4 implements Tasklet {


    @Override
    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
        System.out.println("STEP4 EXECUTED!!");

        // Step3에서 저장한 name 값을 다른 STEP에서 사용
        System.out.println("name: " + chunkContext.getStepContext().getStepExecution().getJobExecution().getExecutionContext().get("name"));

        return RepeatStatus.FINISHED;
    }
}
