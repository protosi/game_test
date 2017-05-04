<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<meta charset="utf-8">
<style>

body {
  font: 10px sans-serif;
}

.axis path,
.axis line {
  fill: none;
  stroke: #000;
  shape-rendering: crispEdges;
}

.x.axis path {
  display: none;
}

.line {
  fill: none;
  stroke: steelblue;
  stroke-width: 1.5px;
}

</style>
<body>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.2.4/jquery.min.js"></script>
<script src="//d3js.org/d3.v3.min.js"></script>
<script>

$(document).ready(function(){
	//makeGraph();
	$.ajax("/GameTest/v2/monthly").done(function(data){
		if(typeof data == 'string' )
		{
			data = JSON.parse(data);
		}
		var names = Object.getOwnPropertyNames(data);
		
		for(var i = 0 ; i < names.length ; i++)
		{
			drawGraph(names[i], data[names[i]]);
		}
	}
	);
});

function drawGraph(name, json)
{
	console.log(json);
	var margin = {top: 20, right: 80, bottom: 30, left: 50},
    width = 800 - margin.left - margin.right,
    height = 400 - margin.top - margin.bottom;

	var parseDate = d3.time.format("%Y-%m-%d").parse;
	
	var x = d3.time.scale()
	    .range([0, width]);
	
	var y = d3.scale.linear()
	    .range([height, 0]);
	
	var color = d3.scale.category10();
	
	var xAxis = d3.svg.axis()
	    .scale(x)
	    .orient("bottom");
	
	var yAxis = d3.svg.axis()
	    .scale(y)
	    .orient("left");
	
	var line = d3.svg.line()
	    .interpolate("basis")
	    .x(function(d) { return x(d.date); })
	    .y(function(d) { return y(d.value); });
	
	var svg = d3.select("body").append("svg")
	    .attr("width", width + margin.left + margin.right)
	    .attr("height", height + margin.top + margin.bottom)
	  .append("g")
	    .attr("transform", "translate(" + margin.left + "," + margin.top + ")");
	
	var a = [];
	  
    var data = [];
    
    for(var i = 0 ; i < json.fcst.length ; i++)
    {
  	  var obj = {};
  	  obj["date"] = json.date[i];
  	  obj["fcst"] = json.fcst[i];
  	  obj["upper"] = json.upper[i];
  	  obj["lower"] = json.lower[i];
  	  
  	  data.push(obj);
    }
    color.domain(d3.keys(data[0]).filter(function(key) { return key !== "date"; }));

    
    data.forEach(function(d) {
      d.date = parseDate(d.date);
    }); 
    

     var datas = color.domain().map(function(name) {
      return {
        name: name,
        values: data.map(function(d) {
          return {date: d.date, value: +d[name]};
        })
      };
    }); 

     console.log("datas");
     console.log(datas);

    x.domain(d3.extent(data, function(d) { return d.date; }));

    console.log("xdomain");
    
    y.domain([
      d3.min(datas, function(c) { return d3.min(c.values, function(v) { return v.value; }); }),
      d3.max(datas, function(c) { return d3.max(c.values, function(v) { return v.value; }); })
    ]);

    svg.append("g")
        .attr("class", "x axis")
        .attr("transform", "translate(0," + height + ")")
        .call(xAxis);

    
    svg.append("g")
        .attr("class", "y axis")
        .call(yAxis)
      .append("text")
        .attr("transform", "rotate(-90)")
        .attr("y", 6)
        .attr("dy", ".71em")
        .style("text-anchor", "end")
        .text(name);

    
    var city = svg.selectAll(".datas")
        .data(datas)
        .enter().append("g")
        .attr("class", "datas");
        console.log("city");
        console.log(city);

    city.append("path")
        .attr("class", "line")
        .attr("d", function(d) { return line(d.values); })
        .style("stroke", function(d) { return color(d.name); });
    console.log("text");
    
    city.append("text")
        .datum(function(d) { return {name: d.name, value: d.values[d.values.length - 1]}; })
        .attr("transform", function(d) { return "translate(" + x(d.value.date) + "," + y(d.value.value) + ")"; })
        .attr("x", 3)
        .attr("dy", ".35em")
        .text(function(d) { return d.name; });

}

function makeGraph()
{
	var margin = {top: 20, right: 80, bottom: 30, left: 50},
    width = 960 - margin.left - margin.right,
    height = 500 - margin.top - margin.bottom;

var parseDate = d3.time.format("%Y-%m-%d").parse;

var x = d3.time.scale()
    .range([0, width]);

var y = d3.scale.linear()
    .range([height, 0]);

var color = d3.scale.category10();

var xAxis = d3.svg.axis()
    .scale(x)
    .orient("bottom");

var yAxis = d3.svg.axis()
    .scale(y)
    .orient("left");

var line = d3.svg.line()
    .interpolate("basis")
    .x(function(d) { return x(d.date); })
    .y(function(d) { return y(d.temperature); });

var svg = d3.select("body").append("svg")
    .attr("width", width + margin.left + margin.right)
    .attr("height", height + margin.top + margin.bottom)
  .append("g")
    .attr("transform", "translate(" + margin.left + "," + margin.top + ")");

d3.json("/v2/monthly", function(error, raw_data) {

  if (error) throw error;

  var a = [];
  
  var data = [];
  
  for(var i = 0 ; i < raw_data["stock_kor"].fcst.length ; i++)
  {
	  var obj = {};
	  obj["date"] = raw_data["stock_kor"].date[i];
	  obj["fcst"] = raw_data["stock_kor"].fcst[i];
	  obj["upper"] = raw_data["stock_kor"].upper[i];
	  obj["lower"] = raw_data["stock_kor"].lower[i];
	  
	  data.push(obj);
  }
  
  console.log("data");
  console.log(data);
  
  color.domain(d3.keys(data[0]).filter(function(key) { return key !== "date"; }));
  
  console.log("domain");
  
  data.forEach(function(d) {
    d.date = parseDate(d.date);
  }); 
  
  console.log("parseDate");
  
   var cities = color.domain().map(function(name) {
    return {
      name: name,
      values: data.map(function(d) {
        return {date: d.date, temperature: +d[name]};
      })
    };
  }); 
   
   console.log("cities");
   console.log(cities);

  x.domain(d3.extent(data, function(d) { return d.date; }));

  console.log("xdomain");
  
  y.domain([
    d3.min(cities, function(c) { return d3.min(c.values, function(v) { return v.temperature; }); }),
    d3.max(cities, function(c) { return d3.max(c.values, function(v) { return v.temperature; }); })
  ]);
  
  console.log("ydomain");

  svg.append("g")
      .attr("class", "x axis")
      .attr("transform", "translate(0," + height + ")")
      .call(xAxis);

  console.log("height");
  
  svg.append("g")
      .attr("class", "y axis")
      .call(yAxis)
    .append("text")
      .attr("transform", "rotate(-90)")
      .attr("y", 6)
      .attr("dy", ".71em")
      .style("text-anchor", "end")
      .text("KOSPI");
  
  console.log("temperature");

  var city = svg.selectAll(".city")
      .data(cities)
    .enter().append("g")
      .attr("class", "city");
  
  console.log("city");
  console.log(city);

  city.append("path")
      .attr("class", "line")
      .attr("d", function(d) { return line(d.values); })
      .style("stroke", function(d) { return color(d.name); });

  console.log("city2");
  
  city.append("text")
      .datum(function(d) { return {name: d.name, value: d.values[d.values.length - 1]}; })
      .attr("transform", function(d) { return "translate(" + x(d.value.date) + "," + y(d.value.temperature) + ")"; })
      .attr("x", 3)
      .attr("dy", ".35em")
      .text(function(d) { return d.name; });
  
  console.log("city3");
});
}


</script>