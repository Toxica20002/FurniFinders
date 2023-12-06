package com.furnifinders.backend.rest;

import com.furnifinders.backend.Entity.User;
import com.furnifinders.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserController {
        private final UserService userService;

        @Autowired
        public UserController(UserService userService) {
            this.userService = userService;
        }

        @GetMapping("/all")
        public ResponseEntity<List<User>> getAllUser(){
            List<User> users = userService.getAllUser();
            return new ResponseEntity<>(users, HttpStatus.OK);
        }

        @GetMapping("/id/{id}")
        public ResponseEntity<User> getUserById(@PathVariable("id") int id){
            User user = userService.getUserByID(id);
            return new ResponseEntity<>(user, HttpStatus.OK);
        }

        @GetMapping("/email/{email}")
        public ResponseEntity<User> getUserByEmail(@PathVariable("email") String email){
            User user = userService.getUserByEmail(email);
            return new ResponseEntity<>(user, HttpStatus.OK);
        }

        @GetMapping("/phone/{phone}")
        public ResponseEntity<User> getUserByPhone(@PathVariable("phone") String phone){
            User user = userService.getUserByPhone(phone);
            return new ResponseEntity<>(user, HttpStatus.OK);
        }

        @GetMapping("/role/{role}")
        public ResponseEntity<List<User>> getAllUserByRole(@PathVariable("role") String role){
            List<User> users = userService.getAllUserByRole(role);
            return new ResponseEntity<>(users, HttpStatus.OK);
        }

        @PutMapping("/update")
        public ResponseEntity<User> updateUser(@RequestBody User user){
            userService.updateUser(user);
            return new ResponseEntity<>(user, HttpStatus.OK);
        }

        @DeleteMapping("/delete/email/{email}")
        public ResponseEntity<?> deleteUserByEmail(@PathVariable("email") String email){
            userService.deleteUserByEmail(email);
            return new ResponseEntity<>(HttpStatus.OK);
        }

        @PostMapping("/add")
        public ResponseEntity<User> addUser(@RequestBody User user){
            user.setId(0);
            user.setCreate_date(new Timestamp(System.currentTimeMillis()));
            User newUser = userService.addUser(user);
            return ResponseEntity.status(HttpStatus.CREATED).body(newUser);
        }




}