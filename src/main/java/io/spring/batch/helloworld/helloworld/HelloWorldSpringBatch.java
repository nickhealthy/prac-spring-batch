package io.spring.batch.helloworld.helloworld;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;

//@Component
public class HelloWorldSpringBatch {

    @Autowired // JobBuilder 인스턴스 생성
    private JobBuilderFactory jobBuilderFactory;

    @Autowired // StepBuilder 인스턴스 생성
    private StepBuilderFactory stepBuilderFactory;


    /**
     * - this.stepBuilderFactory.get("step1"): stepBuilderFactory를 사용해 스텝 구성 및 StepBuilder 반환
     * -  StepBuilder: 해당 빌더를 사용해 스텝을 정의할 수 있다.
     * - tasklet: 테스크릿을 사용해 스텝을 구성
     * - build(): 스텝 구성 완료
     */
    @Bean
    public Step step1() {
        return this.stepBuilderFactory.get("step2")
                .tasklet((contribution, chunkContext) -> {
                    System.out.println("Hello, World!");
                    return RepeatStatus.FINISHED;
                }).build();
    }

    /**
     * 스프링 배치 잡을 반환한다.
     * - this.jobBuilderFactory.get("basicJob"): 잡 이름을 전달해 JobBuilder 얻기
     * - start(step1()): 시작할 스텝을 지정
     * - build(): 실제 잡을 생성
     */
    @Bean
    public Job job() {
        return this.jobBuilderFactory.get("basicJob")
                .start(step1())
                .build();
    }

}
