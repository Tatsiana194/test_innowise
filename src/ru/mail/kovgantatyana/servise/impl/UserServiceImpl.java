package ru.mail.kovgantatyana.servise.impl;

import ru.mail.kovgantatyana.repository.PhoneRepository;
import ru.mail.kovgantatyana.repository.RoleRepository;
import ru.mail.kovgantatyana.repository.UserRepository;
import ru.mail.kovgantatyana.repository.impl.PhoneRepositoryImpl;
import ru.mail.kovgantatyana.repository.impl.RoleRepositoryImpl;
import ru.mail.kovgantatyana.repository.impl.UserRepositoryImpl;
import ru.mail.kovgantatyana.repository.model.Phone;
import ru.mail.kovgantatyana.repository.model.Role;
import ru.mail.kovgantatyana.repository.model.User;
import ru.mail.kovgantatyana.repository.model.UserRole;
import ru.mail.kovgantatyana.servise.UserService;
import ru.mail.kovgantatyana.servise.converter.PhoneConverter;
import ru.mail.kovgantatyana.servise.converter.RoleConverter;
import ru.mail.kovgantatyana.servise.converter.UserConverter;
import ru.mail.kovgantatyana.servise.model.UserDTO;
import ru.mail.kovgantatyana.servise.validator.EmailValidator;
import ru.mail.kovgantatyana.servise.validator.PhoneValidator;
import ru.mail.kovgantatyana.servise.validator.RoleValidator;

import java.util.ArrayList;
import java.util.List;

public class UserServiceImpl implements UserService {
    private static UserServiceImpl instance;
    private UserRepository userRepository = UserRepositoryImpl.getInstance();
    private RoleRepository roleRepository = RoleRepositoryImpl.getInstanse();
    private PhoneRepository phoneRepository = PhoneRepositoryImpl.getInstanse();
    private EmailValidator emailValidator = new EmailValidator();
    private PhoneValidator phoneValidator = new PhoneValidator();
    private RoleValidator roleValidator = new RoleValidator();

    private UserServiceImpl() {
    }

    public static UserServiceImpl getInstance() {
        if (instance == null) {
            instance = new UserServiceImpl();
        }
        return instance;
    }

    @Override
    public UserDTO getUserDTOByUserName(String userName) {
        User user = userRepository.getUserByUserName(userName);
        List<Phone> phoneList = phoneRepository.getPhoneListByUser(user.getId());
        List<Role> roleList = roleRepository.getRoleListByUser(user.getId());
        UserDTO userDTO = UserConverter.userToUserDTO(user);
        List<String> phones = new ArrayList<>();
        for (Phone phone : phoneList) {
            phones.add(phone.getNumber());
        }
        userDTO.setPhoneList(phones);
        List<String> roles = new ArrayList<>();
        for (Role role : roleList) {
            roles.add(role.getRole());
        }
        userDTO.setRoleList(roles);
        return userDTO;
    }

    @Override
    public List<UserDTO> getUserDTOList() {
        List<UserDTO> userDTOList = new ArrayList<>();
        List<User> listUser = userRepository.getUserList();
        for (User user : listUser) {
            UserDTO userDTO = UserConverter.userToUserDTO(user);
            userDTOList.add(userDTO);
        }
        return userDTOList;
    }

    @Override
    public void saveUser(UserDTO userDTO) {
        User user = UserConverter.userDtoToUser(userDTO);
        int userId = userRepository.saveUser(user);

        List<String> rolesFromUser = userDTO.getRoleList();
        List<UserRole> userRoleList = new ArrayList<>();
        for (int i = 0; i <= rolesFromUser.size(); i++) {
            userRoleList.add(RoleConverter.userRoleCreator(rolesFromUser.get(i), userId));
        }
        roleRepository.saveRoles(userRoleList);

        List<String> phonesFromUser = userDTO.getPhoneList();
        List<Phone> phoneList = new ArrayList<>();
        for (int i = 0; i <= rolesFromUser.size(); i++) {
            phoneList.add(PhoneConverter.phoneDtoToPhone(phonesFromUser.get(i), userId));
        }
        phoneRepository.savePhones(phoneList);
    }

    @Override
    public void deleteUser(int userId) {
        userRepository.deleteUser(userId);
    }

    @Override
    public List<String> userValidation(UserDTO userDTO) {
        List<String> errors = new ArrayList<>();
        errors.addAll(emailValidator.checkEmail(userDTO.getEmail()));
        errors.addAll(roleValidator.checkRole(userDTO.getRoleList()));
        errors.addAll(phoneValidator.checkPhone(userDTO.getPhoneList()));
        return errors;
    }

    @Override
    public void updateUser(UserDTO userDTO) {
        userRepository.updateUser(UserConverter.userDtoToUser(userDTO), userDTO.getId());
    }
}
