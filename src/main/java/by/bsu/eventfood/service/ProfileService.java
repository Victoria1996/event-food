package by.bsu.eventfood.service;

import by.bsu.eventfood.controller.resource.ProfileResource;
import by.bsu.eventfood.model.Client;

public interface ProfileService {
    ProfileResource getCurrentProfileInfo(Client client);
}
