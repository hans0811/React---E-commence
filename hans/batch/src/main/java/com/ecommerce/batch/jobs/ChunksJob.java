package com.ecommerce.batch.jobs;

import com.ecommerce.batch.utility.LoggingStepStartStopListener;
import com.ecommerce.batch.utility.ParameterValidator;
import com.ecommerce.batch.utility.RandomChunkSizePolicy;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.job.CompositeJobParametersValidator;
import org.springframework.batch.core.job.DefaultJobParametersValidator;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.support.ListItemReader;
import org.springframework.batch.repeat.CompletionPolicy;
import org.springframework.batch.repeat.policy.CompositeCompletionPolicy;
import org.springframework.batch.repeat.policy.SimpleCompletionPolicy;
import org.springframework.batch.repeat.policy.TimeoutTerminationPolicy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

public class ChunksJob {

    @Autowired
    private JobBuilderFactory jobBuilderFactory;

    @Autowired
    private StepBuilderFactory stepBuilderFactory;

    @Bean
    public Job job(){
        return this.jobBuilderFactory.get("chunkBasedJob3-randomCompletionPolicy")
                .start(chunkStep())
                .validator(validator())
                .incrementer(new RunIdIncrementer())
                .build();
    }

    @Bean
    public Step chunkStep(){
        return this.stepBuilderFactory.get("chunkStep")
                .<String, String>chunk(randomCompletionPolicy())
                .reader(itemReader())
                .writer(itemWriter())
                .listener(new LoggingStepStartStopListener())
                .build();

    }

    @Bean
    public ListItemReader<String> itemReader() {
        List<String> items = new ArrayList<>(1000);
        for (int i = 0; i < 1000; i++) {
            items.add(UUID.randomUUID().toString());
        }
        return new ListItemReader<>(items);
    }

    @Bean
    public ItemWriter<String> itemWriter() {
        return items -> {
            int count =0;
            for (String item : items) {
                count++;
                System.out.println(">> current item = " + item + "number: " + count);
            }
        };
    }

    @Bean
    public CompletionPolicy randomCompletionPolicy(){
        return new RandomChunkSizePolicy();
    }

    public CompletionPolicy completionPolicy(){
        CompositeCompletionPolicy policy = new CompositeCompletionPolicy();
        policy.setPolicies(new CompletionPolicy[]{
                new TimeoutTerminationPolicy(3),
                new SimpleCompletionPolicy(1000)
        });
        return policy;
    }

    @Bean
    public CompositeJobParametersValidator validator(){
        CompositeJobParametersValidator validator =
                new CompositeJobParametersValidator();
        DefaultJobParametersValidator defaultJobParametersValidator =
                new DefaultJobParametersValidator(
                        new String[] {"fileName"},
                        new String[] {"name", "currentDate", "run.id"});
        defaultJobParametersValidator.afterPropertiesSet();
        validator.setValidators(
                Arrays.asList(new ParameterValidator(),
                        defaultJobParametersValidator));
        return validator;
    }
}
