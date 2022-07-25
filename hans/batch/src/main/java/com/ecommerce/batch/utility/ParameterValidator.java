package com.ecommerce.batch.utility;

import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.JobParametersValidator;
import org.springframework.util.StringUtils;

public class ParameterValidator implements JobParametersValidator {
    @Override
    public void validate(JobParameters jobParameters) throws JobParametersInvalidException {
        String fileName = jobParameters.getString("fileName");

        if(!StringUtils.hasText(fileName)){
            throw  new JobParametersInvalidException("fileName is missing!!!\n\n\n\n\n");
        }
        else if(!StringUtils.endsWithIgnoreCase(fileName, "csv")){
            throw new JobParametersInvalidException("fileName parameter does not use csv file extension");
        }

    }
}
