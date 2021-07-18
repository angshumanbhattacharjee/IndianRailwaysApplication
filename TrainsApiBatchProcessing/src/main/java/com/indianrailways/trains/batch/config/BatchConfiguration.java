package com.indianrailways.trains.batch.config;

import com.indianrailways.trains.batch.data.TrainInput;
import com.indianrailways.trains.batch.listener.JobCompletionNotificationListener;
import com.indianrailways.trains.batch.model.Train;
import com.indianrailways.trains.batch.processor.TrainItemProcessor;
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
    public JsonItemReader<TrainInput> jsonItemReader() {
        return new JsonItemReaderBuilder<TrainInput>()
                .jsonObjectReader(new JacksonJsonObjectReader<>(TrainInput.class))
                .resource(new ClassPathResource("trains-updated.json"))
                .name("trainsJsonItemReader")
                .build();
    }

    @Bean
    public MongoItemWriter<Train> writer(MongoTemplate mongoTemplate) {
        return new MongoItemWriterBuilder<Train>().template(mongoTemplate).collection("Train")
                .build();
    }


    @Bean
    public TrainItemProcessor processor() {
        return new TrainItemProcessor();
    }


    @Bean
    public Step step1(JsonItemReader<TrainInput> jsonItemReader, MongoItemWriter<Train> itemWriter)
            throws Exception {

        return this.stepBuilderFactory.get("step1").<TrainInput, Train>chunk(20).reader(jsonItemReader)
                .processor(processor()).writer(itemWriter).build();
    }

    @Bean
    public Job updateUserJob(JobCompletionNotificationListener listener, Step step1)
            throws Exception {

        return this.jobBuilderFactory.get("updateUserJob").incrementer(new RunIdIncrementer())
                .listener(listener).start(step1).build();
    }

}
