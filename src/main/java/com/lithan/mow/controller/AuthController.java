package com.lithan.mow.controller;

import com.lithan.mow.constraint.EGender;
import com.lithan.mow.constraint.ERole;
import com.lithan.mow.entity.Customer;
import com.lithan.mow.entity.Partner;
import com.lithan.mow.exception.UserNotActiveException;
import com.lithan.mow.payload.request.LoginRequest;
import com.lithan.mow.payload.response.AuthResponse;
import com.lithan.mow.security.jwt.JwtUtil;
import com.lithan.mow.service.CustomerService;
import com.lithan.mow.service.PartnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/auth/")

public class AuthController {

    @Autowired
    CustomerService customerService;

    @Autowired
    PartnerService partnerService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    JwtUtil jwtUtil;

    @PostMapping("register")
    public ResponseEntity<?> register(@RequestParam("name") String name,
                                      @RequestParam("address") String address, @RequestParam("gender") String gender, @RequestParam("role") String role,
                                      @RequestParam("email") String email, @RequestParam("password") String password,
                                      @RequestParam("file") MultipartFile file, @RequestParam("image") MultipartFile image,@RequestParam("distance")double distance){
        if(customerService.isCustomerExist(email)){
            return ResponseEntity.badRequest().body("Email already exists, please use different email");
        }
        if (file.getSize() > 304857 || image.getSize() > 304857) {
            return ResponseEntity.badRequest().body("File size exceeds the allowed limit. File must be under 300 KB.");
        }



        byte[] qualification;
        byte[] photo;
        Customer customer = new Customer();
        customer.setName(name);
        customer.setEmail(email);
        customer.setAddress(address);
        customer.setGender(EGender.valueOf(gender));
        customer.setRole(ERole.valueOf(role));
        customer.setPassword(password);
        customer.setDistance(Double.valueOf(distance));
         try {
            qualification = file.getBytes();
            photo = image.getBytes();
            customer.setQualification(qualification);
            customer.setProfilePicture(photo);
            Customer newUser =  customerService.register(customer);
             if (newUser != null) {
                 return ResponseEntity.ok("Registration successful.");
             } else {
                 return ResponseEntity.status(500).body("Failed to register user.");
             }
         }catch (IOException e){
             e.printStackTrace();
             return ResponseEntity.badRequest().body("Something wrong with the file or photo");
         }

    }

    @PostMapping("login")
    public ResponseEntity<?> handleLogin(@RequestBody LoginRequest request) {
        System.out.println("in controller: " + request.getEmail());
        System.out.println("in password: " + request.getPassword());

        try {
            final Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));

            SecurityContextHolder.getContext().setAuthentication(authentication);
            UserDetails user = (UserDetails) authentication.getPrincipal();
            Set<String> roles = user.getAuthorities().stream()
                    .map(GrantedAuthority::getAuthority)
                    .collect(Collectors.toSet());
            boolean isActive;
            if (roles.contains("ROLE_PARTNER")) {
                isActive = partnerService.isActive(user.getUsername());
            } else {
              isActive = customerService.isActive(user.getUsername());
            }
            if (!isActive){
                System.out.println("Your account is not active yet");
                return ResponseEntity.status(401)
                        .body("Your account is not active yet, please wait for admin to review and activate your account");
            }
            String jwtToken = jwtUtil.generateToken(user.getUsername());
            AuthResponse res = new AuthResponse();
            res.setEmail(user.getUsername());
            res.setAccessToken(jwtToken);

            res.setRole(roles);

            return ResponseEntity.ok(res);
        } catch (UserNotActiveException e) {
            // Authentication failed
            System.out.println(e);
            return ResponseEntity.status(401)
                    .body("Your account is not active yet, please wait for admin to review and activate your account");
        }
        catch (BadCredentialsException e){
        System.out.println(e);
        return ResponseEntity.status(401)
                .body("Authentication failed. Invalid username or password.");
        }
        catch (Exception e){
            System.out.println(e);
            return ResponseEntity.status(500)
                    .body("Internal server error");
        }
    }


}
