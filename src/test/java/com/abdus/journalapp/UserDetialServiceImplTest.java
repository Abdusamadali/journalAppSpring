//package com.abdus.journalapp;
//
//import com.abdus.journalapp.entity.User;
//import com.abdus.journalapp.repository.UserRepository;
//import com.abdus.journalapp.service.UserDetailServiceImp;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//
//import static org.junit.jupiter.api.Assertions.assertNotNull;
//import static org.mockito.Mockito.mock;
//import static org.mockito.Mockito.when;
//
//public class UserDetialServiceImplTest {
//
//    private UserRepository userRepository;
//    private UserDetailsService userDetailsService;
//
//    @BeforeEach
//    public void setUp() {
//        userRepository = mock(UserRepository.class);
//        userDetailsService = new UserDetailServiceImp(userRepository); // Your custom service
//    }
//
//    @Test
//    public void test() {
//        User mockUser = new User();
//        mockUser.setUserName("abdus");
//        mockUser.setPassword("123");
//
//        when(userRepository.findByUserName("abdus")).thenReturn(mockUser);
//
//        UserDetails userDetails = userDetailsService.loadUserByUsername("abdus");
//
//        assertNotNull(userDetails);
//    }
//}
