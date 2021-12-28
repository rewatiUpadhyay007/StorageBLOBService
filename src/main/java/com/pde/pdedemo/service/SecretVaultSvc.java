package com.pde.pdedemo.service;

import com.pde.pdedemo.model.KeySecretModel;

public interface SecretVaultSvc {
	public String createSecret( KeySecretModel keySecretModel );
	public String getSecret(KeySecretModel keySecretModel) ;
	}
