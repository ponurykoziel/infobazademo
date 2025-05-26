package pl.zenit.infobazademo.data.controller;

import pl.zenit.infobazademo.data.domain.FromDomainAdapter;

class RosterUserDomainAdapter implements FromDomainAdapter<RosterUser> {

    public RosterUser translate(pl.zenit.infobazademo.data.domain.User input) {
        return new RosterUser(input.id(), input.name(), input.username(), input.email(), input.phone());
    }

}
