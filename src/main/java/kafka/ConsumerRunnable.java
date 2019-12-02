package kafka;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.errors.WakeupException;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.util.Arrays;
import java.util.Properties;
import java.util.concurrent.CountDownLatch;

public class ConsumerRunnable implements Runnable{
    private KafkaConsumer<String, String> kafkaConsumer;
    private CountDownLatch latch;
    private Logger logger = LoggerFactory.getLogger(ConsumerRunnable.class);

    public ConsumerRunnable(String kafkaServer, String group, String topic,
                            CountDownLatch latch) {
        //Create Consumer Config
        Properties properties = new Properties();
        properties.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaServer);
        properties.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        properties.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        properties.put(ConsumerConfig.GROUP_ID_CONFIG, group);
        properties.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
        //Create Consumer
        kafkaConsumer = new KafkaConsumer<String, String>(properties);
        this.latch = latch;
        //subscribe to topic
        kafkaConsumer.subscribe(Arrays.asList(topic));
    }

    @Override
    public void run() {
        try {
            //poll for msg
            while (true) {
                ConsumerRecords<String, String> consumerRecords = kafkaConsumer.poll(Duration.ofMillis(100));
                for (ConsumerRecord consumerRecord : consumerRecords) {
                    logger.info("Key : {} , Value : {}", consumerRecord.key(), consumerRecord.value());
                    logger.info("Partition {}, Offset {}", consumerRecord.partition(), consumerRecord.offset());
                }
            }
        } catch (WakeupException e){
            logger.info("Received ShutDown Signal");
        } finally {
            kafkaConsumer.close();
            //tell our code that we are done with consumer
            latch.countDown();
        }


    }

    public void shutDown(){
        //to interrupt consumer poll method
        // throws WakeUpException
        kafkaConsumer.wakeup();
    }
}
