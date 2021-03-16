<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
  <head>
    <!--Load the AJAX API-->
	<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
    <script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
    <script type="text/javascript">

      // Load the Visualization API and the corechart package.
      google.charts.load('current', {'packages':['corechart']});

      // Set a callback to run when the Google Visualization API is loaded.
      google.charts.setOnLoadCallback(drawChart);

      // Callback that creates and populates a data table,
      // instantiates the pie chart, passes in the data and
      // draws it.
      function drawChart() {

        // Create the data table.
        var data = new google.visualization.DataTable();
        data.addColumn('string', '부서');
        data.addColumn('number', '인원수');
        
        var arr = [];
        //ajax
        $.ajax({
        	url: "getChartData",
        	async: false, //동기 비동기 설정(false : 동기식)
        	dataType: "json", 
        	success : function(result){
        		for(o of result){ //in은 index값을 받아오는것  of는 object
        			console.log(o);
        			arr.push([o.SALE_DATE, o.PRICE]);
        		}
        	}
        });
        data.addRows(arr);

        // Set chart options
        var options = {'title':'일별 판매내역',
                       'width':1000,
                       'height':900,
                       vAxis: {format:"$#,###", gridlines: { count: 4 } }               
        };

        // Instantiate and draw our chart, passing in some options.
        var chart = new google.visualization.ColumnChart(document.getElementById('chart_div'));
        chart.draw(data, options);
      }
    </script>
  </head>

  <body>
    <!--Div that will hold the pie chart-->
    <div id="chart_div"></div>
  </body>
</html>