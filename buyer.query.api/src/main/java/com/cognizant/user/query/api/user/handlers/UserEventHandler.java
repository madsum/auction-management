package com.cognizant.user.query.api.user.handlers;

import com.cognizant.user.core.events.UserRegisteredEvent;
import com.cognizant.user.core.events.UserRemovedEvent;
import com.cognizant.user.core.events.UserUpdatedEvent;

public interface UserEventHandler {
    void on(UserRegisteredEvent event);
    void on(UserUpdatedEvent event);
    void on(UserRemovedEvent event);
}
