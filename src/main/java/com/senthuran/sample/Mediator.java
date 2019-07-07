package com.senthuran.sample;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.synapse.MessageContext;
import org.apache.synapse.commons.json.JsonUtil;
import org.apache.synapse.core.axis2.Axis2MessageContext;
import org.apache.synapse.mediators.AbstractMediator;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

public class Mediator extends AbstractMediator  {

    private static final Log log = LogFactory.getLog(Mediator.class);
    public boolean mediate(MessageContext mc) {
        if (log.isDebugEnabled()) {
            log.debug("Mediator is on");
        }
        String jsonPayloadToString = JsonUtil.jsonPayloadToString(((Axis2MessageContext) mc).getAxis2MessageContext());
        if (log.isDebugEnabled()) {
            log.debug("Payload : " + jsonPayloadToString);
        }
        try {
            JSONObject payLoad = new JSONObject(jsonPayloadToString);
            payLoad.put("Mediator", "MediatorTest");
            JsonUtil.newJsonPayload(((Axis2MessageContext) mc).getAxis2MessageContext(), payLoad.toString(), true, true);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return true;
    }
}
