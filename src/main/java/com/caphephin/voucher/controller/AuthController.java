package com.caphephin.voucher.controller;

import com.caphephin.voucher.model.Role;
import com.caphephin.voucher.model.User;
import com.caphephin.voucher.repository.RoleRepository;
import com.caphephin.voucher.repository.UserRepository;
import com.caphephin.voucher.service.AESService;
import com.caphephin.voucher.service.EmailService;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang.RandomStringUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.*;

@RestController
@RequestMapping("/api")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserRepository userRepository;

    private AESService aesService;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private EmailService emailService;

    @Autowired
    Environment env;

    @PostMapping("/signin")
    public ResponseEntity<String> authenticateUser(@RequestHeader(name = "accept-language", required=false) String language, @RequestParam String phone, @RequestParam String password){
        try {
            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                    phone, password));
            SecurityContextHolder.getContext().setAuthentication(authentication);
        } catch (Exception e) {
            if(language !=null && language.toLowerCase().equals("vi")) {
                return new ResponseEntity<>("T??n ????ng nh???p ho???c m???t kh???u kh??ng ????ng", HttpStatus.UNAUTHORIZED);
            }
            if(language !=null && language.toLowerCase().equals("ja")) {
                return new ResponseEntity<>("?????????ID/?????????????????????????????????????????????????????????????????????", HttpStatus.UNAUTHORIZED);
            }
            return new ResponseEntity<>("The username and password is incorrect", HttpStatus.UNAUTHORIZED);
        }
        return new ResponseEntity<>(aesService.getJWTToken(phone), HttpStatus.OK);
    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@RequestHeader(name = "accept-language", required=false) String language, @RequestParam String phone, @RequestParam String fullName, @RequestParam String password,
                                          @RequestParam String email, @RequestParam(required=false) String birthday, @RequestPart(required=false) MultipartFile uploadfile) {

        String filename = "";
        try {
            if (uploadfile!=null && !uploadfile.isEmpty()) {
                filename = saveUploadedFiles(Arrays.asList(uploadfile));
            }
        } catch (IOException e) {
            return new ResponseEntity < > (HttpStatus.BAD_REQUEST);
        }

        // add check for username exists in a DB
        if(userRepository.existsByUsername(phone)){
            if(language !=null && language.toLowerCase().equals("vi")) {
                return new ResponseEntity<>("T??n ????ng nh???p ???? ???????c s??? d???ng!", HttpStatus.BAD_REQUEST);
            }

            if(language !=null && language.toLowerCase().equals("ja")) {
                return new ResponseEntity<>("?????????????????????????????????ID????????????????????????????????????", HttpStatus.BAD_REQUEST);
            }

            return new ResponseEntity<>("Username already in use", HttpStatus.BAD_REQUEST);
        }

        // add check for email exists in DB
        if(userRepository.existsByEmail(email)){
            if(language !=null && language.toLowerCase().equals("vi")) {
                return new ResponseEntity<>("Email ???? ???????c s??? d???ng!", HttpStatus.BAD_REQUEST);
            }
            if(language !=null && language.toLowerCase().equals("ja")) {
                return new ResponseEntity<>("?????????????????????????????????????????????????????????????????????????????????!", HttpStatus.BAD_REQUEST);
            }
            return new ResponseEntity<>("Email already in use", HttpStatus.BAD_REQUEST);
        }

        // create user object
        User user = new User();
        user.setFullName(fullName);
        user.setUsername(phone);
        user.setEmail(email);
        user.setBirthday(birthday);
        if(filename != null && !filename.isEmpty()) {
            user.setAvatar(env.getProperty("imageUrl") + "avatar/" + filename);
        }
        user.setPassword(passwordEncoder.encode(password));

        Role roles = roleRepository.findByName("ROLE_USER").get();
        user.setRoles(Collections.singleton(roles));

        userRepository.save(user);

        if(language !=null && language.toLowerCase().equals("vi")) {
            return new ResponseEntity<>("B???n ???? ????ng k?? th??nh c??ng!", HttpStatus.OK);
        }

        if(language !=null && language.toLowerCase().equals("ja")) {
            return new ResponseEntity<>("???????????????????????????????????????", HttpStatus.OK);
        }

        return new ResponseEntity<>("You have successfully registered!", HttpStatus.OK);

    }

    @PutMapping("/profile/{username}")
	public ResponseEntity<User> updateUser(@RequestHeader(name = "accept-language", required=false) String language, @PathVariable("username") String username, @RequestParam(required=false) String fullName, @RequestParam(required=false) String password,
                                           @RequestParam(required=false) String email, @RequestParam(required=false) String birthday, @RequestPart(required=false) MultipartFile uploadfile) {
        if(language !=null && language.toLowerCase().equals("vi")) {
            User user =  userRepository.findByUsernameOrEmail(username, username)
                    .orElseThrow(() ->
                            new UsernameNotFoundException("Kh??ng t??m th???y ng?????i d??ng c?? t??n ho???c email: " + username));
        }

        if(language !=null && language.toLowerCase().equals("ja")) {
            User user =  userRepository.findByUsernameOrEmail(username, username)
                    .orElseThrow(() ->
                            new UsernameNotFoundException("?????????ID/?????????????????????????????????????????????????????????????????????" + username));
        }

        User user =  userRepository.findByUsernameOrEmail(username, username)
                .orElseThrow(() ->
                        new UsernameNotFoundException("User not found with username or email: " + username));

        if(StringUtils.isNotEmpty(fullName)) {
            user.setFullName(fullName);
        }
        if(StringUtils.isNotEmpty(birthday)) {
            user.setBirthday(birthday);
        }
        if(StringUtils.isNotEmpty(email)) {
            user.setEmail(email);
        }
        if(StringUtils.isNotEmpty(password)) {
            user.setPassword(passwordEncoder.encode(password));

        }
        String filename = "";
        try {
            if (uploadfile!=null && !uploadfile.isEmpty()) {
                filename = saveUploadedFiles(Arrays.asList(uploadfile));
            }
        } catch (IOException e) {
            return new ResponseEntity < > (HttpStatus.BAD_REQUEST);
        }
        if(filename != null && !filename.isEmpty()) {
            user.setAvatar(env.getProperty("imageUrl") + "avatar/" + filename);
        }
        return new ResponseEntity<>(userRepository.save(user), HttpStatus.OK);
    }

    @PutMapping("/change-password")
    public ResponseEntity<String> changePassword(@RequestHeader(name = "accept-language", required=false) String language, @RequestParam("username") String username, @RequestParam String oldPassword, @RequestParam String newPassword) {
        Optional<User> userData =  userRepository.findByUsernameOrEmail(username, username);
        if(!userData.isPresent()) {
            if(language !=null && language.toLowerCase().equals("vi")) {
                return new ResponseEntity<>("Kh??ng t??m th???y ng?????i d??ng", HttpStatus.BAD_REQUEST);
            }
            if(language !=null && language.toLowerCase().equals("ja")) {
                return new ResponseEntity<>("?????????ID/?????????????????????????????????????????????????????????????????????", HttpStatus.BAD_REQUEST);
            }
            return new ResponseEntity<>("User not found", HttpStatus.BAD_REQUEST);
        }

        User user = userData.get();

        BCryptPasswordEncoder bcrypt = new BCryptPasswordEncoder();
        boolean isPasswordMatches = bcrypt.matches(oldPassword, user.getPassword());

        if(!isPasswordMatches) {
            if(language !=null && language.toLowerCase().equals("vi")) {
                return new ResponseEntity<>("M???t kh???u c?? kh??ng ????ng", HttpStatus.BAD_REQUEST);
            }
            if(language !=null && language.toLowerCase().equals("ja")) {
                return new ResponseEntity<>("?????????????????????????????????????????????", HttpStatus.BAD_REQUEST);
            }
            return new ResponseEntity<>("Old password is incorrect???", HttpStatus.BAD_REQUEST);
        }

        if(StringUtils.isNotEmpty(newPassword)) {
            user.setPassword(passwordEncoder.encode(newPassword));

        }
        userRepository.save(user);
        return new ResponseEntity<>(newPassword, HttpStatus.OK);
    }

    @PutMapping("/forgot-password")
    public ResponseEntity<User> authenticateUser(@RequestHeader(name = "accept-language", required=false) String language, @RequestParam String email){
        Optional<User> userData =  userRepository.findByEmail(email);
        if(!userData.isPresent()) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        User user = userData.get();
        String newPassword = RandomStringUtils.randomAlphabetic(10);
        user.setPassword(passwordEncoder.encode(newPassword));
        if(language !=null && language.toLowerCase().equals("vi")) {
            emailService.sendMail(email, "M???t kh???u m???i c???a Caphephin", "M???t kh???u m???i ???????c cung c???p cho b???n l??: " + newPassword + "\n\nTr??n tr???ng c???m ??n!\nCaphephin");
        } else if(language !=null && language.toLowerCase().equals("ja")) {
            emailService.sendMail(email, "Caphephin?????????????????????", "????????????????????????????????? " + newPassword + "?????????\n\n??????????????????????????????????????????\nCaphephin");
        } else {
            emailService.sendMail(email, "New password of the Caphephin", "The new password provided to you is " + newPassword + "\n\nThanks\nCaphephin");
        }
        return new ResponseEntity<>(userRepository.save(user), HttpStatus.OK);
    }

    private String saveUploadedFiles(List< MultipartFile > files) throws IOException {
        for (MultipartFile file: files) {
            if (file.isEmpty()) {
                continue;
            }
            String fileName = new SimpleDateFormat("yyyyMMddHHmmss'.'").format(new Date()) + FilenameUtils.getExtension(file.getOriginalFilename());;
            byte[] bytes = file.getBytes();
            Path path = Paths.get("src/main/resources/static/images/avatar/" + fileName);
            Files.write(path, bytes);
            Path path2 = Paths.get("target/classes/static/images/avatar/" + fileName);
            Files.write(path2, bytes);
            return fileName;
        }
        return "";
    }
}