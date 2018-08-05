package com.test.classes;

public class Player {
	private long ranking, goal, yellowCard, redCard;
	private String tournamentRegionCode, regionCode, tournamentName, teamName, name;
	private float shotsPerGame, rating;
	
	
	
	
	public Player(long ranking, long goal, long yellowCard, long redCard, String tournamentRegionCode,
			String regionCode, String tournamentName, String teamName, String name, float shotsPerGame, float rating) {
		super();
		this.ranking = ranking;
		this.goal = goal;
		this.yellowCard = yellowCard;
		this.redCard = redCard;
		this.tournamentRegionCode = tournamentRegionCode;
		this.regionCode = regionCode;
		this.tournamentName = tournamentName;
		this.teamName = teamName;
		this.name = name;
		this.shotsPerGame = shotsPerGame;
		this.rating = rating;
	}


	public long getRanking() {
		return ranking;
	}




	public void setRanking(long ranking) {
		this.ranking = ranking;
	}




	public long getGoal() {
		return goal;
	}




	public void setGoal(long goal) {
		this.goal = goal;
	}




	public long getYellowCard() {
		return yellowCard;
	}




	public void setYellowCard(long yellowCard) {
		this.yellowCard = yellowCard;
	}




	public long getRedCard() {
		return redCard;
	}




	public void setRedCard(long redCard) {
		this.redCard = redCard;
	}




	public String getTournamentRegionCode() {
		return tournamentRegionCode;
	}




	public void setTournamentRegionCode(String tournamentRegionCode) {
		this.tournamentRegionCode = tournamentRegionCode;
	}




	public String getRegionCode() {
		return regionCode;
	}




	public void setRegionCode(String regionCode) {
		this.regionCode = regionCode;
	}




	public String getTournamentName() {
		return tournamentName;
	}




	public void setTournamentName(String tournamentName) {
		this.tournamentName = tournamentName;
	}




	public String getTeamName() {
		return teamName;
	}




	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}




	public String getName() {
		return name;
	}




	public void setName(String name) {
		this.name = name;
	}




	public float getShotsPerGame() {
		return shotsPerGame;
	}




	public void setShotsPerGame(float shotsPerGame) {
		this.shotsPerGame = shotsPerGame;
	}




	public float getRating() {
		return rating;
	}




	public void setRating(float rating) {
		this.rating = rating;
	}

	
}
