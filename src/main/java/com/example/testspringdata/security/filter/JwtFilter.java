package com.example.testspringdata.security.filter;

import com.example.testspringdata.customService.CustomUserDetailsService;
import com.example.testspringdata.message.AnswerMessage;
import com.example.testspringdata.security.jwtProvider.JwtProvider;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

@Component
@AllArgsConstructor
public class JwtFilter extends GenericFilterBean {

    private JwtProvider jwtProvider;
    private CustomUserDetailsService userDetailsService;
    private ObjectMapper objectMapper;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException {
        try {
            Optional<String> token = jwtProvider.resolveToken((HttpServletRequest) request);
            if (token.isPresent() && jwtProvider.validateToken(token.get())) {
                UserDetails userDetails = userDetailsService.loadUserByUsername(jwtProvider.getLoginFromToken(token.get()));
                Authentication authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
            chain.doFilter(request, response);
        } catch (Exception e) {
            AnswerMessage answerMessage = new AnswerMessage();
            answerMessage.setStatus(HttpStatus.FORBIDDEN.toString());
            answerMessage.setMessage("Access is forbidden");
            ((HttpServletResponse) response).setStatus(HttpStatus.FORBIDDEN.value());
            response.setContentType("application/json");
            response.getWriter().write(objectMapper.writeValueAsString(answerMessage));
        }
    }

}
