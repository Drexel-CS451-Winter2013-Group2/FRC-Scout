function getContentItems(){
    return [{
        xtype: 'tabpanel',
        activeTab: 0,
        tabPosition: 'top',
        items: [{
            title: 'Overview',
            items: getOverviewItems()
        },{
            title: 'View Match',
            html: '<p> tab2 </p>'
        },{
            title: 'View Team',
            html: '<p> tab3 </p>'
        }]
    }];
}

function getOverviewStore() {	// Temp data generator
    store1 = Ext.create('Ext.data.JsonStore', {
        fields: ['team_number', 'total_points', 'autonomous', 'teleop', 'climb'],
        sorters: ['team_number', 'total_points'],
	    data: [
	           {team_number: 25, total_points: 100, autonomous: 40, teleop: 30, climb: 30},
		       {team_number: 103, total_points: 70, autonomous: 36, teleop: 24, climb: 10}
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
    		fields: ['team_number'],
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
        	align: 'stretch'
        },
        items: [{
        	xtype: 'gridpanel',
            title: '[Event Name] Overview',
            id: 'eventOverviewGrid',
            store: getOverviewStore(),
            columns: [
                { header: 'Team #',  dataIndex: 'team_number', flex: 1  },
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