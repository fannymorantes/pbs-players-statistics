package com.test.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.test.classes.Player;

import org.json.JSONException;
import org.json.JSONObject;

@Controller
public class HomeController {
	private ArrayList<Player> records;
	
	//Public API URL 
	String urlPlayers = "http://playersapi.eu-central-1.elasticbeanstalk.com/api/players/";
	
	//+++++++++++++++++++++Returns Players small list as a ModelAndView.+++++++++++++++++++++++++++
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView getPlayers(HttpServletRequest request, HttpServletResponse response) throws IOException, JSONException{
		records = new ArrayList<Player>();
		Player record= null;
		ModelAndView mv = new ModelAndView("index");
		DecimalFormat df = new DecimalFormat("#.00");
		String jSON = sendGET(urlPlayers);
		
		
		try {
			JSONObject jsonObj = new JSONObject(jSON);
			long numberOfPlayers = jsonObj.getJSONObject("map").getLong("numberOfPlayers");
			for (int i = 0; i < numberOfPlayers; i++) {
				long ranking = jsonObj.getJSONObject("map").getJSONObject("results").getJSONArray("myArrayList").getJSONObject(i).getJSONObject("map").getLong("ranking");
				long goal = jsonObj.getJSONObject("map").getJSONObject("results").getJSONArray("myArrayList").getJSONObject(i).getJSONObject("map").getLong("goal");
				String tournamentRegionCode = jsonObj.getJSONObject("map").getJSONObject("results").getJSONArray("myArrayList").getJSONObject(i).getJSONObject("map").getString("tournamentRegionCode");
				String regionCode = jsonObj.getJSONObject("map").getJSONObject("results").getJSONArray("myArrayList").getJSONObject(i).getJSONObject("map").getString("regionCode");
				String tournamentName = jsonObj.getJSONObject("map").getJSONObject("results").getJSONArray("myArrayList").getJSONObject(i).getJSONObject("map").getString("tournamentName");
				String teamName = jsonObj.getJSONObject("map").getJSONObject("results").getJSONArray("myArrayList").getJSONObject(i).getJSONObject("map").getString("teamName");
				String name = jsonObj.getJSONObject("map").getJSONObject("results").getJSONArray("myArrayList").getJSONObject(i).getJSONObject("map").getString("name");
				long yellowCard = jsonObj.getJSONObject("map").getJSONObject("results").getJSONArray("myArrayList").getJSONObject(i).getJSONObject("map").getLong("yellowCard");
				long redCard = jsonObj.getJSONObject("map").getJSONObject("results").getJSONArray("myArrayList").getJSONObject(i).getJSONObject("map").getLong("redCard");
				String shotsPerGame = jsonObj.getJSONObject("map").getJSONObject("results").getJSONArray("myArrayList").getJSONObject(i).getJSONObject("map").getString("shotsPerGame");
				String rating = jsonObj.getJSONObject("map").getJSONObject("results").getJSONArray("myArrayList").getJSONObject(i).getJSONObject("map").getString("rating");
				float shotsPerGameFloat = Float.parseFloat(df.format(Float.parseFloat(rating)));
				float ratingFloat = Float.parseFloat(df.format(Float.parseFloat(shotsPerGame)));
				
				record = new  Player(ranking, goal, yellowCard, redCard, tournamentRegionCode, regionCode, tournamentName, teamName, name, shotsPerGameFloat, ratingFloat);
				records.add(record);
	
			}
			mv.addObject("records", records);
			mv.addObject("error", false);
			
		} catch (Exception e) {
			System.out.println("error accessing to the API");
			mv.addObject("error", true);
		}
			
		 
		return mv;
	}
	
	//+++++++++++++++++++++Returns a Json with all information of the selected player.+++++++++++++++++++++++++++
	@RequestMapping(value = "/getPlayerSelected", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public String getPlayerSelected(HttpServletRequest request, HttpServletResponse response, int id) throws IOException, JSONException{
		//Public API /{i} 
		String urlPlayerDetailis = urlPlayers+id;
		String jSON = sendGET(urlPlayerDetailis);
		String result = null;
		try {
			JSONObject jsonObj = new JSONObject(jSON);
			JsonObject jsonObj2 = new JsonObject(); //Google
			long numberOfProperties = jsonObj.getJSONObject("map").getLong("numberOfProperties");
			for (int i = 0; i < numberOfProperties; i++) {
				jsonObj2.addProperty(jsonObj.getJSONObject("map").getJSONObject("properties").getJSONArray("myArrayList").getJSONObject(0).getJSONObject("map").getString("p"+i), jsonObj.getJSONObject("map").getJSONObject("results").getJSONArray("myArrayList").getJSONObject(0).getJSONObject("map").getString(jsonObj.getJSONObject("map").getJSONObject("properties").getJSONArray("myArrayList").getJSONObject(0).getJSONObject("map").getString("p"+i)));
			}
			
			Gson gson = new Gson();
	    	result = gson.toJson(jsonObj2);
	    	System.out.println("Result: "+ result);
		} catch (Exception e) {
			System.out.println(e);
			result = "error";
		}
			
    	
		return result;
	}
	
	//+++++++++++++++++++++Returns the information from the API.+++++++++++++++++++++++++++
	private static String sendGET(String url) throws IOException {
		URL obj = new URL(url);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();
		con.setRequestMethod("GET");
		StringBuffer response = new StringBuffer();
		int responseCode = 0;
		try {
			responseCode = con.getResponseCode();
			BufferedReader in = new BufferedReader(new InputStreamReader(
					con.getInputStream()));
			String inputLine;
			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			in.close();
			// print result
			System.out.println(response.toString());
		} catch (Exception e) {
			System.out.println("GET Response Code :: " + responseCode);
			System.out.println("Error: " + e);
		}
		return response.toString();

	}
	
	//+++++++++++++++++++++Returns a JSON with information to use on the chart.+++++++++++++++++++++++++++
	@RequestMapping(value = "/getGoalsChart", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public String getGoalsChart(HttpServletRequest request, HttpServletResponse response) throws IOException, JSONException{
		
		String jSON = sendGET(urlPlayers);
		JSONObject jsonObj = new JSONObject(jSON);
		JsonArray jArray = new JsonArray();
		
		long numberOfPlayers = jsonObj.getJSONObject("map").getLong("numberOfPlayers");
		
		for (int i = 0; i < numberOfPlayers; i++) {
			JsonObject json = new JsonObject();
			json.addProperty("name", jsonObj.getJSONObject("map").getJSONObject("results").getJSONArray("myArrayList").getJSONObject(i).getJSONObject("map").getString("name"));
			json.addProperty("goals", jsonObj.getJSONObject("map").getJSONObject("results").getJSONArray("myArrayList").getJSONObject(i).getJSONObject("map").getInt("goal"));
			json.addProperty("color", "#96b8f0");
			jArray.add(json);;
		}
		

		Gson gson = new Gson();
    	String result = gson.toJson(jArray);
    	return result;
	}
	
}
