package messaging;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import orders.api.FoodOrder;
import org.apache.kafka.common.serialization.Deserializer;
import org.apache.kafka.common.serialization.Serializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

public class OrderSerializer implements Serializer<JVROMessageType>, Deserializer<FoodOrder> {
    private static final Logger LOGGER = LoggerFactory.getLogger(OrderSerializer.class);

    private ObjectMapper mapper =  new ObjectMapper();

    @Override
    public FoodOrder deserialize(String topic, byte[] data) {
        return null;
    }

    @Override
    public void configure(Map<String, ?> configs, boolean isKey) {

    }

    @Override
    public byte[] serialize(String topic, JVROMessageType obj) {
        try {
            var message = new JVROMessage(mapper.writeValueAsBytes(obj), obj.getClass());
            return mapper.writeValueAsBytes(message);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            throw new RuntimeException("Invalid message type");
        }
    }

    @Override
    public void close() {
        LOGGER.info("Should be doing some deserialized clean up[");
    }
}
