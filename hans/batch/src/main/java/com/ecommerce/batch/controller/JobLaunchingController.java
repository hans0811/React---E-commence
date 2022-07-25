package com.ecommerce.batch.controller;

import com.ecommerce.batch.Application;
import com.ecommerce.batch.model.JobLaunchRequest;

import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.explore.JobExplorer;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class JobLaunchingController {

    @Autowired
    private JobLauncher jobLauncher;

    @Autowired
    private ApplicationContext context;

    @Autowired
    private JobExplorer jobExplorer;

    @PostMapping(path = "/run")
    public ExitStatus runJob(@RequestBody JobLaunchRequest request) throws Exception{
        Job job = this.context.getBean(request.getName(), Job.class);
        JobParameters jobParameters = new JobParametersBuilder(request.getJobParameters(),
                this.jobExplorer)
                .getNextJobParameters(job)
                .toJobParameters();

        return this.jobLauncher.run(job, jobParameters).getExitStatus();
    }
}
