$(document).ready(function(){
	
//Amchart
	
	$.ajax({
        url : '/getGoalsChart',
        type : 'post',
        data : {
        },
        success : function(response) {
            console.log('success');
            let chartdata = JSON.parse(response)
            var chart = AmCharts.makeChart("chartdiv", {
            	  "type": "serial",
            	  "theme": "light",
            	  "marginRight": 50,
            	  "dataProvider": chartdata,
            	  "valueAxes": [{
            	    "axisAlpha": 0,
            	    "position": "left",
            	    "title": "Goals per player"
            	  }],
            	  "startDuration": 1,
            	  "graphs": [{
            	    "balloonText": "<b>[[category]]: [[value]]</b>",
            	    "fillColorsField": "color",
            	    "fillAlphas": 0.9,
            	    "lineAlpha": 0.2,
            	    "type": "column",
            	    "valueField": "goals"
            	  }],
            	  "chartCursor": {
            	    "categoryBalloonEnabled": false,
            	    "cursorAlpha": 0,
            	    "zoomable": false
            	  },
            	  "categoryField": "name",
            	  "categoryAxis": {
            	    "gridPosition": "start",
            	    "labelRotation": 45
            	  },
            	  "export": {
            	    "enabled": true
            	  }

            	});
      	
        }
	});
	
});// End function ready.

function playerDetails(id){
	
	$.ajax({
        url : '/getPlayerSelected',
        type : 'post',
        data : {
        id: id
        },
        success : function(response) {
            console.log('success');
            if (response == "error"){
            	alert("Error getting the Player data");
            }
            else{
            	let playerSelected = JSON.parse(response);
                $("#playerPicture").attr("src","/resources/images/players/"+playerSelected.playerId+".jpg");
                $("#ranking").text(playerSelected.ranking);
                $("#headerModal").text(playerSelected.name);
                $("#namePlayer").text(playerSelected.firstName + " " + playerSelected.lastName);
                $("#agePlayer").text(playerSelected.age);
                $("#teamName").text(playerSelected.teamName);
                $("#positionText").text(playerSelected.positionText);
                $("#playerId").text(playerSelected.playerId);
                
                
                $("#height").text(playerSelected.height);
                $("#weight").text(playerSelected.weight);
                
                
                $("#tournamentName").text(playerSelected.tournamentName + " - " + playerSelected.tournamentShortName);
                $("#tournamentRegionCode").attr("src","resources/images/flags/"+ playerSelected.tournamentRegionCode+".jpg");
                $("#seasonName").text(playerSelected.seasonName);
                $("#regionCode").attr("src","resources/images/flags/"+ playerSelected.regionCode+".jpg");
                
                $("#playedPositions").text(playerSelected.playedPositions);
                $("#minsPlayed").text(playerSelected.minsPlayed);
                $("#goal").text(playerSelected.goal);
                $("#assistTotal").text(playerSelected.assistTotal);
                $("#yellowCard").text(playerSelected.yellowCard);
                $("#redCard").text(playerSelected.redCard);
                $("#shotsPerGame").text((parseFloat(playerSelected.shotsPerGame)).toFixed(2));
                $("#rating").text((parseFloat(playerSelected.rating)).toFixed(2));
                $("#aerialWonPerGame").text((parseFloat(playerSelected.aerialWonPerGame)).toFixed(2));
                $("#passSuccess").text((parseFloat(playerSelected.passSuccess)).toFixed(2));
                $("#apps").text(playerSelected.apps);
                $("#subOn").text(playerSelected.subOn);
                $("#manOfTheMatch").text(playerSelected.manOfTheMatch);
                
                $("#playerDetailsModal").modal("show");
            }
            
            
        }
        
    });
	

}