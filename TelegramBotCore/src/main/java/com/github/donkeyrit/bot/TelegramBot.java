package com.github.donkeyrit.bot;

import com.github.donkeyrit.models.request.GetUpdatesRequest;
import com.github.donkeyrit.models.response.Update;
import com.github.donkeyrit.models.response.User;

import java.util.Optional;

public interface TelegramBot 
{
    User getMe() throws Exception;
    Update[] getUpdates(Optional<GetUpdatesRequest> request) throws Exception;
}
