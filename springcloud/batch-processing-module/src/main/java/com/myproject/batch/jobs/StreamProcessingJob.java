package com.myproject.batch.jobs;

import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.kstream.KStream;
import org.springframework.stereotype.Component;

@Component
public class StreamProcessingJob {

    private final StreamsBuilder streamsBuilder;

    public StreamProcessingJob(StreamsBuilder streamsBuilder) {
        this.streamsBuilder = streamsBuilder;
    }

    public void processStream() {
        KStream<String, String> stream = streamsBuilder.stream("input-topic");
        stream.filter((key, value) -> value != null && !value.isEmpty())
              .to("output-topic");
        System.out.println("Stream processing job executed.");
    }
}
