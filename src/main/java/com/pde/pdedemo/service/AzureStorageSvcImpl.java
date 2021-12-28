package com.pde.pdedemo.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.nio.charset.Charset;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.io.WritableResource;
import org.springframework.stereotype.Component;
import org.springframework.util.StreamUtils;

import com.azure.spring.autoconfigure.storage.resource.AzureStorageResourcePatternResolver;
import com.azure.storage.blob.BlobServiceClient;
import com.azure.storage.blob.BlobServiceClientBuilder;

@Component
public class AzureStorageSvcImpl implements AzureStorageSvc{
	
	@Value("${storage.connectionString}")
    private String connectionString;
	
	@Value("${azure.storage.blob-endpoint}")
    private String endpoint;
	
	@Override
	public String readFromBlob(String containerName, String fileName) throws IOException 
	{
		 String searchLocation = "azure-blob://" + containerName + "/" + fileName;
		 System.out.println(searchLocation);
//	        String connectionString = "DefaultEndpointsProtocol=https;AccountName=mystorsgeaccountpde;AccountKey=Y6mX9oa3Qa3MAnz2Q0zcG/wN8AEhZMGup5RQSCq34yZAtHgWWWYWJY/Jl9QU6xy8Xe+FWRMyXie/uTEAkKpZTA==;EndpointSuffix=core.windows.net";
//	        String endpoint = "https://mystorsgeaccountpde.blob.core.windows.net/";		
		BlobServiceClient client = new BlobServiceClientBuilder().connectionString(connectionString).endpoint(endpoint).buildClient();
        AzureStorageResourcePatternResolver storageResourcePatternResolver = new AzureStorageResourcePatternResolver(client);

        Resource resource = storageResourcePatternResolver.getResource(searchLocation);

			return StreamUtils.copyToString(
					resource.getInputStream(),
			        Charset.defaultCharset());

	}

	@Override
	public String writeToBlob(String containerName, String fileName, String data) throws IOException {
		
		String searchLocation = "azure-blob://" + containerName + "/" + fileName;
		
		BlobServiceClient client = new BlobServiceClientBuilder().connectionString(connectionString).endpoint(endpoint).buildClient();
		AzureStorageResourcePatternResolver storageResourcePatternResolver = new AzureStorageResourcePatternResolver(client);

		Resource resource = storageResourcePatternResolver.getResource(searchLocation);
		
		String  readFromFile = StreamUtils.copyToString(
				resource.getInputStream(),
                Charset.defaultCharset());
    	
    	String dataFinal=readFromFile +  System.lineSeparator() + data; 

       try (OutputStream os = ((WritableResource) resource).getOutputStream()) {
    	   
            os.write(dataFinal.getBytes());
        }
        return "file was updated";
	}

	@Override
	public String getSecret(String keyVaultName, String secretName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setSecret(String keyVaultName, String secretName, String secretValue) {
		// TODO Auto-generated method stub
		
	}

}
