package com.ozodbek.classifieds.user;

import com.ozodbek.classifieds.exception.EmailAlreadyExistsException;
import com.ozodbek.classifieds.exception.UserNotFoundException;
import com.ozodbek.classifieds.user.dto.CreateUserRequest;
import com.ozodbek.classifieds.user.dto.UpdateUserRequest;
import com.ozodbek.classifieds.user.dto.UserResponse;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserResponse createUser(CreateUserRequest request) {
        String email = request.email().trim().toLowerCase();

        if (userRepository.existsByEmail(email)) {
            throw new EmailAlreadyExistsException("User with this email already exists.");
        }

        User user = new User(request.name(), email);
        userRepository.save(user);

        return UserResponse.from(user);
    }

    @Transactional(readOnly = true)
    public UserResponse getById(Long id) {
        User user = findById(id);

        return UserResponse.from(user);
    }

    public UserResponse update(Long id, UpdateUserRequest request) {
        User user = findById(id);

        if (request.name() != null) {
            user.changeName(request.name());
        }

        if (request.email() != null) {
            String email = request.email().trim().toLowerCase();

            if (!user.getEmail().equals(email) && userRepository.existsByEmail(email)) {
                throw new EmailAlreadyExistsException("Email already exists.");
            }

            user.changeEmail(email);
        }

        return UserResponse.from(user);
    }

    public void delete(Long id) {
        User user = findById(id);

        userRepository.delete(user);
    }

    private User findById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User not found."));
    }
}
