package com.xianyu.yixian_client.Frame.Ready.Model;

import com.yixian.material.Entity.CardGroup;
import com.yixian.material.Entity.User;

public class UserWithCardGroupItem {
    private User user;
    private CardGroup cardGroup;

    public UserWithCardGroupItem(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public CardGroup getCardGroup() {
        return cardGroup;
    }

    public void setCardGroup(CardGroup cardGroup) {
        this.cardGroup = cardGroup;
    }
}
