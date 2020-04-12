package IOT;

import org.apache.kafka.clients.producer.*;

import java.util.Properties;

public class KafkaMainProducer implements Runnable {
    static KafkaProducer<String, String> kafkaproducer = null;
    static final String TOPIC = "test-topic";
    static String dat;
    static int num;

    static {
        Properties settings = new Properties();
        settings.put(ProducerConfig.CLIENT_ID_CONFIG, "basic-producer");
        settings.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        settings.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG,"org.apache.kafka.common.serialization.StringSerializer");
        settings.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,"org.apache.kafka.common.serialization.StringSerializer");
        kafkaproducer = new KafkaProducer<String, String>(settings);
    }

    public void run() {
        while(true){
            publishData(kafkaproducer);
        }
    }
    private void publishData(KafkaProducer producer) {
        num = MessageGenerator.getRandomNo();
        dat = MessageGenerator.getRamdomDate();
        ProducerRecord<String, String> record = new ProducerRecord<String, String>(TOPIC, num + "", dat);
        producer.send(record, new Callback() {
            public void onCompletion(RecordMetadata metadata, Exception e) {
                if (e != null) {
                    e.printStackTrace();
                }
                System.out.println("Sent:" + num+" , "+dat + ", Offset: " + metadata.offset());
            }
        });
        try{
            Thread.sleep(5);
        }
        catch (Exception e){
            System.out.println("msg:"+e);
        }
    }

    public static void main(String[] args) {
        KafkaMainProducer pro = new KafkaMainProducer();
        Thread t = new Thread(pro);
        t.start();
    }

}
