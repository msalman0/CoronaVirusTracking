/**
 *
 */
package com.voda.coronaVirus.service;

import java.io.IOException;
import java.io.StringReader;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.voda.coronaVirus.model.CoronaState;


/**
 *
 */
@Service

public class CornoaVirusData
{
	private static String url = "https://raw.githubusercontent.com/CSSEGISandData/COVID-19/master/csse_covid_19_data/csse_covid_19_time_series/time_series_covid19_confirmed_global.csv";
	private List<CoronaState> coronateStateList = new ArrayList<CoronaState>();

	@PostConstruct
	@Scheduled(cron = "* * 1 * * *")
	public void fetchVirusData() throws IOException, InterruptedException
	{
		final HttpClient client = HttpClient.newHttpClient();
		final HttpRequest request = HttpRequest.newBuilder().uri(URI.create(url)).build();

		final HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
		final StringReader csvReader = new StringReader(response.body());
		convertCSVResponseToObject(csvReader);

	}

	private void convertCSVResponseToObject(final StringReader csvReader) throws IOException
	{
		// TODO Auto-generated method stub
		final List<CoronaState> newCoronateStateList = new ArrayList<CoronaState>();
		final Iterable<CSVRecord> records = CSVFormat.DEFAULT.withFirstRecordAsHeader().parse(csvReader);
		for (final CSVRecord record : records)
		{
			final CoronaState newCoronaState = new CoronaState();
			newCoronaState.setCountry(record.get("Country/Region"));
			newCoronaState.setState(record.get("Province/State"));
			newCoronaState.setCount(Integer.parseInt(record.get(record.size() - 1)));
			newCoronateStateList.add(newCoronaState);
		}

		coronateStateList = newCoronateStateList;

	}

	/**
	 * @return the coronateStateList
	 */
	public List<CoronaState> getCoronateStateList()
	{
		return coronateStateList;
	}

	/**
	 * @param coronateStateList
	 *           the coronateStateList to set
	 */
	public void setCoronateStateList(final List<CoronaState> coronateStateList)
	{
		this.coronateStateList = coronateStateList;
	}

}
