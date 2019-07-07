package com.senthuran.sample;

import org.apache.synapse.AbstractSynapseHandler;
import org.apache.synapse.MessageContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.synapse.core.axis2.Axis2MessageContext;
import org.json.JSONObject;
import org.apache.synapse.commons.json.JsonUtil;


public class handler extends AbstractSynapseHandler {

    private static final Log log = LogFactory.getLog(com.senthuran.sample.handler.class);

    public boolean handleRequestInFlow(MessageContext mc) {
        log.debug("Request In Flow");
        return true;
    }

    public boolean handleRequestOutFlow(MessageContext mc) {

        log.debug("Request Out Flow");
        return true;
    }


    public boolean handleResponseInFlow(MessageContext mc) {
        log.debug("Response In Flow");
        return true;
    }


    public boolean handleResponseOutFlow(MessageContext mc) {
        log.debug("Response Out Flow");
        String jsonPayloadToString = JsonUtil.jsonPayloadToString(((Axis2MessageContext) mc).getAxis2MessageContext());
        if (log.isDebugEnabled()) {
            log.debug("Payload : " + jsonPayloadToString);
        }
        JSONObject jsonBody = new JSONObject(jsonPayloadToString);
        jsonBody.put("Handler", "HandlerTest");
        String transformedJson = jsonBody.toString();
        JsonUtil.newJsonPayload(((Axis2MessageContext) mc).getAxis2MessageContext(), transformedJson, true, true);
        return true;
    }


}