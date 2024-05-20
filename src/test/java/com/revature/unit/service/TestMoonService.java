package com.revature.unit.service;

import com.revature.repository.MoonDao;
import com.revature.service.MoonService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class TestMoonService {
    @Mock
    public MoonDao moonDao;
    @InjectMocks
    public MoonService moonService;

    @Test
    public void testCreateMoonPositive(){

    }
}
