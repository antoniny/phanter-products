package br.com.awm.pantherproducts.kafka.config.lib;

import java.util.Iterator;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.TopicPartition;
import org.apache.kafka.common.header.Header;
import org.apache.kafka.common.header.internals.RecordHeader;
import org.springframework.kafka.KafkaException;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.listener.GenericMessageListenerContainer;
import org.springframework.kafka.requestreply.ReplyingKafkaOperations;
import org.springframework.kafka.requestreply.ReplyingKafkaTemplate;
import org.springframework.kafka.requestreply.RequestReplyFuture;

public class PartitionAwareReplyingKafkaTemplate<K, V, R> extends ReplyingKafkaTemplate<K, V, R> implements ReplyingKafkaOperations<K, V, R> {
    public PartitionAwareReplyingKafkaTemplate(ProducerFactory<K, V> producerFactory, GenericMessageListenerContainer<K, R> replyContainer) {
        super(producerFactory, replyContainer);
    }

    protected RequestReplyFuture<K, V, R> doSendAndReceive(ProducerRecord<K, V> record) {
        String replyTopic = this.getReplyTopicHeader(record);
        if (replyTopic == null) {
            throw new KafkaException("Illegal argument: No reply topic is configured.");
        } else {
            TopicPartition replyPartition = this.getAssignedReplyTopicPartition(replyTopic);
            if (replyPartition == null) {
                throw new KafkaException("Illegal state: Reply topic is not match to any partition.");
            } else {
                record.headers().add(new RecordHeader("kafka_replyTopic", replyPartition.topic().getBytes()));
                record.headers().add(new RecordHeader("kafka_replyPartition", intToBytesBigEndian(replyPartition.partition())));
                return super.sendAndReceive(record);
            }
        }
    }

    private static byte[] intToBytesBigEndian(int data) {
        return new byte[]{(byte)(data >> 24 & 255), (byte)(data >> 16 & 255), (byte)(data >> 8 & 255), (byte)(data & 255)};
    }

    private TopicPartition getAssignedReplyTopicPartition(String topicReplyName) {
        if (this.getAssignedReplyTopicPartitions() != null) {
            return this.getReplyTopicPartition(topicReplyName);
        } else {
            throw new KafkaException("Illegal state: No reply partition is assigned to this instance.");
        }
    }

    private TopicPartition getReplyTopicPartition(String topicReplyName) {
        Iterator topicPartitionIterator = this.getAssignedReplyTopicPartitions().iterator();

        TopicPartition topicPartition;
        do {
            if (!topicPartitionIterator.hasNext()) {
                return null;
            }

            topicPartition = (TopicPartition)topicPartitionIterator.next();
        } while(!topicPartition.topic().equalsIgnoreCase(topicReplyName));

        return topicPartition;
    }

    private String getReplyTopicHeader(ProducerRecord<K, V> record) {
        Iterator iterator = record.headers().iterator();

        Header header;
        do {
            if (!iterator.hasNext()) {
                return null;
            }

            header = (Header)iterator.next();
        } while(!header.key().equalsIgnoreCase("kafka_replyTopic"));

        return new String(header.value());
    }
}
