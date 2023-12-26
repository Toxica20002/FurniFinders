package com.furnifinders.backend.Controller;

import com.furnifinders.backend.Entity.User;
import com.furnifinders.backend.dto.Request.EditProfileRequest;
import com.furnifinders.backend.dto.Response.EditProfileResponse;
import com.furnifinders.backend.service.UserService;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3030")
public class UserController {
    private final UserService userService;

    @PutMapping("/editProfile/{id}")
    public ResponseEntity<EditProfileResponse> editProfile(@RequestBody EditProfileRequest editProfileRequest, @PathVariable("id") Long id) {
        EditProfileResponse editProfileResponse = new EditProfileResponse();
        try {
            userService.editUser(id,editProfileRequest);
            editProfileResponse.setMessage("Updated profile successfully");
        } catch (Exception e) {
            editProfileResponse.setMessage(e.getMessage());
        }
        return ResponseEntity.ok().body(editProfileResponse);
    }

    @GetMapping("/getProfile/{id}")
    public ResponseEntity<User> getProfile (@PathVariable("id") Long id) {
        User user = userService.findUserById(id);
        return ResponseEntity.ok().body(user);
    }
}