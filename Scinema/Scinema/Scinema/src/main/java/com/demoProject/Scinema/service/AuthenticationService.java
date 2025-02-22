package com.demoProject.Scinema.service;


import com.demoProject.Scinema.dto.reponse.AuthenticationResponse;
import com.demoProject.Scinema.dto.reponse.IntrospectResponse;
import com.demoProject.Scinema.dto.request.AuthenticationRequest;
import com.demoProject.Scinema.dto.request.IntrospectRequest;
import com.demoProject.Scinema.entity.User;
import com.demoProject.Scinema.exception.AppException;
import com.demoProject.Scinema.exception.ErrorCode;
import com.demoProject.Scinema.repository.UserRepository;
import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jose.crypto.MACVerifier;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.experimental.NonFinal;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.text.ParseException;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.StringJoiner;

@Slf4j
@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AuthenticationService
{
    UserRepository userRepository;

    @NonFinal
    @Value("${signer.key}")
    protected String SIGNER_KEY;

    public IntrospectResponse introspect(IntrospectRequest request) throws JOSEException, ParseException {
        var token = request.getToken();

        JWSVerifier verifier = new MACVerifier(SIGNER_KEY.getBytes());

        SignedJWT signedJWT = SignedJWT.parse(token);

        Date expiryTime = signedJWT.getJWTClaimsSet().getExpirationTime();

        var verified =  signedJWT.verify(verifier);

        return IntrospectResponse.builder()
                .valid(verified && expiryTime.after(new Date()))
                .build()
                ;
    }

    public AuthenticationResponse authenticated(AuthenticationRequest request)
    {
        var user = userRepository.findByUsername(request.getUsername())
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND));

        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(12); // tăng độ bảo mật
        boolean authenticated = passwordEncoder.matches(request.getPassword(),
                user.getPassword());

        if (!authenticated)
        {
            throw new AppException(ErrorCode.UNAUTHENTICATED);
        }

        var token = generateToken(user);  // Sửa lại từ request.getUsername() thành user

        return AuthenticationResponse.builder()
                .token(token)
                .isAuthenticated(true)
                .build();
    }

    private String generateToken(User user)
    {
        JWSHeader header = new JWSHeader(JWSAlgorithm.HS512);

        JWTClaimsSet jwtClaimsSet = new JWTClaimsSet.Builder()
                .subject(user.getUsername())
                .issuer("SCINEMA")
                .issueTime(new Date())
                .expirationTime(new Date(
                        Instant.now().plus(1, ChronoUnit.HOURS).toEpochMilli()
                ))
                .claim("scope", buildScope(user))  // dùng buildScope(user) thay vì username
                .build();
        Payload payload = new Payload(jwtClaimsSet.toJSONObject());

        JWSObject jwsObject = new JWSObject(header, payload);

        try {
            jwsObject.sign(new MACSigner(SIGNER_KEY.getBytes()));
            return jwsObject.serialize();
        } catch (JOSEException e) {
            log.error("Can not generate token", e);
            throw new RuntimeException(e);
        }
    }

    private String buildScope(User user)
    {
        StringJoiner stringJoiner = new StringJoiner(" ");

        if (!CollectionUtils.isEmpty(user.getRoles()))
        {
            user.getRoles().forEach(role -> {
                stringJoiner.add("ROLE_" + role.getRole_name());
                if (!CollectionUtils.isEmpty(role.getPermissions()))
                    role.getPermissions()
                            .forEach(permission -> stringJoiner.add(permission.getPermission_name()));
            });
        }
        return stringJoiner.toString();
    }
}
