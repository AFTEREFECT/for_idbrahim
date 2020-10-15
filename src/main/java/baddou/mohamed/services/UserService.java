package baddou.mohamed.services;

import baddou.mohamed.shared.UserDto;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService  extends UserDetailsService {

   UserDto createUser(UserDto userDto) ;


   UserDto getUser(String userName);
}
