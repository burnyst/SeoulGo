<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>
<t:path>
</t:path>
<html>
<head>
	<meta charset="utf-8">
	<script src="${libPath}/d3/5.16.0/d3.min.js"></script>
	<script src="${libPath}/d3/d3.layout.cloud.js"></script>
</head>
<body>
  <div id="cloud"></div>
  <script type="text/javascript">
  //var wordscale = d3.scaleLinear().range([10,60]);
  var fill = d3.schemeCategory10;
	var test = [
		{"word":"가", "freq":1000},
		{"word":"나", "freq":900},
		{"word":"다", "freq":800},
		{"word":"라", "freq":700},
		{"word":"마", "freq":600},
		{"word":"바", "freq":500},
		{"word":"사", "freq":400},
		{"word":"아", "freq":300},
		{"word":"자", "freq":200},
		{"word":"차", "freq":100},
		{"word":"하", "freq":10}
	];
	var max = d3.max(test, function(d) {return d.freq;});
	var data = [];
	test.forEach(function(d) {
		data.push({text: d.word, size: d.freq/max*200});
		/*for (var i=0; i < d.freq; i++) {
			data.push(d.word);
		}*/
	});
	//wordscale.domain([0,d3.max(data, function(d) {return d.fsize;})]); 
  	var layout = d3.layout.cloud()
	  .size([500, 500])
	  .words(data/*data.map(function(d) {
	    return {text: d, size: 10 + Math.random() * 90};
	  })*/)
	  .padding(5)
	  //.rotate(function() { return ~~(Math.random() * 2) * 90; })
	  .rotate(0)
	  .font("Impact")
	  .fontSize(function(d) { return +d.size; })
	  .on("end", draw);
	
	layout.start();
	
	function draw(words) {
	d3.select("body").append("svg")
	    .attr("width", layout.size()[0])
	    .attr("height", layout.size()[1])
	  .append("g")
	    .attr("transform", "translate(" + layout.size()[0] / 2 + "," + layout.size()[1] / 2 + ")")
	  .selectAll("text")
	    .data(words)
	  .enter().append("text")
	    .style("font-size", function(d) { return d.size + "px"; })
	    .style("font-family", "Impact")
	    .style("fill", function(d, i) { return fill[i]; })
	    .attr("text-anchor", "middle")
	    .attr("transform", function(d) {
	      return "translate(" + [d.x, d.y] + ")rotate(" + d.rotate + ")";
	    })
	    .text(function(d) { return d.text; });
	}
  </script>
</body>
</html>