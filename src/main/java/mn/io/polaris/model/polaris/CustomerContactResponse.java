package mn.io.polaris.model.polaris;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonDeserialize(using = CustomerContactResponse.CustomerContactResponseDeserializer.class)
public class CustomerContactResponse {

    private List<Object> returningArray;

    public static class CustomerContactResponseDeserializer extends JsonDeserializer<CustomerContactResponse> {
        @Override
        public CustomerContactResponse deserialize(JsonParser jp, DeserializationContext context) {

            ObjectMapper mapper = (ObjectMapper) jp.getCodec();
            List<Object> myArray;
            try {
                myArray = mapper.readValue(jp, new TypeReference<>() {
                });
            } catch (IOException e) {
                throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
            }

            CustomerContactResponse response = new CustomerContactResponse();
            response.setReturningArray(new ArrayList<>(myArray));
            return response;
        }
    }

}
