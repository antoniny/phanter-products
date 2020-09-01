package br.com.awm.pantherproducts.kafka;

import br.com.awm.pantherproducts.kafka.config.lib.CompletableFutureReplyingKafkaOperations;
import br.com.awm.pantherproducts.kafka.enums.ErrorCode;
import br.com.awm.pantherproducts.kafka.exceptions.KafkaTimedOutException;
import br.com.awm.pantherproducts.kafka.exceptions.TopicResponseErrorHandler;
import com.google.gson.Gson;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.header.internals.RecordHeader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.concurrent.ExecutionException;

@Component
public class KafkaRequestReplySender {

    @Autowired
    private CompletableFutureReplyingKafkaOperations<String, String, String> requestReplyKafkaTemplate;

    @Autowired
    TopicResponseErrorHandler topicResponseErrorHandler;

    public <T> String send(String topicRequest, String topicReply, T requestObject, Map<String, String> extraHeaders) throws ExecutionException, InterruptedException {
        try {
            ProducerRecord<String, String> record = new ProducerRecord<>(topicRequest, new Gson().toJson(requestObject));
            record.headers().add(new RecordHeader(KafkaHeaders.TOPIC, topicRequest.getBytes()));
            record.headers().add(new RecordHeader(KafkaHeaders.REPLY_TOPIC, topicReply.getBytes()));
            if (Optional.ofNullable(extraHeaders).isPresent()) {
                addRecordHeader(extraHeaders, record);
            }

            String reply = requestReplyKafkaTemplate.requestReply(record).get();
            topicResponseErrorHandler.handleExceptions(reply);
            return reply;
        } catch (ExecutionException | InterruptedException ex) {
            if (ex.getCause().getMessage().equals(ErrorCode.KAFKA_TIMED_OUT.getMessage())) {
                throw new KafkaTimedOutException(ex.getMessage());
            }
            throw ex;
        }
    }

    public <T> String send(String topicRequest, String topicReply, T requestObject) throws ExecutionException, InterruptedException {
        return send(topicRequest, topicReply, requestObject, null);
    }

    private void addRecordHeader(Map<String, String> headers, ProducerRecord<String, String> record) {
        for (Entry<String, String> entry : headers.entrySet()) {
            record.headers().add(new RecordHeader(entry.getKey(), entry.getValue().getBytes()));
        }
    }

}