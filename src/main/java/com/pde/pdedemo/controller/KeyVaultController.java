package com.pde.pdedemo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.pde.pdedemo.model.KeySecretModel;
import com.pde.pdedemo.service.SecretVaultSvc;

@RestController
public class KeyVaultController {
	
	
	@Autowired
	private SecretVaultSvc secretVaultSvc;
	
	
	@PutMapping("/secrets/{vaultName}")
    public String setSecret(@PathVariable String vaultName, @RequestBody KeySecretModel keySecretModel) {
		 KeySecretModel setKeySecretModel = new KeySecretModel();
		 setKeySecretModel.setKeyVaultName(vaultName);
		 setKeySecretModel.setSecretName(keySecretModel.getSecretName());
		 setKeySecretModel.setSecretValue(keySecretModel.getSecretValue());
        
		return secretVaultSvc.createSecret(setKeySecretModel);
    }
	
	@GetMapping("/getSecret/{vaultName}/{secretName}")
	public String getSecret(@PathVariable String vaultName, @PathVariable String secretName) {
		 KeySecretModel setKeySecretModel = new KeySecretModel();
		 setKeySecretModel.setKeyVaultName(vaultName);
		 setKeySecretModel.setSecretName(secretName);
		 
		 return secretVaultSvc.getSecret(setKeySecretModel);
		 
	    	 
	    }
}
