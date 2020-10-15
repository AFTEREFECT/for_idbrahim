package baddou.mohamed.services.impl;

import baddou.mohamed.entities.UserEntity;
import baddou.mohamed.repositories.UserRepository;
import baddou.mohamed.services.UserService;
import baddou.mohamed.shared.UserDto;
import baddou.mohamed.shared.Utils;
import com.fasterxml.jackson.databind.RuntimeJsonMappingException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;


@Service
public class UserServiceImpl implements UserService {
   @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    UserRepository userRepository;

    @Autowired
    Utils utils ;

    // overid t la classe bcryptencoder de mot de pass


    @Override
    public UserDto createUser(UserDto user) {

        //chek
        UserEntity checUser = userRepository.findByEmail(user.getEmail());
        if (checUser != null ) throw new RuntimeJsonMappingException("duplicated user  ") ;


        UserEntity userEntity = new UserEntity();



        BeanUtils.copyProperties(user, userEntity);


        // faire appelle a l encodage du mot de pass via la methode
        userEntity.setEncryptedPassword(bCryptPasswordEncoder.encode(user.getPassword()));


        // faire appelle a la generation id avant d etre percister
        userEntity.setUserId(utils.generateStringId(32));


        // save


        UserEntity newUser = userRepository.save(userEntity);
        UserDto userDto = new UserDto();
        BeanUtils.copyProperties(newUser, userDto);
        return userDto;
    }

    @Override
    public UserDto getUser(String username) {
        // TODO Auto-generated method stub

        UserEntity userEntity = userRepository.findByEmail(username);
        if (userEntity == null)
            throw new RuntimeException(username);
        UserDto userDto = new UserDto();
        BeanUtils.copyProperties(userEntity, userDto);
        return userDto;
    }


/////////////////////////////////////////////////////////////////////


    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

      UserEntity userEntity=  userRepository.findByEmail(email);
      if(userEntity==null)  throw new UsernameNotFoundException(email);
        return new User(userEntity.getEmail(),userEntity.getEncryptedPassword(),new ArrayList<>());
    }
 //////////////////////////////////////////////////////////////////
}
