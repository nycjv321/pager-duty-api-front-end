package com.nycjv321.pagerdutytools.utilities;

import com.google.gson.JsonObject;
import com.nycjv321.pagerdutytools.documents.models.Incident;
import com.nycjv321.pagerdutytools.exceptions.UnResolvedIncidentsException;
import com.nycjv321.pagerdutytools.rest.processor.IncidentProcessor;

/**
 * Created by jvelasquez on 4/19/15.
 */
public class PagerDutySynchronizer {
    private static IncidentProcessor incidentProcessor = new IncidentProcessor();

    // Move me?
    public static JsonObject synchronize() {
        JsonObject result = new JsonObject();
        result.addProperty("title", "Synchronization Result");
        try {
            result.addProperty("updated", incidentProcessor.processResolvedIncidents());
        } catch (UnResolvedIncidentsException e) {
            result.addProperty("updated", false);
            result.addProperty("message", e.getMessage());
        }
        return result;
    }

    public static JsonObject delete(int incidentNumber) {
        JsonObject result = new JsonObject();
        Incident.find(incidentNumber).delete();
        return result;
    }

}
