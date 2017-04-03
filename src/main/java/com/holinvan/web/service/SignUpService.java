package com.holinvan.web.service;

import static org.apache.commons.lang3.RandomStringUtils.randomAlphabetic;

import java.util.ArrayList;
import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionKey;
import org.springframework.social.connect.ConnectionSignUp;
import org.springframework.social.connect.UserProfile;
import org.springframework.social.facebook.api.Facebook;
import org.springframework.social.google.api.Google;
import org.springframework.stereotype.Service;

import com.holinvan.web.model.Authority;
import com.holinvan.web.model.User;
import com.holinvan.web.model.UserAuthority;
import com.holinvan.web.model.UserData;
import com.holinvan.web.repository.AuthorityRepository;
import com.holinvan.web.repository.UserRepository;
import com.holinvan.web.type.Role;

@Service
public class SignUpService implements ConnectionSignUp{
	
	private static Logger LOG = LoggerFactory.getLogger(SignUpService.class);
	
	@Autowired
    private UserRepository userRepository;
	
	@Autowired
    private AuthorityRepository authorityRepository;
 
    @Override
    public String execute(Connection<?> connection) {
    	ConnectionKey key = connection.getKey();
    	LOG.debug("key= (" + key.getProviderId() + "," + key.getProviderUserId() + ")");
        UserData userData = new UserData();
        if (connection.getApi() instanceof Google) {
        	userData = setUserProfileGoogle(connection);
        	LOG.debug("Logged user from GOOGLE: " + userData.getEmail());
        }
        else if (connection.getApi() instanceof Facebook) {
        	userData = setUserProfileFacebook(connection);
        	LOG.debug("Logged user from FACEBOOK: " + userData.getName());
        }
        User user = saveSocialUser(userData);
        return user.getUserData().getName();
    }
    
    public Authority getUserAuthority(){
    	return authorityRepository.findByName(Role.USER.getValue());
    }
    
    private UserData setUserProfileGoogle(Connection<?> connection) {
    	UserProfile userProfile = connection.fetchUserProfile();
    	return new UserData(userProfile.getFirstName(), userProfile.getLastName(), userProfile.getEmail());
    }
    
    /*BUG http://stackoverflow.com/questions/39890885/error-message-is-12-bio-field-is-deprecated-for-versions-v2-8-and-higher */
    private UserData setUserProfileFacebook(Connection<?> connection) {
    	return new UserData(connection.getDisplayName(), "#", "noinfo@mail.com");
    }
    
    private User saveSocialUser(UserData userData) {
    	User user = new User(userData.getName(), randomAlphabetic(8), Arrays.asList(new SimpleGrantedAuthority(Role.USER.getValue())), userData, null);
        userData.setUser(user);
        UserAuthority userAuthority = new UserAuthority(getUserAuthority(), user);
        user.setUserAuthorities(new ArrayList<UserAuthority>());
        user.addUserAuthority(userAuthority);
        userRepository.save(user);
        return user;
    }
}
