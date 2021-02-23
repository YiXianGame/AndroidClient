package com.yixian.make.Event.ReadyEvent.Args;

import com.yixian.material.Entity.User;

public class InviteUserArgs {
    User inviter;
    String secretKey;

    public InviteUserArgs(User inviter, String secretKey) {
        this.inviter = inviter;
        this.secretKey = secretKey;
    }

    public User getInviter() {
        return inviter;
    }

    public void setInviter(User inviter) {
        this.inviter = inviter;
    }

    public String getSecretKey() {
        return secretKey;
    }

    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }
}
