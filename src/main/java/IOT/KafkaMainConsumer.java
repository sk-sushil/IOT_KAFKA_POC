package IOT;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;

import java.util.Date;
import java.util.Properties;
import java.util.Timer;
import java.util.TimerTask;

import static java.util.Collections.singletonList;
import static org.apache.kafka.clients.consumer.ConsumerConfig.*;

public class KafkaMainConsumer extends TimerTask{
    static KafkaConsumer<String, String> consumer = null;
    String TOPIC = "test-topic";

    static {
        Properties settings = new Properties();
        settings.put(GROUP_ID_CONFIG, "basic-consumer");
        settings.put(BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        settings.put(ENABLE_AUTO_COMMIT_CONFIG, "true");
        settings.put(AUTO_COMMIT_INTERVAL_MS_CONFIG, "1000");
        settings.put(AUTO_OFFSET_RESET_CONFIG, "earliest");
        settings.put(KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        settings.put(VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        consumer = new KafkaConsumer<String, String>(settings);
    }

    public void run() {
        System.out.println("counsuming message....");
        consumer.subscribe(singletonList(TOPIC));
        ConsumerRecords<String, String> records = consumer.poll(200);
        for (ConsumerRecord<String, String> record : records) {
            System.out.printf("message offset = %d, key = %s, value = %s\n", record.offset(), record.key(),record.value());
        }
    }

    public static void main(String[] args){
        KafkaMainConsumer kafkaconsumer = new KafkaMainConsumer();
        Timer timer=new Timer();
        timer.scheduleAtFixedRate(kafkaconsumer, 0,5*1000);
    }
}
