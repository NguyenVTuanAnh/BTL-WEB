package food_store.example.foodstore.security.oauth2;

import food_store.example.foodstore.model.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.Collection;
import java.util.List;
import java.util.Map;

public class CustomOAuth2User implements OAuth2User {

    private String provider;
    private OAuth2User oauth2User;

    public String getProvider() {
        return provider;
    }

    public CustomOAuth2User(OAuth2User oauth2User, String provider) {
        this.oauth2User = oauth2User;
        this.provider = provider;
    }

    public String getUrlImage() {
        if (provider.compareToIgnoreCase("GOOGLE") == 0) {
            return oauth2User.getAttribute("picture");
        } else if (provider.compareToIgnoreCase("GITHUB") == 0) {
            return oauth2User.getAttribute("avatar_url");
        } else if (provider.compareToIgnoreCase("FACEBOOK") == 0) {
            Map<String, Object> picture = oauth2User.getAttribute("picture");
            if (picture != null) {
                Map<String, Object> dataImageFacebook = (Map<String, Object>) picture.get("data");
                if (dataImageFacebook != null) {
                    return (String) dataImageFacebook.get("url");
                }
            }
            return oauth2User.getAttribute("facebook");
        }
        return null;
    }

    public String getFullname(){
        if (provider.compareToIgnoreCase("GOOGLE") == 0) {
            return oauth2User.getAttribute("name");
        } else if (provider.compareToIgnoreCase("FACEBOOK") == 0) {
            return oauth2User.getAttribute("name");
        } else if (provider.compareToIgnoreCase("GITHUB") == 0) {
            return oauth2User.getAttribute("login");
        }
        return null;
    }

    public String getEmail() {
        if (provider.compareToIgnoreCase("GOOGLE") == 0) {
            return oauth2User.getAttribute("email");
        } else if (provider.compareToIgnoreCase("FACEBOOK") == 0) {
            return oauth2User.getAttribute("email");
        } else if (provider.compareToIgnoreCase("GITHUB") == 0) {
            return oauth2User.getAttribute("login");
        }
        return null;
    }



    @Override
    public <A> A getAttribute(String name) {
        return OAuth2User.super.getAttribute(name);
    }

    @Override
    public Map<String, Object> getAttributes() {
        return oauth2User.getAttributes();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return oauth2User.getAuthorities();
    }



    @Override
    public String getName() {
        if (provider.compareToIgnoreCase("GOOGLE") == 0) {
            return oauth2User.getAttribute("name");
        } else if (provider.compareToIgnoreCase("FACEBOOK") == 0) {
            return oauth2User.getAttribute("email");
        } else if (provider.compareToIgnoreCase("GITHUB") == 0) {
            return oauth2User.getAttribute("login");
        }
        return null;
    }
}
