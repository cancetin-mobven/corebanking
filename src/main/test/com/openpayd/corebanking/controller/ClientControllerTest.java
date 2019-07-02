package com.openpayd.corebanking.controller;

import com.openpayd.corebanking.AppConfig;
import com.openpayd.corebanking.controller.ClientController;
import com.openpayd.corebanking.entity.Address;
import com.openpayd.corebanking.entity.Client;
import com.openpayd.corebanking.entity.dto.ClientDTO;
import com.openpayd.corebanking.service.IClientService;
import com.openpayd.corebanking.service.impl.ClientServiceImpl;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.mockito.internal.verification.Times;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

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
public class ClientControllerTest {

    @InjectMocks
    private ClientController clientController;

    @Mock
    IClientService clientService;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @org.junit.Test
    public void shouldListClients() {
        List<Client> clients = new ArrayList<Client>();
        HttpServletRequest  mockedRequest = mock(HttpServletRequest.class);
        clientController.listClient(mockedRequest);
        verify(clientService, Mockito.times(1)).listClients();
    }

    @org.junit.Test
    public void shouldGetClient() {
        List<Client> clients = new ArrayList<Client>();
        HttpServletRequest  mockedRequest = mock(HttpServletRequest.class);
        Long id = 0L;
        clientController.listClientById(id,mockedRequest);
        verify(clientService, Mockito.times(1)).getClient(id);
    }
    /*
    @RequestMapping(value = "/saveClient", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<ClientDTO> saveClient(@RequestBody ClientDTO clientDTO, HttpServletRequest request){
        ClientDTO cto = null;
        System.out.println(clientDTO.toString());
        try{
            cto = clientService.saveClient(clientDTO);
        }catch (Exception e){

        }
        return new ResponseEntity<>(cto ,HttpStatus.CREATED);
    }
    */
    @org.junit.Test
    public void shouldSaveClient() {
        ClientDTO clientDTO = new ClientDTO();
        clientDTO.setId(1);
        clientDTO.setName("name");

        Address primAddress = new Address();
        primAddress.setAddressLine1("add line 1 ");
        primAddress.setAddressLine2("add line 2 ");
        primAddress.setCity(" city 1");
        primAddress.setCountry(" country 1 ");

        Address secondryAddress = new Address();
        secondryAddress.setAddressLine1("add line 1 ");
        secondryAddress.setAddressLine2("add line 2 ");
        secondryAddress.setCity(" city 1");
        secondryAddress.setCountry(" country 1 ");

        clientDTO.setPrimaryAddress(primAddress);
        clientDTO.setSecondaryAddress(secondryAddress);

        HttpServletRequest  mockedRequest = Mockito.mock(HttpServletRequest.class);

        clientController.saveClient(clientDTO,mockedRequest);

//        verify(clientService.saveClient(any(Object.class), Mockito.times(1)));
        verify(clientService, Mockito.times(1)).saveClient(Matchers.<ClientDTO>any());
    }



}