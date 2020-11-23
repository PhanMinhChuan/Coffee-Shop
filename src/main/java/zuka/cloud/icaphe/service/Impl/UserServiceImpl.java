package zuka.cloud.icaphe.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import zuka.cloud.icaphe.entity.CustomUserDetails;
import zuka.cloud.icaphe.entity.User;
import zuka.cloud.icaphe.repository.UserRepository;
import zuka.cloud.icaphe.service.UserService;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService, UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public void save(User user) {
        userRepository.save(user);
    }

    @Override
    public Page<User> findAllUser(Integer page, Integer size) {
        Pageable pageable = PageRequest.of(page, size);
        return (Page<User>) userRepository.findAll(pageable);
    }

    @Override
    public User findUserById(long idShop) {
        return userRepository.findById(idShop);
    }

    @Override
    public void create(User userNew) {
       userRepository.save(userNew);
    }

    @Override
    public void remove(long id) {
        userRepository.deleteUser(id);
    }

    @Override
    public void update (long id, User userUpdate) {
            findUserById(id).setPassword(userUpdate.getPassword());
            userRepository.save(userUpdate);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException(username);
        }
        return new CustomUserDetails(user.getId(), user.getUsername(), user.getPassword(), user.getRole());
    }

    @Transactional
    public UserDetails loadUserById(long id) {
        User user = userRepository.findById(id);
        return new CustomUserDetails(user.getId(), user.getUsername(), user.getPassword(), user.getRole());
    }


}
