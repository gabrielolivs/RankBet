package br.com.rankbet.client;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Scanner;

import org.apache.http.HttpResponse;
import org.apache.log4j.Logger;
import org.codehaus.jackson.map.ObjectMapper;

import br.com.taticasolutions.domain.PoCElements;
import br.com.taticasolutions.exceptions.BusinessException;
import br.com.taticasolutions.utils.ProvisioningAuditory;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PoCThreadHttpCall implements Runnable {

	private long id;
	private PoCElements pocElements;
	private String responseString;

	private Logger logger = Logger.getLogger(PoCThreadHttpCall.class);

	public PoCThreadHttpCall(long id) {
		this.id = id;
	}

	public PoCThreadHttpCall(long id, PoCElements pocElements) {
		this.id = id;
		this.pocElements = pocElements;
	}

	public void run() {

		try {

			String endpoint = "http://localhost:9090/smart/start";

			ObjectMapper mapper = new ObjectMapper();
			String jsonBody = mapper.writeValueAsString(this.pocElements);

			HttpCallActions.POST(endpoint, jsonBody, HttpCallActions.getSSLCustomClient());
			HttpResponse response = HttpCallActions.getResponse();

			logger.info("response.getEntity().getContent(): " + response.getEntity().getContent());

			try {
				Scanner sc = new Scanner(response.getEntity().getContent());
				while (sc.hasNext()) {
					String result = sc.next();
					ProvisioningAuditory.insert(id, "EXEC01", "Executando: " + result, "INFO", "N0");
				}

			} catch (Exception e) {
				logger.error(e);
				ProvisioningAuditory.insert(id, "0010", "Execução Falhou! " + e.getMessage(), "ERROR", "N2");
				throw new BusinessException("Execução Falhou!");
			}

		} catch (Exception e) {
			try {
				ProvisioningAuditory.insert(id, "0011", "Execução Falhou! " + e.getMessage(), "ERROR", "N2");
			} catch (Exception e1) {
				logger.error(e1);
			}
			//throw new BusinessException("Execução Falhou!");
			logger.error(e);
		}

		try {
			ProvisioningAuditory.insert(id, "9999", "Programa Finalizado!", "EXIT", "N2");
		} catch (SQLException | IOException e) {
			logger.error(e);
			//throw new BusinessException("Execução Falhou!");
		}

	}

}