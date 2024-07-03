package au.com.telstra.simcardactivator.controller;


import au.com.telstra.simcardactivator.model.SimCardActivationRequest;
import au.com.telstra.simcardactivator.model.SimCardActuatorRequest;
import au.com.telstra.simcardactivator.model.SimCardActuatorResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Objects;

@RestController
@RequestMapping("/activate")
public class SimCardActivationController {
//    @Autowired
//    private RestTemplate restTemplate;


//    @PostMapping
//    public ResponseEntity<String> activateSimCard(@RequestBody SimCardActivationRequest request) {
//        HttpHeaders headers = new HttpHeaders();
//        headers.setContentType(MediaType.APPLICATION_JSON);
//
//        HttpEntity<SimCardActuatorRequest> entity = new HttpEntity<>(new SimCardActuatorRequest(request.getIccid()), headers);
//
//        ResponseEntity<SimCardActuatorResponse> response = restTemplate.exchange(
//                "http://localhost:8444/actuate",
//                HttpMethod.POST,
//                entity,
//                SimCardActuatorResponse.class);
//
//        boolean success = response.getBody().isSuccess();
//        String result = success ? "Activation successful" : "Activation failed";
//        return ResponseEntity.ok(result);
//    }


    @PostMapping("/activate")
    public ResponseEntity<String> activateSim(@RequestBody SimCardActivationRequest request) {
        RestTemplate restTemplate = new RestTemplate();
        String actuatorUrl = "http://localhost:8444/actuate";

        // Create the payload for the actuator
        SimCardActuatorRequest actuatorRequest = new SimCardActuatorRequest(request.getIccid());

        // Send the request to the actuator
        ResponseEntity<SimCardActuatorResponse> response = restTemplate.postForEntity(actuatorUrl, actuatorRequest, SimCardActuatorResponse.class);

        // Process the response
        if (Objects.requireNonNull(response.getBody()).isSuccess()) {
            return ResponseEntity.ok("SIM activation successful");
        } else {
            return ResponseEntity.status(500).body("SIM activation failed");
        }
    }
}

