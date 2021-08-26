package kafka;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.apache.kafka.common.serialization.StringSerializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Properties;
import java.util.Scanner;
import java.util.concurrent.Future;

public class ProducerDemo {
    public static final Logger LOGGER = LoggerFactory.getLogger(ProducerDemo.class);

    public static void main(String[] args) {
        //Create Producer config
        Properties properties = new Properties();
        properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());

        String topic = "first_topic";

        //Create Producer
        KafkaProducer<String, String> kafkaProducer = new KafkaProducer<String, String>(properties);

        boolean wantToSendMoreRecord = true;
        Scanner scanner = new Scanner(System.in);
        while(wantToSendMoreRecord){
            LOGGER.info("Enter New Record: ");
            String msg = scanner.nextLine();
            //create record
            ProducerRecord<String, String> producerRecord = new ProducerRecord<>(topic, msg);

            //send Data asynchronous
            Future<RecordMetadata> acknowledge = kafkaProducer.send(producerRecord);
            LOGGER.info("Want to produce more: yes/no");
            String toProduceMore = scanner.nextLine();
            if(toProduceMore.equalsIgnoreCase("no")){
                wantToSendMoreRecord = false;
            }
        }


        kafkaProducer.flush();
        kafkaProducer.close();
    }
}
