	function getEventStore() { //temp data generator
        store1 = Ext.create('Ext.data.JsonStore', {
            fields: ['id', 'name', 'location', 'start_date', 'end_date'],
            data: eventJSON
        });
        return store1;
    }
	
	function getTeamStore() { //temp data generator
        store1 = Ext.create('Ext.data.JsonStore', {
            fields: ['id', 'name', 'location'],
            data: teamJSON
        });
        return store1;
    }
	
	function getTeamEventsStore() { //temp data generator
		store1 = Ext.create('Ext.data.JsonStore', {
            fields: ['id', 'event1', 'event2'],
            data: [{ 'id': '1', 'event1': 30, 'event2': 29},
                   { 'id': '2', 'event1': 20, 'event2': 49},
                   { 'id': '3', 'event1': 43, 'event2': 55}
            	]
            });
        return store1;
	}
	
	function getRadarChartStore() {
		store1 = Ext.create('Ext.data.JsonStore', {
            fields: ['category', 'data'],
            data: [{ 'category': 'Auton.', 'data': 24},
                   { 'category': 'Teleop.', 'data': 50},
                   { 'category': 'Climb', 'data': 20},
            	]
            });
        return store1;
	}

function getContentItems(){
	return [{
        xtype: 'tabpanel',
        activeTab: 0,
        tabPosition: 'top',
        overflowY: 'auto',
        overflowX: 'auto',
        minWidth: 800,
        items: [{
            title: 'Group By Event',
            padding: 10,
            items: [{
            	xtype: 'combobox',
            	fieldLabel: 'Select Event',
            	store: getEventStore(),
            	queryMode: 'local',
            	displayField: 'name',
            	valueField: 'id'
            }, {
                xtype: 'tabpanel',
                activeTab: 0,
                tabPosition: 'top',
                items: [{
                    title: 'Overview',
                    items: getOverviewItems()
                },{
                    title: 'View Match',
                    items: getViewMatchItems()
                },{
                    title: 'View Team',
                    items: getViewTeamItems()
                }]
            }]
        },{
            title: 'Group By Team',
            padding: 10,
            items: [{
            	xtype: 'combobox',
            	fieldLabel: 'Select Team',
            	store: getTeamStore(),
            	queryMode: 'local',
            	displayField: 'id',
            	valueField: 'id'
            }, {
                xtype: 'tabpanel',
                activeTab: 0,
                tabPosition: 'top',
                items: [{
                    title: 'Team Profile',
                    items: getTeamProfileItems()
                },{
                    title: 'View Matches',
                    items: getTeamMatchItems()
                }]
            }]
        }]
    }];
}

function getOverviewStore() {	// Temp data generator
    store1 = Ext.create('Ext.data.JsonStore', {
        fields: ['id', 'total_points', 'autonomous', 'teleop', 'climb'],
        sorters: ['id', 'total_points'],
	    data: [
	           {id: 25, total_points: 100, autonomous: 40, teleop: 30, climb: 30},
		       {id: 103, total_points: 70, autonomous: 36, teleop: 24, climb: 10}
		       ]
    });
    return store1;
}

function getOverviewItems() {
	var overviewChart = {
    	xtype: 'chart',
    	animate: 'true',
    	store: getOverviewStore(),
    	legend: {
    		position: 'bottom'
    	},
    	axes: [{
    		type: 'Numeric',
    		position: 'bottom',
    		fields: ['autonomous', 'teleop', 'climb'],
    		grid: true
    	}, {
    		type: 'Category',
    		position: 'left',
    		fields: ['id'],
    		title: false
    	}],
    	series: [{
    		type: 'bar',
    		axis: 'bottom',
    		gutter: 10,
    		xField: 'team_number',
    		yField: ['autonomous', 'teleop', 'climb'],
    		stacked: true,
    		tips: {
    			trackMouse: true,
    			renderer: function(storeItem, item) {
    				this.setTitle(String(item.value[1]));
    			}
    		}
    	}],
    	width: 300,
    	minHeight: 300
    };
	
	overviewChart.store.sort([{property: 'total_points', direction: 'ASC'}]);
	
	return [{
        layout: {
        	type: 'hbox',
        	align: 'left'
        },
        items: [{
        	xtype: 'gridpanel',
            id: 'eventOverviewGrid',
            store: getOverviewStore(),
            columns: [
                { header: 'Team #',  dataIndex: 'id', flex: 1},
                { header: 'Total Points', dataIndex: 'total_points'},
                { header: 'Auton. Points', dataIndex: 'autonomous' },
                { header: 'Disc Points', dataIndex: 'teleop'},
                { header: 'Climb Points', dataIndex: 'climb'},
            ],
            height: 400,
            width: 500, 
            margin: 10
        }, overviewChart]
	}];
}

function getViewMatchItems() {
	return [{
		defaultType: 'container',
		layout: {
			type: 'vbox',
			align: 'left',
		},
		items: [{
			layout: {
				type: 'hbox'
			},
			items: [{
				xtype: 'panel',
				border: false,
		        html: '<h1>Match [#]</h1>',
		        margin: 10,
			}, {
				xtype: 'panel',
				border: false,
				items: [{
					xtype: 'textfield',
					border: false,
					name: 'matchSearchBox',
					fieldLabel: 'Match search'
				}],
				margin: '10 0 10 120'
			}, {
				xtype: 'button',
				text: 'Search',
				handler: function() {
					alert('Search for a specified match number.');
				},
				margin: 10
			}]
		}, {
			xtype: 'gridpanel',
			defaultType: 'container',
			title: 'Red Alliance',
			id: 'redAllianceGrid',
			//store: getRedAllianceStore(),
			columns: [
			    { header: 'Team #',  dataIndex: 'id', flex: 1},
                { header: 'Auton. Points', dataIndex: 'autonomous' },
                { header: 'Teleop Points', dataIndex: 'teleop'},
                { header: 'Climb Points', dataIndex: 'climb'},
                { header: 'Total Points', dataIndex: 'total_points'}
			],
			width: 500,
			margin: 10
		}, {
			xtype: 'gridpanel',
			defaultType: 'container',
			title: 'Blue Alliance',
			id: 'blueAllianceGrid',
			//store: getBlueAllianceStore(),
			columns: [
			    { header: 'Team #',  dataIndex: 'id', flex: 1},
                { header: 'Auton. Points', dataIndex: 'autonomous' },
                { header: 'Teleop Points', dataIndex: 'teleop'},
                { header: 'Climb Points', dataIndex: 'climb'},
                { header: 'Total Points', dataIndex: 'total_points'}
			],
			width: 500,
			margin: 10
		}, {
			xtype: 'button',
			text: 'View Match Record',
			handler: function() {
				alert('This button brings up a detailed view for the selected match record.');
			},
			margin: '0 0 10 400'
		}]
	}];
}

function getTeamMatchStore() {	// Temp data generator
    store1 = Ext.create('Ext.data.JsonStore', {
        fields: ['id', 'total_points', 'autonomous', 'teleop', 'climb'],
	    data: [
	           {id: 1, total_points: 100, autonomous: 40, teleop: 33, climb: 30},
		       {id: 5, total_points: 54, autonomous: 20, teleop: 24, climb: 10},
		       {id: 7, total_points: 70, autonomous: 36, teleop: 24, climb: 10},
		       {id: 12, total_points: 66, autonomous: 18, teleop: 28, climb: 20}
		       ]
    });
    return store1;
}

function getTeamPieChartStore() {	// Temp data generator
    store1 = getTeamMatchStore();
    var pointTotals = {
    		autonomous: 0,
    		teleop: 0,
    		climb: 0
    };
    for (var i = 0; i < store1.count(); i++) {
    	pointTotals.autonomous += store1.getAt(i).autonomous;
    	pointTotals.teleop += store1.getAt(i).teleop;
    	pointTotals.climb += store1.getAt(i).climb;
    }
	var store2 = Ext.create('Ext.data.JsonStore', {
    	fields: ['category', 'total'],
    	data: [
    	       { category: 'autonomous', total: 10 },
    	       { category: 'teleop', total: 24 },
    	       { category: 'climb', total: 10 }
    	       ]
    });
    return store2;
}

function getViewTeamItems() {
	return [{
		defaultType: 'container',
		layout: {
			type: 'vbox',
			align: 'left'
		},
		items: [{
			margin: 10,
			layout: {
				type: 'hbox'
			},
			items: [{
				xtype: 'panel',
				border: false,
				margin: '10 0 0 0',
				html: '<h1>Team [#]</h1>'
			}, {
				xtype: 'panel',
				border: false,
				margin: '10 0 0 150',
				items: [{
					xtype: 'textfield',
					name: 'teamSearchBox',
					fieldLabel: 'Team Search'
				}]
			}, {
				xtype: 'button',
				text: 'search',
				margin: '10 0 0 5',
				handler: function() {
					alert('This will search for matches and update the tables below!');
				}
			}]
		}, {
			xtype: 'gridpanel',
			title: 'Match Records',
			id: 'teamMatchGrid',
			store: getTeamMatchStore(),
			columns: [
			    { header: 'Match #',  dataIndex: 'id', flex: 1},
                { header: 'Auton. Points', dataIndex: 'autonomous' },
                { header: 'Teleop Points', dataIndex: 'teleop'},
                { header: 'Climb Points', dataIndex: 'climb'},
                { header: 'Total Points', dataIndex: 'total_points'}
			],
			width: 500,
			margin: '0 0 0 10',
		}, {
			xtype: 'button',
			text: 'View Match Record',
			handler: function() {
				alert('This will bring up a detailed view for the selected match record.');
			},
			margin: '10 0 0 400'
		}, {
			layout: {
				type: 'hbox'
			}, 
			items: [{
				xtype: 'chart',
				animate: true,
				width: 500,
		    	height: 300,
		    	margin: 10,
		    	store: getTeamMatchStore(),
		    	legend: {
		    		position: 'bottom'
		    	},
		    	axes: [{
		    		type: 'Numeric',
		    		position: 'left',
		    		grid: true,
		    		minimum: 0
		    	}, {
		    		type: 'Category',
		    		position: 'bottom',
		    		fields: ['id'],
		    		title: 'Match #'
		    	}],
		    	series: [{
		    		type: 'line',
		    		axis: 'left',
		    		xField: 'id',
		    		yField: 'total_points',
		    		fill: true
		    	}, {
		    		type: 'line',
		    		axis: 'left',
		    		xField: 'id',
		    		yField: 'autonomous'
		    	}, {
		    		type: 'line',
		    		axis: 'left',
		    		xField: 'id',
		    		yField: 'teleop'
		    	}, {
		    		type: 'line',
		    		axis: 'left',
		    		xField: 'id',
		    		yField: 'climb'
		    	}]
			}, {
				xtype: 'chart',
				animate: true,
				margin: '50',
				width: 200,
				height: 200,
				store: getTeamPieChartStore(),
				series: [{
					type: 'pie',
					angleField: 'total',
					label: {
						field: 'category',
						display: 'inside',
						contrast: true,
						font: '12px Helvetica'
					}
				}]
			}]
		}]
	}];
}

function getTeamProfileItems() {
	return [{
		defaultType: 'container',
		layout: {
			type: 'vbox',
			align: 'left'
		},
		items: [{
			defaultType: 'container',
			layout: {
				type: 'hbox',
				align: 'left'
			},
			items: [{
				xtype: 'gridpanel',
				title: 'Event Averages',
				id: 'eventAverageGrid',
				//store: getTeamMatchStore(),
				columns: [
				    { header: 'Event',  dataIndex: 'id', flex: 1},
	                { header: 'Auton. Points', dataIndex: 'autonomous' },
	                { header: 'Teleop Points', dataIndex: 'teleop'},
	                { header: 'Climb Points', dataIndex: 'climb'},
	                { header: 'Total Points', dataIndex: 'total_points'}
				],
				width: 500,
				margin: 10
			}, {
				defaultType: 'container',
				layout: {
					type: 'vbox'
				},
				items: [{
					xtype: 'panel',
					border: false,
					html: 'Team profile area',
					width: 350,
					height: 300,
					margin: 10
				}, {
					xtype: 'filefield',
					buttonOnly: true,
					margin: '0 0 0 100'
				}]
			}]
		}, {
			defaultType: 'container',
			layout: {
				type: 'hbox'
			},
			items: [{
				xtype: 'chart',
				defaultType: 'container',
				animate: true,
				width: 500,
		    	height: 300,
		    	margin: 25,
		    	store: getTeamEventsStore(),
		    	legend: {
		    		position: 'bottom'
		    	},
		    	axes: [{
		    		type: 'Numeric',
		    		position: 'left',
		    		title: 'Points Scored',
		    		grid: true,
		    		minimum: 0
		    	},{
		    		type: 'Category',
		    		position: 'bottom',
		    		fields: ['id'],
		    		title: 'Match #'
		    	}],
		    	series: [{
		    		type: 'line',
		    		axis: 'left',
		    		xField: 'id',
		    		yField: 'event1'
		    	}, {
		    		type: 'line',
		    		axis: 'left',
		    		xField: 'id',
		    		yField: 'event2'
		    	}]
			}, {
				xtype: 'chart',
				defaultType: 'container',
				width: 350,
			    height: 250,
			    margin: '25 0 0 0',
			    animate: true,
			    theme:'Category2',
			    store: getRadarChartStore(),
			    axes: [{
			        type: 'Radial',
			        position: 'radial',
			        label: {
			            display: true
			        }
			    }],
			    series: [{
			        type: 'radar',
			        xField: 'category',
			        yField: 'data',
			        showMarkers: true,
			        showInLegend: true,
			        markerConfig: {
			            radius: 5,
			            size: 5
			        },
			        style: {
			            'stroke-width': 2,
			            fill: 'none'
			        }
			    }]
			}]
		}]
	}];
}

function getTeamMatchItems() {
	return [{
		defaultType: 'container',
		layout: {
			type: 'vbox',
			align: 'left'
		},
		items: [{
			defaultType: 'container',
			xtype: 'gridpanel',
			//store: ,
			columns: [
			    { header: 'Event',  dataIndex: 'event'},
                { header: 'Match #', dataIndex: 'match_id' },
                { header: 'Auton Points', dataIndex: 'autonomous' },
                { header: 'Teleop Points', dataIndex: 'teleop'},
                { header: 'Climb Points', dataIndex: 'climb'},
                { header: 'Total Points', dataIndex: 'total_points'}
			],
			margin: 10,
		}, {
			xtype: 'button',
			text: 'View Match Record',
			handler: function() {
				alert('Open match record with all details filled in.');
			},
			margin: '0 0 10 500'
		}]
	}];
}