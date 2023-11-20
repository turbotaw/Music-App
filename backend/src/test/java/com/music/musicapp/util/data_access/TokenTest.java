package com.music.musicapp.util.data_access;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Query;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class TokenTest {

    @Mock
    private EntityManagerFactory entityManagerFactory;

    @Mock
    private EntityManager entityManager;

    @Mock
    private EntityTransaction transaction;

    @Mock
    private Query query;

    private Token token;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        // Mocking JPA EntityManager and transaction
        when(entityManagerFactory.createEntityManager()).thenReturn(entityManager);
        when(entityManager.getTransaction()).thenReturn(transaction);
        when(entityManager.createQuery(anyString())).thenReturn(query);

        token = new Token();
        token.setUserId(123L);
        token.setAccessToken("accessToken123");
        token.setAuthToken("authToken123");
        token.setRefreshToken("refreshToken123");
    }

    @AfterEach
    void tearDown() {
        token = null;
    }

    @Test
    void testGetUserId() {
        assertEquals(123L, token.getUserId());
    }

    @Test
    void testSetUserId() {
        token.setUserId(456L);
        assertEquals(456L, token.getUserId());
    }

    @Test
    void testGetAccessToken() {
        assertEquals("accessToken123", token.getAccessToken());
    }

    @Test
    void testSetAccessToken() {
        token.setAccessToken("newAccessToken");
        assertEquals("newAccessToken", token.getAccessToken());
    }

    @Test
    void testGetAuthToken() {
        assertEquals("authToken123", token.getAuthToken());
    }

    @Test
    void testSetAuthToken() {
        token.setAuthToken("newAuthToken");
        assertEquals("newAuthToken", token.getAuthToken());
    }

    @Test
    void testGetRefreshToken() {
        assertEquals("refreshToken123", token.getRefreshToken());
    }

    @Test
    void testSetRefreshToken() {
        token.setRefreshToken("newRefreshToken");
        assertEquals("newRefreshToken", token.getRefreshToken());
    }

    @Test
    void testGetAppUser() {
        AppUser appUser = new AppUser();
        appUser.setUserId(123L);
        token.setAppUser(appUser);
        assertEquals(appUser, token.getAppUser());
    }

    @Test
    void testSetAppUser() {
        AppUser appUser = new AppUser();
        appUser.setUserId(456L);
        token.setAppUser(appUser);
        assertEquals(appUser, token.getAppUser());
    }
}
