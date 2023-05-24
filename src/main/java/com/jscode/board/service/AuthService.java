package com.jscode.board.service;

import com.jscode.board.domain.Member;
import com.jscode.board.domain.RefreshToken;
import com.jscode.board.dto.jwt.TokenDto;
import com.jscode.board.dto.jwt.TokenRequestDto;
import com.jscode.board.dto.member.MemberFormRequest;
import com.jscode.board.exception.jwt.ExpiredJwtTokenException;
import com.jscode.board.exception.jwt.InvalidJwtTokenException;
import com.jscode.board.exception.member.DuplicateEmailException;
import com.jscode.board.jwt.TokenProvider;
import com.jscode.board.repository.MemberRepository;
import com.jscode.board.repository.RefreshTokenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@RequiredArgsConstructor
@Transactional
@Service
public class AuthService {

    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final PasswordEncoder passwordEncoder;
    private final TokenProvider tokenProvider;
    private final MemberRepository memberRepository;
    private final RefreshTokenRepository refreshTokenRepository;

    public Long join(MemberFormRequest request){
        if(isEmailDuplicate(request.getEmail())){
            throw new DuplicateEmailException();
        }
        Member savedMember = memberRepository.save(request.toEntity(passwordEncoder));
        return savedMember.getId();
    }

    public TokenDto login(MemberFormRequest request){
        UsernamePasswordAuthenticationToken authenticationToken = request.toAuthentication();

        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);

        TokenDto tokenDto = tokenProvider.createToken(authentication);

        RefreshToken refreshToken = RefreshToken.builder()
                .key(authentication.getName())
                .value(tokenDto.getRefreshToken())
                .build();

        refreshTokenRepository.save(refreshToken);

        return tokenDto;
    }

    public TokenDto reissue(TokenRequestDto tokenRequestDto){
        if(!tokenProvider.validateToken(tokenRequestDto.getRefreshToken())){
            throw new InvalidJwtTokenException();
        }

        Authentication authentication = tokenProvider.getAuthentication(tokenRequestDto.getAccessToken());

        RefreshToken refreshToken = refreshTokenRepository.findByKey(authentication.getName())
                .orElseThrow(ExpiredJwtTokenException::new);

        if(!refreshToken.getValue().equals(tokenRequestDto.getRefreshToken())){
            throw new InvalidJwtTokenException();
        }

        TokenDto tokenDto = tokenProvider.createToken(authentication);

        refreshToken.updateValue(tokenDto.getRefreshToken());

        return tokenDto;
    }

    private boolean isEmailDuplicate(String email){
        Optional<Member> findMember = memberRepository.findByEmail(email);
        return findMember.isPresent();
    }
}