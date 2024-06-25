package io.spring.batch.helloworld.ch3.executionContext;

import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.stereotype.Component;

@Component
public class ExecutionContextStep3 implements Tasklet {


    @Override
    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
        System.out.println("STEP3 EXECUTED!!");
        Object name = chunkContext.getStepContext().getStepExecution().getJobExecution().getExecutionContext().get("name");


        if (name == null) {
            // JobExecutionContext에 등록 - Step4에서 사용 예정
            chunkContext.getStepContext().getStepExecution().getJobExecution().getExecutionContext().put("name", "user1");
            throw new RuntimeException("STEP3 WAS FAIELD");
        }

        return RepeatStatus.FINISHED;
    }
}
