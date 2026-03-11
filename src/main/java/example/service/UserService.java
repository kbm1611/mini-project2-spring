package example.service;

import example.dto.UserDto;
import example.entity.UserEntity;
import example.repository.UserRepository;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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

    public boolean findDetail(int user_no){
        return false;
    }

    public boolean delete(int user_no){
        return false;
    }

    public boolean update(UserDto userDto){
        return false;
    }
}


