package com.fastcampus.pass.passbatch;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.transaction.PlatformTransactionManager;

@EnableBatchProcessing
@SpringBootApplication
public class PassBatchApplication {

	public static void main(String[] args) {
		SpringApplication.run(PassBatchApplication.class, args);
	}

	@Bean
	public Tasklet testTasklet(){
		return ((contribution, chunkContext) -> {
			System.out.println("테스트1");
			return RepeatStatus.FINISHED;
		});
	}

	// JobRepository를 명시적으로 제공하는 방식을 사용하길 권장한다.
	@Bean
	public Step passStep(JobRepository jobRepository, Tasklet testTasklet, PlatformTransactionManager platformTransactionManager){
		return new StepBuilder("passStep", jobRepository)
				.tasklet(testTasklet, platformTransactionManager)
				.build();
	}

	@Bean
	public Job passJob(JobRepository jobRepository, Step passStep) {
		return new JobBuilder("passJob", jobRepository)
				.start(passStep)
				.build();
	}

}
