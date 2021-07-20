package com.indianrailways.schedule.batch.config;

import com.indianrailways.schedule.batch.data.ScheduleInput;
import com.indianrailways.schedule.batch.listener.JobCompletionNotificationListener;
import com.indianrailways.schedule.batch.model.Schedule;
import com.indianrailways.schedule.batch.processor.ScheduleItemProcessor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.data.MongoItemWriter;
import org.springframework.batch.item.data.builder.MongoItemWriterBuilder;
import org.springframework.batch.item.json.JacksonJsonObjectReader;
import org.springframework.batch.item.json.JsonItemReader;
import org.springframework.batch.item.json.builder.JsonItemReaderBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.mongodb.core.MongoTemplate;

@Configuration
@EnableBatchProcessing
public class BatchConfiguration {

    @Autowired
    public JobBuilderFactory jobBuilderFactory;
    @Autowired
    public StepBuilderFactory stepBuilderFactory;


    @Bean
    public JsonItemReader<ScheduleInput> jsonItemReader() {
        return new JsonItemReaderBuilder<ScheduleInput>()
                .jsonObjectReader(new JacksonJsonObjectReader<>(ScheduleInput.class))
                .resource(new ClassPathResource("schedules.json"))
                .name("schedulesJsonItemReader")
                .build();
    }

    @Bean
    public MongoItemWriter<Schedule> writer(MongoTemplate mongoTemplate) {
        return new MongoItemWriterBuilder<Schedule>().template(mongoTemplate).collection("Schedule")
                .build();
    }


    @Bean
    public ScheduleItemProcessor processor() {
        return new ScheduleItemProcessor();
    }


    @Bean
    public Step step1(JsonItemReader<ScheduleInput> jsonItemReader, MongoItemWriter<Schedule> itemWriter)
            throws Exception {

        return this.stepBuilderFactory.get("step1").<ScheduleInput, Schedule>chunk(20).reader(jsonItemReader)
                .processor(processor()).writer(itemWriter).build();
    }

    @Bean
    public Job updateUserJob(JobCompletionNotificationListener listener, Step step1)
            throws Exception {

        return this.jobBuilderFactory.get("updateUserJob").incrementer(new RunIdIncrementer())
                .listener(listener).start(step1).build();
    }

}
