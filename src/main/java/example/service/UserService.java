package example.service;

import example.dto.UserDto;
import example.entity.UserEntity;
import example.repository.UserRepository;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;


    public boolean login(UserDto userDto){
        UserEntity saveEntity = userDto.toEntity();
         saveEntity= userRepository.save(saveEntity);

         if (saveEntity.getUser_no()<=1)return true;
         return false;
    }

    public List<UserDto> find(){
        List<UserEntity>  userEntityList =userRepository.findAll();
        List<UserDto> list = new ArrayList<>();
        userEntityList.forEach( entity->{
            UserDto userDto = entity.toDto();
            list.add(userDto);
        });
        return list;
    }

    public UserDto findDetail(int user_no){
        Optional<UserEntity> optional =userRepository.findById(user_no);

        if (optional.isPresent()){
            UserEntity userEntity = optional.get();
            UserDto userDto = userEntity.toDto();
            return userDto;
        }
        return null;
    }


    public boolean delete(int user_no){

        boolean exist = userRepository.existsById(user_no);
        if (exist){
              userRepository.deleteById(user_no);
              return true;
        }
        return false;
    }


    public boolean update(UserDto userDto){
        int updatePk = userDto.getUser_no();
        Optional<UserEntity> optional =userRepository.findById(updatePk);
        if (optional.isPresent()){
            UserEntity updateEntity = optional.get();
            updateEntity.setNickname(userDto.getNickname());
            updateEntity.setUid(userDto.getUid());
            updateEntity.setUpwd(userDto.getUpwd());
            return true;
        } else return false;
    }
}


