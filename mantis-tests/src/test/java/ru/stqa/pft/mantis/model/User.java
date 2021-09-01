package ru.stqa.pft.mantis.model;

import com.google.common.collect.ForwardingSet;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class User extends ForwardingSet<UserData> {

    private Set<UserData> delegate;

    public User(User user) {
        this.delegate = new HashSet<UserData>(user.delegate);
    }

    public User() {
        this.delegate = new HashSet<UserData>();
    }

    public User(Collection<UserData> user) {
        this.delegate = new HashSet<UserData>(user);
    }

    @Override
    protected Set<UserData> delegate() {
        return delegate;
    }

    public User withAdded(UserData userData) {
        User user = new User(this);
        user.add(userData);
        return user;
    }

    public User withoutAdded(UserData userData) {
        User user = new User(this);
        user.remove(userData);
        return user;
    }

}
