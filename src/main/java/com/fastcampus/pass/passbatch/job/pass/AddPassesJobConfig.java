package com.fastcampus.pass.passbatch.job.pass;

import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

@RequiredArgsConstructor
@Configuration
public class AddPassesJobConfig {

    // Job 생성
    @Bean
    public Job addPassesJob(JobRepository jobRepository, Step addPassesStep) {
        return new JobBuilder("addPassesJob", jobRepository)
                .start(addPassesStep)
                .build();
    }


    // Step 생성
    @Bean
    public Step addPassesStep(JobRepository jobRepository, AddPassesTasklet addPassesTasklet, PlatformTransactionManager platformTransactionManager){
        return new StepBuilder("addPassesStep", jobRepository)
                .tasklet(addPassesTasklet, platformTransactionManager)
                .build();
    }
}
