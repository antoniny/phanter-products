package br.com.awm.pantherproducts.kafka.config.lib;

import java.util.concurrent.CompletableFuture;
import org.apache.kafka.clients.producer.ProducerRecord;

public interface CompletableFutureReplyingKafkaOperations<K, V, R> {
    CompletableFuture<R> requestReply(ProducerRecord<K, V> record);
}

