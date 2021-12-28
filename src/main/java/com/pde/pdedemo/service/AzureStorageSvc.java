package com.pde.pdedemo.service;

import java.io.IOException;

public interface AzureStorageSvc {
	public String readFromBlob(String containerName, String fileName) throws IOException;
	public String writeToBlob(String containerName, String fileName, String data) throws IOException;
    public String getSecret(String keyVaultName, String secretName);
    public void setSecret(String keyVaultName, String secretName, String secretValue);

}
