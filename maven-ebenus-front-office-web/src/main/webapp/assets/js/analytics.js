	
	$(function () {
		
		try {
			
			$.get(
			  
				"ReportServlet?ajax_getAnalytics",
				{}
				
			).always(function (ajaxData) {
				
				loadChart(ajaxData);
			});
		}
		 
		catch (err) {
			
			console.log("error : " + err);
		}
	});
	

	function loadChart(ajaxData) {
		
		var chartData = [];
		
		$.each(ajaxData, function(key, value) {
			
		    chartData.push([key, value]);
		});
		
		var chartColors = [
			"rgba(66, 134, 244, 0.4)",
			"rgba(96, 127, 175, 0.4)",
			"rgba(41, 209, 111, 0.4)",
			"rgba(252, 245, 151, 0.4)",
			"rgba(237, 122, 120, 0.4)",
			"rgba(40, 226, 208, 0.4)",
			"rgba(182, 95, 244, 0.4)",
			"rgba(249, 37, 207, 0.4)",
			"rgba(242, 234, 240, 0.4)",
			"rgba(56, 247, 27, 0.4)"
		]
		
		$('#donutChart').highcharts({
			
            chart: {
            	backgroundColor : 'transparent',
            	margin: 20,
                plotBackgroundColor: null,
                plotBorderWidth: null,
                plotShadow: false
            },
            
            title: {
            	text: '',
            },
            
            tooltip: {
                pointFormat: '{series.name}: <b>{point.y}</b>',
                percentageDecimals: 1
            },
            
            plotOptions: {
            	
                pie: {
                	
                	colors: chartColors.map((color, index) => {
                        
                		return {
                			
                			linearGradient: {
                				
                				x1: 0,
                				x2: 0,
                				y1: 0,
                				y2: 1
                			},
                			
                			stops: [
                				[0, chartColors[index + 1]],
                				[1, color]
                	        ]
                		}
                    }),
        			borderColor: null,
                    allowPointSelect: true,
                    cursor: 'pointer',
                    selected: true,
                    dataLabels: {
                        enabled: true,
                        color: 'white',
                        shadow: false,
                        connectorColor: 'white',
                        formatter: function() {
                            return '<b>'+ this.point.name +'</b>: '+ Highcharts.numberFormat(this.percentage, 2) +' %';
                        }
                    }
                }
            },
            
            series: [{

            	type: 'pie',
                name: 'Number of incidents',
                data: chartData,
                innerSize: '55%',
            }]
        });
		
		$('#timeSeriesChart').highcharts({
			
			chart: {
				
                zoomType: 'x',
                backgroundColor : 'transparent',
            	margin: 20,
            },
            
            title: {
                text: null
            },
            
            xAxis: {
                type: 'datetime'
            },
            
            yAxis: {
                title: {
                    text: 'Number Of Incidents'
                },
                gridLineWidth: 0,
                minorGridLineWidth: 0,
                states: {
                	
                    hover: {
                    	gridLineWidth: 0,
                        minorGridLineWidth: 0,
                    }
                },
            },
            
            legend: {
                enabled: false
            },
            
            plotOptions: {
            	
                area: {
                	
                    fillColor: {
                    	
                        linearGradient: {
                            x1: 0,
                            y1: 0,
                            x2: 0,
                            y2: 1
                        },
                        
                        stops: [
                            [0, Highcharts.getOptions().colors[2]],
                            [1, Highcharts.Color(Highcharts.getOptions().colors[7]).setOpacity(0).get('rgba')]
                        ]
                    },
                    
                    marker: {
                        radius: 2
                    },
                    
                    lineWidth: 0,
                    
                    states: {
                    	
                        hover: {
                            lineWidth: 1
                        }
                    },
                    
                    threshold: null
                }
            },

            series: [{
                type: 'area',
                name: 'USD to EUR',
                data: chartData
            }]
        });
    }
