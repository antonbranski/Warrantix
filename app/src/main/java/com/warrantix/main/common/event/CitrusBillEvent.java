package com.warrantix.main.common.event;

import com.warrantix.main.common.rest.response.CitrusBillResponse;

/**
 * Created on 10/07/16.
 */
public class CitrusBillEvent {

    private CitrusBillResponse citrusBillResponse = new CitrusBillResponse();

    public CitrusBillEvent(CitrusBillResponse citrusBillResponse) {
        this.citrusBillResponse = citrusBillResponse;
    }

    public CitrusBillResponse getCitrusBillResponse() {
        return citrusBillResponse;
    }
}
