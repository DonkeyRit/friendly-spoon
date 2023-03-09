package com.github.telegrambotstepfather.models.chat;

import java.util.Optional;

import com.github.telegrambotstepfather.models.response.User;

/**
 * Represents an invite link for a chat.
 * @see <a href="https://core.telegram.org/bots/api#chatinvitelink">Telegram API ChatInviteLink</a>
 */
public record ChatInviteLink(
    /**
     * The invite link. If the link was created by another chat administrator, 
     * then the second part of the link will be replaced with “…”.
     */
    String inviteLink,
    /**
     * Creator of the link
     */
    User creator,
    /**
     * True, if users joining the chat via the link need to be approved by chat administrators
     */
    boolean createsJoinRequest,
    /**
     * True, if the link is primary
     */
    boolean isPrimary,
    /**
     * True, if the link is revoked
     */
    boolean isRevoked,
    /**
     * Invite link name
     */
    Optional<String> name,
    /**
     * Point in time (Unix timestamp) when the link will expire or has been expired
     */
    Optional<Integer> expireDate,
    /**
     * The maximum number of users that can be members of the chat simultaneously 
     * after joining the chat via this invite link; 1-99999
     */
    Optional<Integer> memberLimit,
    /**
     * Number of pending join requests created using this link
     */
    Optional<Integer> pendingJoinRequestCount
) 
{
    
}
