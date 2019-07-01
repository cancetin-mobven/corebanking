package com.openpayd.corebanking.entity.dto;

import com.openpayd.corebanking.entity.Address;
import com.openpayd.corebanking.entity.Client;

import java.util.ArrayList;
import java.util.List;

public class ClientDTO {

    public long id;

    public String name;

    public Address primaryAddress;

    public Address secondaryAddress;

    private List<AccountDTO> clientAccounts = new ArrayList<AccountDTO>();

    public ClientDTO() {

    }

    public ClientDTO(Client client) {
        System.out.println(client.toString());

        this.name = client.getName();
        this.id = client.getId();
        this.primaryAddress = client.getPrimaryAddress();
        this.secondaryAddress = client.getSecondaryAddress();

/**
      for(Account account : client.getClientAccounts()){
          this.clientAccounts.add(new AccountDTO(account));
      }
*/
        //this.clientAccounts.add(new AccountDTO())
      /*  client.getClientAccounts().stream().map(
         clients -> this.clientAccounts.add(clients);
        )
    */
    }

    public ClientDTO(String name, Address primaryAddress, Address secondaryAddress ) {
        this.name = name;
        this.primaryAddress = primaryAddress;
        this.secondaryAddress = secondaryAddress;
    }


    public ClientDTO(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }


    public Address getPrimaryAddress() {
        return primaryAddress;
    }

    public void setPrimaryAddress(Address primaryAddress) {
        this.primaryAddress = primaryAddress;
    }

    public Address getSecondaryAddress() {
        return secondaryAddress;
    }

    public void setSecondaryAddress(Address secondaryAddress) {
        this.secondaryAddress = secondaryAddress;
    }

    @Override
    public String toString() {
        return "ClientDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", primaryAddress=" + primaryAddress +
                ", secondaryAddress=" + secondaryAddress +
                ", clientAccounts=" + clientAccounts +
                '}';
    }
}
