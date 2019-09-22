package kafka;

import org.apache.kafka.clients.producer.*;
import org.apache.kafka.common.serialization.StringSerializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Timestamp;
import java.util.Properties;
import java.util.concurrent.ExecutionException;

public class ProducerDemoWithKey {
    private static Logger logger = LoggerFactory.getLogger(ProducerDemoWithKey.class);

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        Properties properties = new Properties();
        properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());


        KafkaProducer<String, String> kafkaProducer = new KafkaProducer<String, String>(properties);
        for(int i = 0; i< 10; i++){
            String topic = "first_topic";
            String key = "Id_"+i%3;
            String value = "Msg : "+i;

            ProducerRecord<String, String> producerRecord = new ProducerRecord<>(topic, key, value);

            logger.info("Key {} , Value {}", key, value);
            //send Data asynchronous
            kafkaProducer.send(producerRecord, new Callback() {
                @Override
                public void onCompletion(RecordMetadata recordMetadata, Exception e) {
                    if(e==null){
                        //on sending msg successfully
                        logger.info("Topic {}, Partition {}, Offset {}, TimeStamp {}"
                            ,recordMetadata.topic(), recordMetadata.partition(), recordMetadata.offset(), new Timestamp(recordMetadata.timestamp()));
                        logger.info("Key {} -> Partition {}", key, recordMetadata.partition());
                    } else{
                        logger.error("Error While sending Data", e);
                    }
                }
            }).get();
        }
        kafkaProducer.flush();
        kafkaProducer.close();
    }
}
