	
	$(function () {
		
		try {
			
			$.get(
			  
				"ReportServlet?ajax_getAnalytics",
				{}
				
			).always(function (ajaxData) {
				
				loadTypeChart(ajaxData[0]);
				loadTimeSeriesChart(ajaxData[1]);
			});
			
			$.get(
					  
				"UserServlet?ajax_getAnalytics",
				{}
				
			).always(function (ajaxData) {
				
				loadRoleChart(ajaxData);
			});
			
		}
		 
		catch (err) {
			
			console.log("error : " + err);
		}
	});
	

	function loadTypeChart(ajaxData) {
		
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
		
		$('#typeDonutChart').highcharts({
			
            chart: {
            	backgroundColor : 'transparent',
            	margin: 20,
                plotBackgroundColor: null,
                plotBorderWidth: null,
                plotShadow: false,
            },
            
            title: {
            	text: 'Incidents by Type',
            	style: {
            		color: 'white',
            	},
                y: 325,
            },
            
            tooltip: {
                pointFormat: '<b>{point.y}</b> {series.name}',
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
                        distance: '-23%',
                        shadow: false,
                        connectorColor: null,
                        formatter: function() {
                            return this.point.name +': '+ Highcharts.numberFormat(this.percentage, 2) +' %';
                        },
                        style: {
                        	textOutline: false,
                        	fontWeight: 'normal'
                        }
                    }
                }
            },
            
            series: [{

            	type: 'pie',
                name: 'incidents',
                data: chartData,
                innerSize: '55%',
            }]
        });
    }
	
	
	function loadTimeSeriesChart(ajaxData) {
		
		var chartData = [];
		
		$.each(ajaxData, function(key, value) {
			
			var date = new Date(key).getTime();
		    chartData.push([date, value]);
		});
		
		Highcharts.setOptions({
	        lang: {
	            resetZoom: "Reset Zoom"
	        }
	    });
		
		$('#timeSeriesChart').highcharts({
			
			chart: {
				
                zoomType: 'x',
                backgroundColor : 'transparent',
            	margin: 25,
            	
            	resetZoomButton: {
            		
	                theme: {
	                    fill: '#0f2940',
	                    style: {
                            color: 'white',
                        },
	                    stroke: null,
	                    r: 5,
	                    
	                    states: {
	                        hover: {
	                            fill: '#17a2b8',
	                            style: {
	                                color: 'white'
	                            }
	                        }
	                    }
	                }
	            }, 
            },
            
            title: {
                text: "Incidents Over Time",
                style: {
            		color: 'white',
            	},
            },
            
            tooltip: {
                pointFormat: '<b>{point.y}</b> {series.name}',
                percentageDecimals: 1
            },
            
            xAxis: {
                type: 'datetime',
                labels: {
                	style: {
                        color: 'white'
                    }
                }
            },
            
            yAxis: {
                title: {
                    text: 'Number Of Incidents'
                },
                labels: {
                	style: {
                        color: 'white'
                    }
                },
                gridLineWidth: 0,
                minorGridLineWidth: 0,
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
                            x2: 1,
                            y2: 1,
                            x3: 2,
                            y3: 2,
                        },
                        
                        stops: [
                            [0, Highcharts.Color(Highcharts.getOptions().colors[8]).setOpacity(0.8).get('rgba')],
                            [1, Highcharts.Color(Highcharts.getOptions().colors[5]).setOpacity(0).get('rgba')],
                            [2, Highcharts.Color(Highcharts.getOptions().colors[4]).setOpacity(0).get('rgba')],
                        ]
                    },
                    
                    marker: {
                        radius: 2
                    },
                    
                    lineWidth: 0.5,
                    
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
                name: 'incidents',
                data: chartData
            }]
        });
	}
	
	
	function loadRoleChart(ajaxData) {
		
		var chartData = [];
		
		$.each(ajaxData, function(key, value) {
			
		    chartData.push([key, value]);
		});
		
		var chartColors = [
			"rgba(237, 122, 120, 0.4)",
			"rgba(66, 134, 244, 0.4)",
			"rgba(41, 209, 111, 0.4)",
			"rgba(252, 245, 151, 0.4)",
		]
		
		$('#roleDonutChart').highcharts({
			
            chart: {
            	backgroundColor : 'transparent',
            	margin: 20,
                plotBackgroundColor: null,
                plotBorderWidth: null,
                plotShadow: false,
            },
            
            title: {
            	text: 'Users By Role',
            	margin: 50,
            	rotation: 270,
            	style: {
            		color: 'white',
            	},
            	y: 160
            },
            
            tooltip: {
                pointFormat: '<b>{point.y}</b> {series.name}',
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
                        distance: '-23%',
                        shadow: false,
                        connectorColor: null,
                        
                        formatter: function() {
                            return this.point.name +': '+ Highcharts.numberFormat(this.percentage, 2) +' %';
                        },
                        style: {
                        	textOutline: false,
                        	fontWeight: 'normal'
                        }
                    }
                },
            },
            
            series: [{

            	type: 'pie',
                name: 'users',
                data: chartData,
                innerSize: '55%',
            }]
        });
    }
