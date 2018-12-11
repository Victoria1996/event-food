package by.bsu.eventfood.service;

import by.bsu.eventfood.model.Client;
import by.bsu.eventfood.model.RoleName;
import org.springframework.stereotype.Service;

import java.util.HashMap;

import static by.bsu.eventfood.model.RoleName.BUSINESS_CLIENT;
import static by.bsu.eventfood.model.RoleName.GENERAL_CLIENT;
import static org.apache.logging.log4j.util.Strings.EMPTY;

public interface RoleActionUrlResolver {
    enum ActionUrl {
        SIGN_UP(new HashMap() {{
            put(GENERAL_CLIENT, "/u1");
            put(BUSINESS_CLIENT, "/u2");
        }}),

        SIGN_IN(new HashMap() {{
            put(GENERAL_CLIENT, "/u3");
            put(BUSINESS_CLIENT, "/u4");
        }}),

        ADD_PLACE(new HashMap() {{
            put(BUSINESS_CLIENT, "/u4");
        }}),

        ADD_EVENT(new HashMap() {{
            put(BUSINESS_CLIENT, "/u4");
        }});

        HashMap<RoleName, String> mapping;

        ActionUrl(HashMap<RoleName, String> map) {
            this.mapping = map;
        }

        public String url(RoleName roleName) {
            return mapping.get(roleName);
        }
    }

    default String resolve(ActionUrl action, Client client) {
        return client != null && client.getRole() != null ?
                action.url(client.getRole().getName()) : EMPTY;
    }


    @Service
    class RoleActionUrlResolverImpl implements RoleActionUrlResolver {

    }
}
