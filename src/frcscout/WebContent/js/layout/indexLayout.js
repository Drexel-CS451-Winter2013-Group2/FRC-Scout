	function getEventStore() { //temp data generator
        store1 = Ext.create('Ext.data.JsonStore', {
            fields: ['id', 'name', 'location', 'start_date', 'end_date'],
            data: eventJSON
        });
        return store1;
    }

function getContentItems(){
    return [{
    	xtype: 'combobox',
    	fieldLabel: 'Select Event',
    	store: getEventStore(),
    	queryMode: 'local',
    	displayField: 'name',
    	valueField: 'id',
    	padding: 5
    }, {
        xtype: 'tabpanel',
        activeTab: 0,
        tabPosition: 'top',
        items: [{
            title: 'Overview',
            items: getOverviewItems()
        },{
            title: 'View Match',
            html: '<p> tab2 </p>',
            items: getViewMatchItems()
        },{
            title: 'View Team',
            html: '<p> tab3 </p>',
            items: getViewTeamItems()
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
    	height: 400
    };
	
	overviewChart.store.sort([{property: 'total_points', direction: 'ASC'}]);
	
	return [{
        layout: {
        	type: 'hbox',
        	align: 'left'
        },
        items: [{
        	xtype: 'gridpanel',
            title: '[Event Name] Overview',
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
            width: 600
        }, overviewChart]
	}];
}

function getViewMatchItems() {
	return [{
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
		        html: '<h1>Match [#]</h1>',
			}, {
				xtype: 'panel',
				items: [{
					xtype: 'textfield',
					name: 'matchSearchBox',
					fieldLabel: 'Match search'
				}]
			}, {
				xtype: 'button',
				text: 'Search',
				handler: function() {
					alert('Search for a specified match number.');
				}
			}]
		}, {
			xtype: 'gridpanel',
			title: 'Red Alliance',
			id: 'redAllianceGrid',
			//store: getRedAllianceStore(),
			columns: [
			    { header: 'Team #',  dataIndex: 'id', flex: 1},
                { header: 'Auton. Points', dataIndex: 'autonomous' },
                { header: 'Disc Points', dataIndex: 'teleop'},
                { header: 'Climb Points', dataIndex: 'climb'},
                { header: 'Total Points', dataIndex: 'total_points'}
			],
			width: 500
		}, {
			xtype: 'gridpanel',
			title: 'Blue Alliance',
			id: 'blueAllianceGrid',
			//store: getBlueAllianceStore(),
			columns: [
			    { header: 'Team #',  dataIndex: 'id', flex: 1},
                { header: 'Auton. Points', dataIndex: 'autonomous' },
                { header: 'Disc Points', dataIndex: 'teleop'},
                { header: 'Climb Points', dataIndex: 'climb'},
                { header: 'Total Points', dataIndex: 'total_points'}
			],
			width: 500
		}, {
			xtype: 'button',
			text: 'View Match Record',
			handler: function() {
				alert('This button brings up a detailed view for the selected match record.');
			}
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
		layout: {
			type: 'vbox',
			align: 'left'
		},
		items: [{
			layout: {
				type: 'hbox'
			},
			items: [{
				xtype: 'panel',
				html: '<h1>Team [#]</h1>'
			}, {
				xtype: 'panel',
				items: [{
					xtype: 'textfield',
					name: 'teamSearchBox',
					fieldLabel: 'Team Search'
				}]
			}, {
				xtype: 'button',
				text: 'search',
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
                { header: 'Disc Points', dataIndex: 'teleop'},
                { header: 'Climb Points', dataIndex: 'climb'},
                { header: 'Total Points', dataIndex: 'total_points'}
			],
			width: 500
		}, {
			xtype: 'button',
			text: 'View Match Record',
			handler: function() {
				alert('This will bring up a detailed view for the selected match record.');
			}
		}, {
			layout: {
				type: 'hbox'
			}, 
			items: [{
				xtype: 'chart',
				animate: true,
				width: 300,
		    	height: 300,
		    	store: getTeamMatchStore(),
		    	legend: {
		    		position: 'left'
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
				width: 300,
				height: 300,
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