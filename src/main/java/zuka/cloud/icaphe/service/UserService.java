package zuka.cloud.icaphe.service;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import zuka.cloud.icaphe.entity.User;

import java.util.List;
import java.util.Optional;

@Service
public interface UserService {

    void save(User user);

    public Page<User> findAllUser(Integer page, Integer size);

    public User findUserById(long idShop);

    public void create(User user);

    public void remove(long id);

    public void update(long id, User user);

}
