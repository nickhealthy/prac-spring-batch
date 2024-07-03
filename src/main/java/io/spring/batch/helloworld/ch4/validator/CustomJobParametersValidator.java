package io.spring.batch.helloworld.ch4.validator;

import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.JobParametersValidator;

public class CustomJobParametersValidator implements JobParametersValidator {

    /**
     * JobParameters 파라미터에 우리가 설정한 파라미터 값들이 들어오게 되는데 그 값들을 활용해서 검증기를 구현할 수 있다.
     */
    @Override
    public void validate(JobParameters jobParameters) throws JobParametersInvalidException {
        if (jobParameters.getString("name") == null) {
            throw new JobParametersInvalidException("name parameter is required");
        }

    }
}
