package com.openpayd.corebanking.controller;

import com.openpayd.corebanking.AppConfig;
import com.openpayd.corebanking.controller.ClientController;
import com.openpayd.corebanking.entity.Address;
import com.openpayd.corebanking.entity.Client;
import com.openpayd.corebanking.entity.dto.ClientDTO;
import com.openpayd.corebanking.service.IAccountService;
import com.openpayd.corebanking.service.IClientService;
import com.openpayd.corebanking.service.impl.ClientServiceImpl;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.mockito.internal.verification.Times;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.web.bind.annotation.PathVariable;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.servlet.http.HttpServletRequest;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.any;
import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {AppConfig.class})
@WebAppConfiguration
public class AccountControllerTest {

    @InjectMocks
    private AccountController accountController;

    @Mock
    IClientService clientService;

    @Mock
    IAccountService accountService;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @org.junit.Test
    public void shouldListAccountByClient() {
        List<Client> clients = new ArrayList<Client>();
        HttpServletRequest  mockedRequest = mock(HttpServletRequest.class);
        accountController.listAccountByClient(1L,mockedRequest);
        verify(clientService, Mockito.times(1)).findClientById(1L);
    }

}
