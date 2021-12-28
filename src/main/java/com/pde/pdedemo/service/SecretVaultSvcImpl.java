package com.pde.pdedemo.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.azure.identity.DefaultAzureCredential;
import com.azure.identity.DefaultAzureCredentialBuilder;
import com.azure.security.keyvault.secrets.SecretClient;
import com.azure.security.keyvault.secrets.SecretClientBuilder;
import com.azure.security.keyvault.secrets.models.KeyVaultSecret;
import com.pde.pdedemo.model.KeySecretModel;

@Service
public class SecretVaultSvcImpl implements SecretVaultSvc {
	
	 private SecretClient secretClient;
	 
	@Value("${azure.keyvault.client-id}")
	private String clientId;


	private void setupSecretClient(String vaultName) {
    	
		String keyVaultUrl = "https://" + vaultName + ".vault.azure.net/";
        DefaultAzureCredential defaultCredential = new DefaultAzureCredentialBuilder()
                .managedIdentityClientId(clientId)
                .build();

        secretClient = new SecretClientBuilder()
                .vaultUrl(keyVaultUrl)
                .credential(defaultCredential)
                .buildClient();
    }

	public String createSecret(KeySecretModel keySecretModel ) {
		
		 try {
			 	setupSecretClient(keySecretModel.getKeyVaultName());
			 
	            KeyVaultSecret secret = secretClient.setSecret(keySecretModel.getSecretName(), keySecretModel.getSecretValue());
	            return String.format("Successfully set secret in Key Vault", keySecretModel.getSecretName());
	            
	        } catch (Exception ex) {
	            return String.format("Failed to set secret in Key Vault  due to ", keySecretModel.getSecretName(),
	            		keySecretModel.getKeyVaultName(), ex.getMessage());
	        }
		
	}
	
	public String getSecret(KeySecretModel keySecretModel ) {
		
		 try {
			 	setupSecretClient(keySecretModel.getKeyVaultName());
			 
			 	 KeyVaultSecret secret = secretClient.getSecret(keySecretModel.getSecretName());
			 	 
		            return "Successfully got the value of secret is " + secret.getValue();
		            
	        } catch (Exception ex) {
	            return String.format("Failed to set secret in Key Vault  due to ", keySecretModel.getSecretName(),
	            		keySecretModel.getKeyVaultName(), ex.getMessage());
	        }
		
	}

}
