package com.github.donkeyrit.models.update;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonValue;

@JsonFormat(shape=JsonFormat.Shape.STRING)
public enum UpdateType 
{
    UNKNOWN("unknown"),
    MESSAGE("message"),
    EDITED_MESSAGE("edited_message"),
    CHANNEL_POST("channel_post"),
    EDITED_CHANNEL_POST("edited_channel_post"),
    INLINE_QUERY("inline_query"),
    CHOSEN_INLINE_RESULT("chosen_inline_result"),
    CALLBACK_QUERY("callback_query"),
    SHIPPING_QUERY("shipping_query"),
    PRE_CHECKOUT_QUERY("pre_checkout_query"),
    POLL("poll"),
    POLL_ANSWER("poll_answer"),
    MY_CHAT_MEMBER("my_chat_member"),
    CHAT_MEMBER("chat_member"),
    CHAT_JOIN_REQUEST("chat_join_request");

    private String type;

    private UpdateType(String type)
    {
        this.type = type;
    }

    @JsonValue
    public String getType()
    {
        return this.type;
    }
}
